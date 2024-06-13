<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/etc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        .content {
            padding-top: 160px;
            margin-left: auto;
            margin-right: auto;
            width: 70%;
        }
        .reply-form, .edit-form {
            display: none;
        }
        .comment-level-1 {
            margin-left: 20px;
        }
        .comment-level-2 {
            margin-left: 40px;
        }
        .comment-level-3{
            margin-left: 60px;
        }
        .button-group {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
        .button-group a, .button-group button {
            border: none;
            background: none;
            color: #5a5a5a; /* 차분한 색상 */
            text-decoration: none;
            cursor: pointer;
            font-size: 1rem;
        }
        .button-group a:hover, .button-group button:hover {
            color: #343a40; /* 호버 시 조금 더 어두운 색상 */
            text-decoration: underline;
        }

        .col-12 {
            bottom: 65px;
            -ms-flex: 0 0 100%;
            flex: 0 0 100%;
            max-width: 100%;
        }

    </style>
</head>
<body>
<div class="content">
    <div class="container">
        <!-- 게시글 상세 내용 -->
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <span class="text-secondary">[${boardDetailModel.categoryName}]</span>
                        <h2 class="ml-2" style="flex-grow: 1; text-align: center">${boardDetailModel.boardTitle}</h2>
                    </div>
                    <div class="card-body">
                        <div class="text-right" style="line-height: 1.5;">
                            <p style="margin-bottom: 0.2rem;"><strong>작성자:</strong> ${boardDetailModel.userNickname}</p>
                            <p style="margin-bottom: 0.2rem; font-size: 0.9rem;"><strong>작성일자:</strong> ${boardDetailModel.boardDate}</p>
                            <p style="margin-bottom: 0.2rem; font-size: 0.9rem;"><strong>조회수:</strong> ${boardDetailModel.boardViews}</p>
                        </div>
                        <hr>
                        <p class="card-text">${boardDetailModel.boardContent}</p>
                    </div>
                    <div class="card-footer text-muted">
                        <div class="button-group">
                            <c:if test="${loginMember.userCode == boardDetailModel.userCode || loginMember.gradeCode == 4}">
                                <a href="${pageContext.request.contextPath}/board/${boardDetailModel.boardCode}/edit">수정</a>
                                <button onclick="deleteBoard(event)">삭제</button>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/board">목록으로</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 댓글 리스트 -->
        <div class="row mt-4">
            <div class="col-12">
                <div class="list-group">
                    <c:forEach var="comment" items="${commentList}">
                        <div class="list-group-item comment-level-${comment.level > 3 ? 3 : comment.level}">
                            <p><strong>${comment.userNickname}</strong> <small>${comment.commentDate}</small></p>
                            <p id="comment-content-${comment.commentCode}">${comment.commentContent}</p>

                            <c:if test="${loginMember.userCode == comment.userCode || loginMember.gradeCode == 4}">
                                <!-- 수정 버튼 -->
                                <button class="btn btn-warning btn-sm" onclick="showEditForm('${comment.commentCode}', '${comment.commentContent}')">수정</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteComment(${comment.commentCode})">삭제</button>
                            </c:if>
                            <!-- 답글 버튼 -->
                            <button class="btn btn-info btn-sm" onclick="showReplyForm('${comment.commentCode}')">답글</button>

                            <!-- 수정 폼 -->
                            <form id="edit-form-${comment.commentCode}" class="edit-form" action="${pageContext.request.contextPath}/board/comment/edit" method="post">
                                <input type="hidden" name="commentCode" value="${comment.commentCode}">
                                <div class="form-group">
                                    <textarea class="form-control" name="commentContent" rows="3" required>${comment.commentContent}</textarea>
                                </div>
                                <input type="hidden" name="boardCode" value="${boardCode}">
                                <button type="submit" class="btn btn-primary">수정 완료</button>
                                <button type="button" class="btn btn-secondary" onclick="hideEditForm(${comment.commentCode})">취소</button>
                            </form>

                            <!-- 답글 폼 -->
                            <form id="reply-form-${comment.commentCode}" class="reply-form" action="${pageContext.request.contextPath}/board/comment/reply" method="post">
                                <input type="hidden" name="boardCode" value="${boardCode}">
                                <input type="hidden" name="replyCode" value="${comment.commentCode}">
                                <input type="hidden" name="userCode" value="${loginMember.userCode}">
                                <div class="form-group">
                                    <textarea class="form-control" name="commentContent" rows="3" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">답글 작성</button>
                                <button type="button" class="btn btn-secondary" onclick="hideReplyForm(${comment.commentCode})">취소</button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- 댓글 작성 폼 -->
        <div class="row mt-3">
            <div class="col-12">
                <h5>댓글</h5>
                <form action="${pageContext.request.contextPath}/board/comment" method="post">
                    <input type="hidden" name="boardCode" value="${boardDetailModel.boardCode}">
                    <div class="form-group">
                        <textarea class="form-control" id="commentContent" name="commentContent" rows="3" placeholder="댓글 내용" required></textarea>
                    </div>
                    <input type="hidden" name="userCode" value="${loginMember.userCode}" />
                    <div class="button-group">
                        <button type="submit">댓글 작성</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    async function deleteBoard(event) {
        event.stopPropagation();
        if (confirm('정말 삭제하시겠습니까?')) {
            try {
                const response = await fetch(`/board/${boardCode}`, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    alert('삭제되었습니다.');
                    window.location.href = '/board?page=1';
                } else {
                    alert('삭제 중 오류가 발생했습니다.');
                }
            } catch (error) {
                alert('삭제 중 오류가 발생했습니다.');
            }
        }
    }

    async function deleteComment(commentCode) {
        if (confirm('정말 삭제하시겠습니까?')) {
            try {
                const response = await fetch(`/board/comment/` + commentCode, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    alert('삭제되었습니다.');
                    location.reload();
                } else {
                    alert('삭제 중 오류가 발생했습니다.');
                }
            } catch (error) {
                alert('삭제 중 오류가 발생했습니다.');
            }
        }
    }

    function showEditForm(commentCode, commentContent) {
        hideReplyForm(commentCode); // 답글 폼 숨기기
        const editForm = document.getElementById(`edit-form-` + commentCode);
        if (editForm) {
            editForm.style.display = 'block';
            document.getElementById(`comment-content-` + commentCode).style.display = 'none';
        }
    }

    function showReplyForm(commentCode) {
        hideEditForm(commentCode); // 수정 폼 숨기기
        const replyForm = document.getElementById(`reply-form-` + commentCode);
        if (replyForm) {
            replyForm.style.display = 'block';
        }
    }

    function hideEditForm(commentCode) {
        document.getElementById(`edit-form-` + commentCode).style.display = 'none';
        document.getElementById(`comment-content-` + commentCode).style.display = 'block';
    }

    function hideReplyForm(commentCode) {
        const replyForm = document.getElementById(`reply-form-` + commentCode);
        if (replyForm) {
            replyForm.style.display = 'none';
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
