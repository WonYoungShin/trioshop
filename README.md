# **📖 TrioShop 쇼핑몰 README**

- 배포 URL : https://trioshop.site
- Admin Test ID/PW : admintest // admintest
- User Test ID/PW : usertest // usertest

## **프로젝트 소개**

- 이번 프로젝트는 남성 의류 쇼핑몰을 기초로 하여 단계적으로 발전시켜 나가는 것을 목적으로 합니다.
- 초기 단계에서는 기본적인 쇼핑몰 기능을 구현하여 사용자에게 편리하고 만족스러운 쇼핑 경험을 제공하는 것에 중점을 두고 있습니다. 점진적으로 새로운 기능을 추가하고 최신 기술을 적용하여 쇼핑몰의 완성도를 높여갈 계획입니다. 이를 통해 포트폴리오의 다양성과 깊이를 더하고, 프로젝트 관리 및 기술적 역량을 강화하고자 합니다.

## 팀원구성

|  | 김원형 | 신원영 | 전민주 |
| --- | --- | --- | --- |
| E-Mail | https://github.com/kkgg0522 | https://github.com/WonYoungShin | https://github.com/jeonminju23 |
| GitHub | https://github.com/kkgg0522 | http://github.com/WonYoungShin |  |
| Blog | https://tan-sog.tistory.com/ | https://www.notion.so/27a1fef33a064c6bb3a26ce6de2e30d4?pvs=21 | https://mom11230.tistory.com/ |

## **1. 개발 환경**

