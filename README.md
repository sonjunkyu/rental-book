# 도서 대여 관리 API (Demo)

간단한 도서 대여/좋아요/알림을 제공하는 Spring Boot API 프로젝트입니다.

## ERD
<img width="1762" height="586" alt="ERD" src="https://github.com/user-attachments/assets/507cf2ea-45ae-42e1-8d3d-0c7da56b9d25" />

## 주요 기능
- 회원가입/로그인 (JWT 발급)
- 도서 목록 조회 (페이지네이션)
- 도서 좋아요 등록/취소, 좋아요 수 조회
- 도서 대여/반납
- 대여/반납 시 알림 생성 및 알림 확인 처리

## 동작 흐름(요약)
1. 회원가입 → 로그인으로 `accessToken`을 발급받는다.
2. 도서 목록을 조회하고 원하는 도서를 선택한다.
3. 선택한 도서에 좋아요를 등록/취소하거나 좋아요 수를 확인한다.
4. 도서를 대여하면 대여 정보가 생성되고, 반납 시 상태가 갱신된다.
5. 대여/반납 이벤트에 대해 알림이 생성되며, 사용자는 알림을 확인 처리한다.

## 기술 스택
- Java 21, Spring Boot 3.5.8
- Spring Web, Spring Data JPA, Spring Security
- JWT
- MySQL
- Swagger(OpenAPI)

## 환경 변수
- `DB_URL`
- `DB_USER`
- `DB_PW`
- `JWT_SECRET`

## 인증
- 로그인 성공 시 `accessToken` 발급
- 보호된 API 호출 시 `Authorization: Bearer {token}` 헤더 필요
- 공개 API: `/sign-up`, `/login`, Swagger 관련 엔드포인트

## 응답 형식
모든 API는 `ApiResponse`로 응답합니다.

```json
{
  "isSuccess": true,
  "code": "BOOK_200_2",
  "message": "요청 성공 메시지",
  "result": {}
}
```

## API 목록
### 회원
- `POST /sign-up`
  - 요청: `{ "email": "...", "password": "...", "name": "...", "gender": "MALE|FEMALE" }`
  - 응답: `{ "memberId": 1, "createdAt": "..." }`
- `POST /login`
  - 요청: `{ "email": "...", "password": "..." }`
  - 응답: `{ "memberId": 1, "accessToken": "..." }`

### 도서
- `GET /books?page=1`
  - 페이지는 1부터 시작, 기본 크기 5
  - 응답: `BookPreViewListDTO`
- `POST /books/{bookId}/rent`
  - 요청: `{ "bookId": 1 }` (현재 구현은 바디의 bookId를 사용)
  - 응답: `RentInfo`
- `POST /books{bookId}/likes`
  - 요청 바디 없음, 인증 필요
  - 응답: `BookLikeResult`
- `DELETE /books/{bookId}/likes`
  - 요청 바디 없음, 인증 필요
  - 응답: `BookUnlikeResult`
- `GET /books/{bookId}/count-likes`
  - 응답: `BookLikeCountResult`

### 대여
- `PATCH /rents/{rentId}/return`
  - 요청 바디 없음, 인증 필요
  - 응답: `ReturnInfo`

### 알림
- `PATCH /{notificationId}/confirm`
  - 요청 바디 없음, 인증 필요
  - 응답: `NotificationInfo`
