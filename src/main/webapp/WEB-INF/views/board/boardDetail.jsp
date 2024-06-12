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
            padding-top: 100px;
            margin-left: 100px;
            width: calc(100% - 250px);
        }
        .reply-form, .edit-form {
            display: none;
        }
    </style>
</head>
<body>
<div class="d-flex">
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h2 style="text-align: center">${boardDetailModel.boardTitle}</h2>
                        </div>
                        <div class="card-body">
                            <p class="card-text"><strong>작성자:</strong> ${boardDetailModel.userName}</p>
                            <p class="card-text"><strong>작성일자:</strong> ${boardDetailModel.boardDate}</p>
                            <p class="card-text"><strong>카테고리:</strong> ${boardDetailModel.categoryName}</p>
                            <p class="card-text"><strong>조회수:</strong> ${boardDetailModel.boardViews}</p>
                            <hr>
                            <p class="card-text">${boardDetailModel.boardContent}</p>
                        </div>
                        <div class="card-footer text-muted d-flex justify-content-between">
                            <c:if test="${loginMember.userCode == boardDetailModel.userCode}">
                                <a href="${pageContext.request.contextPath}/board/${boardDetailModel.boardCode}/edit" class="btn btn-warning">수정</a>
                                <button onclick="deleteBoard(event)" class="btn btn-danger">삭제</button>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/board" class="btn btn-secondary">목록으로</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <h4>댓글 작성</h4>
                    <form action="${pageContext.request.contextPath}/board/comment" method="post">
                        <input type="hidden" name="boardCode" value="${boardDetailModel.boardCode}">
                        <div class="form-group">
                            <label for="commentContent">댓글 내용</label>
                            <textarea class="form-control" id="commentContent" name="commentContent" rows="3" required></textarea>
                        </div>
                        <input type="hidden" name="userCode" value="${loginMember.userCode}" />
                        <button type="submit" class="btn btn-primary">댓글 작성</button>
                    </form>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-12">
                    <h3>댓글</h3>
                    <div class="list-group">
                        <c:forEach var="comment" items="${commentList}">
                            <div class="list-group-item">
                                <p><strong>${comment.userName}</strong> <small>${comment.commentDate}</small></p>
                                <p id="comment-content-${comment.commentCode}">${comment.commentContent}</p>

                                <c:if test="${loginMember.userCode == comment.userCode}">
                                    <!-- 수정 버튼 -->
                                    <button class="btn btn-warning btn-sm" onclick="showEditForm('${comment.commentCode}', '${comment.commentContent}')">수정</button>
                                    <button class="btn btn-danger btn-sm" onclick="deleteComment(${comment.commentCode})">삭제</button>
                                </c:if>
                                <!-- 답글 버튼 -->
                                <button class="btn btn-info btn-sm" onclick="showReplyForm('${comment.commentCode}')">답글</button>

                                <!-- 수정 폼 -->
                                <form id="edit-form-${comment.commentCode}" class="edit-form" action="${pageContext.request.contextPath}/comment/edit" method="post">
                                    <input type="hidden" name="commentCode" value="${comment.commentCode}">
                                    <div class="form-group">
                                        <textarea class="form-control" name="commentContent" rows="3" required>${comment.commentContent}</textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary">수정 완료</button>
                                    <button type="button" class="btn btn-secondary" onclick="hideEditForm(${comment.commentCode})">취소</button>
                                </form>

                                <!-- 답글 폼 -->
                                <form id="reply-form-${comment.commentCode}" class="reply-form" action="${pageContext.request.contextPath}/comment/reply" method="post">
                                    <input type="hidden" name="parentCommentCode" value="${comment.commentCode}">
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
                    window.location.href = '/board';
                } else {
                    alert('삭제 중 오류가 발생했습니다.');
                }
            } catch (error) {
                alert('삭제 중 오류가 발생했습니다.');
            }
        }
    }

    function showEditForm(commentCode, commentContent) {
        const editForm = document.getElementById(`edit-form-`+commentCode);
        if (editForm) {
            editForm.style.display = 'block';
            document.getElementById(`comment-content-`+commentCode).style.display = 'none';
        }
    }

    function showReplyForm(commentCode) {
        const replyForm = document.getElementById(`reply-form-`+commentCode);
        if (replyForm) {
            replyForm.style.display = 'block';
        }
    }
    function hideEditForm(commentCode) {
        document.getElementById(`edit-form-`+commentCode).style.display = 'none';
        document.getElementById(`comment-content-`+commentCode).style.display = 'block';
    }

    function hideReplyForm(commentCode) {
        const replyForm = document.getElementById(`reply-form-`+commentCode);
        if (replyForm) {
            replyForm.style.display = 'none';
        }
    }

    async function deleteComment(commentCode) {
        if (confirm('정말 삭제하시겠습니까?')) {
            try {
                const response = await fetch(`/comment/`+commentCode, {
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
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