- IDEA : intellij IDEA 2023
- Front : HTML, JSP, ASYNC, JAVASCRIPT
- Back-end : Spring, spring boot, Redis - JDK17 / BOOT 3.2.5
- 버전 및 이슈관리 : Github, Github Project
- 협업 툴 : Google Drive , Notion
- 서비스 배포 환경 : AWS, Docker
- 디자인 : [Figma](https://www.figma.com/file/fAisC2pEKzxTOzet9CfqML/README(oh-my-code)?node-id=39%3A1814)
- [커밋 컨벤션](https://github.com/likelion-project-README/README/wiki/%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B2%A4%EC%85%98)
- [코드 컨벤션](https://github.com/likelion-project-README/README/wiki/%EC%BD%94%EB%93%9C-%EC%BB%A8%EB%B2%A4%EC%85%98)
- [스프라이트](https://github.com/likelion-project-README/README/wiki/%EC%8A%A4%ED%94%84%EB%9D%BC%EC%9D%B4%ED%8A%B8)

## **2. 채택한 개발 기술**

**spring, spring boot**

- Spring Security
- JWT + Redis
- KAKAO Login API
- Toss Payment API

## **3.** 개발 기간 및 작업 관리 ****

- 개발 기간 : **2024-05-13 ~ 2024-06-18**
- [**WBS (Work Breakdown Structure)**](https://docs.google.com/spreadsheets/d/1m35sK-1xVs-JSpqdoHF4DRyb5vCe_IZI_XXMazHGumk/edit?usp=drive_link)

## 4. ERD

![shopping_mall.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/9cdb4443-f6d3-4f09-a5f8-095cbced043a/shopping_mall.png)

ERD cloud : [LINK](https://www.erdcloud.com/d/ABv9hDDr684A3Fbdc)

## 5. 디렉토리 **구조**

```bash
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─trioshop
    │  │          ├─controller
    │  │          │  ├─admin
    │  │          │  ├─board
    │  │          │  ├─item
    │  │          │  └─user
    │  │          ├─deprecated
    │  │          │  ├─filter
    │  │          │  ├─hashing
    │  │          │  └─interceptor
    │  │          ├─exception
    │  │          │  └─advice
    │  │          ├─filter
    │  │          ├─interceptor
    │  │          ├─model
    │  │          │  └─dto
    │  │          │      ├─admin
    │  │          │      ├─board
    │  │          │      ├─item
    │  │          │      ├─payment
    │  │          │      ├─popup
    │  │          │      └─user
    │  │          ├─repository
    │  │          │  ├─dao
    │  │          │  │  ├─admin
    │  │          │  │  ├─borad
    │  │          │  │  ├─item
    │  │          │  │  └─user
    │  │          │  ├─mybatis
    │  │          │  └─redis
    │  │          ├─service
    │  │          │  ├─admin
    │  │          │  ├─board
    │  │          │  ├─item
    │  │          │  ├─payment
    │  │          │  └─user
    │  │          └─utils
    │  │              ├─business
    │  │              ├─handler
    │  │              └─service
    │  ├─resources
    │  │  ├─mybatis
    │  │  └─static
    │  └─webapp
    │      ├─css
    │      ├─images
    │      └─WEB-INF
    │          └─views
    │              ├─admin
    │              │  └─sales
    │              ├─board
    │              ├─error
    │              ├─etc
    │              ├─payment
    │              └─user
    │                  ├─itemInfo
    │                  └─userInfo
```

---

## 6. 페이지별 기능

**[초기화면]**

- 서비스 접속 초기화면으로 대쉬보드와 상품목록이 나타납니다.
- 회원 : index 페이지

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/63a809db-7066-4d8d-83f8-d38d37013d94/Untitled.png)

- 어드민 : admin 홈 화면

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/21b87222-bccc-4f89-a561-2eb3782424ee/Untitled.png)

---

**[회원가입]**

회원가입

- 전화번호 입력(01012345678) → 실시간 자동 변환(010-1234-1234)
- 작성이 완료된 후, 가입하기 버튼을 클릭하게 되면 아이디 유효성 검사가 실행됩니다.

---

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/25b9bfcb-68bd-4d75-b9b3-bbcda9a7985d/Untitled.png)

![회원가입 1.JPG](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/e8af84c6-cb1c-4c2c-9b14-40c47942a04b/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85_1.jpg)

---

**[로그인]**

![로그인1.JPG](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/bf96a77a-6c21-4251-a672-ae84846613c1/%EB%A1%9C%EA%B7%B8%EC%9D%B81.jpg)

![카카오로그인 1.JPG](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/eafb348a-5806-4c63-a7fe-90694d735f97/%EC%B9%B4%EC%B9%B4%EC%98%A4%EB%A1%9C%EA%B7%B8%EC%9D%B8_1.jpg)

- 아이디와 비밀번호를 입력 후 로그인 버튼을 누르게되면 유효성 검사가 진행되고
- 로그인에 성공하면 AccessToken과 RefreshToken을 발급합니다.

---

**[로그아웃]**

- 상단의 헤더의 로그아웃 버튼을 클릭하면 로그아웃이 됩니다.
- 로그아웃시 토큰 값을 Redis 블랙리스트에 추가하고 초기화면으로 이동합니다.

---

**[헤더]**

- 유저헤더
    
    ![해더.JPG](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/e1e949e0-e9fb-4ec8-bd26-87cce3b4b8f6/%ED%95%B4%EB%8D%94.jpg)
    
- 관리자헤더
    
    ![해더 2.JPG](https://prod-files-secure.s3.us-west-2.amazonaws.com/9fe6ea9f-e901-47cf-9865-d8628bad1c0c/8e349b89-8299-4901-af26-0a39db57679f/%ED%95%B4%EB%8D%94_2.jpg)
    

---

## 7. **프로젝트 후기**

- 김원형
    - 이번 프로젝트에서 저는 어드민, 게시판 풀스택과 유저파트 일부분의 백엔드와 배포 등을 담당했습니다. 어드민 관리, 게시판의 기능에서는 배운 것을 실제로 적용해보는 것을 중점으로 기능을 구현하였습니다. 이를 통해 기본적인 코딩 스킬을 늘리고 MVC 구조에 대해 더 자세히 알게 되었습니다. 도전적인 기능들 중에서 특히, 시큐리티를 적용하는 것과 인증방식을 JWT를 바꾸는 것이 가장 도전적 이었는데 이를 구현하는 과정에서 보안에 대해 많은 공부를 하게 되었고, 이를 통해 안전한 웹 애플리케이션 개발의 중요성을 실감하였습니다.
    이러한 프로젝트를 통해 백엔드 개발자로서의 능력을 한층 더 향상시킬 수 있었고 팀원과의 소통을 중시하며 개발상황 공유, 공통 기능 구현 등의 과정들을 통해 팀원들과의 협업 능력을 키울 수 있었습니다.
- 신원영
    - 이번 프로젝트에서 저는 상품 페이지, 주문, 장바구니, 회원가입 및 설계를 담당했습니다.
    상품 페이지를 구현하면서 그동안 배운 것들을 실제로 적용하는 과정에서 많은 것을 배울 수 있었습니다. 특히 토스와의 연동은 제 첫 API 연동이었기 때문에 초기에는 많은 실수와 문제가 있었습니다. API 키, 데이터 형식의 불일치, 인증 과정의 문제 등 다양한 문제를 겪었습니다. 하지만 이러한 문제들을 하나씩 해결해 나가면서 API 연동에 대한 이해도가 높아졌습니다.
    이후 이 과정에서 얻은 지식과 경험은 앞으로 다른 API 연동 작업에서도 큰 도움이 될 것이라 생각합니다.
    또한, 이번 프로젝트를 통해 문제 해결 능력과 디버깅 스킬을 향상시킬 수 있었고, 팀원들과의 협업을 통해 더 나은 결과를 도출할 수 있었습니다.
- 전민주
    - 이번 프로젝트에서는 CSS 수정 및 프론트엔드 수정을 담당했으며, 유저 파트를 프로토타입으로만 구현해 아쉬움이 컸습니다.그러나 팀원의 도움 덕분에 체계적으로 코드를 작성하는 방법을 깨닫고 큰 성장을 이룰 수 있었습니다. 특히 팀원들은 코드 리뷰와 피드백을 통해 코드의 구조와 유지보수성을 개선하는 데 많은 조언을 주었고, 이를 통해 더 효율적이고 깔끔한 코드를 작성할 수 있었습니다. 다음 프로젝트에서는 이러한 경험을 바탕으로 사전에 철저히 계획하고, 더욱 완성도 높은 결과물을 만들기 위해 노력하겠습니다. 이번 프로젝트에서 얻은 경험과 배움을 통해 앞으로 더욱 발전된 모습을 보여드리겠습니다.
