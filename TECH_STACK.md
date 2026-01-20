# 기술 스택 (Technology Stack)

## 개요
도서 대여 관리 시스템의 기술 스택 문서입니다. 본 프로젝트는 Spring Boot 기반의 RESTful API 서버로 구현되었습니다.

---

## 1. 백엔드 프레임워크 및 언어

### Java 21
- **역할**: 애플리케이션 개발 언어
- **버전**: Java 21 (LTS)
- **특징**: 최신 LTS 버전으로 성능 향상 및 새로운 언어 기능 지원

### Spring Boot 3.5.8
- **역할**: 애플리케이션 프레임워크
- **주요 기능**:
  - 의존성 자동 구성
  - 내장 웹 서버 (Tomcat)
  - 프로덕션 준비 기능
  - 간편한 설정 및 관리

### Spring Framework 주요 모듈

#### Spring Web
- **역할**: RESTful API 구현
- **기능**:
  - MVC 패턴 지원
  - HTTP 요청/응답 처리
  - JSON 직렬화/역직렬화

#### Spring Data JPA
- **역할**: 데이터 접근 계층 구현
- **기능**:
  - ORM (Object-Relational Mapping)
  - Repository 패턴 지원
  - CRUD 자동 구현
  - 쿼리 메서드 자동 생성

---

## 2. 데이터베이스

### MySQL
- **역할**: 관계형 데이터베이스
- **설정**:
  - JDBC Driver: `com.mysql.cj.jdbc.Driver`
  - Dialect: `org.hibernate.dialect.MySQLDialect`
  - DDL 전략: `update` (개발 환경)

### Hibernate
- **역할**: JPA 구현체
- **기능**:
  - 엔티티 관리
  - SQL 자동 생성
  - 쿼리 최적화
  - 포맷팅된 SQL 로깅 (`show-sql: true`)

---

## 3. 보안 및 인증

### Spring Security
- **역할**: 애플리케이션 보안 프레임워크
- **기능**:
  - 인증/인가 처리
  - 보안 필터 체인 관리
  - 비밀번호 암호화

### JWT (JSON Web Token)
- **라이브러리**: `io.jsonwebtoken:jjwt` (v0.12.3)
- **구성요소**:
  - `jjwt-api`: JWT API
  - `jjwt-impl`: JWT 구현체
  - `jjwt-jackson`: JSON 직렬화
- **토큰 전략**:
  - **Access Token**: 30분 만료 (1,800,000ms)
  - **Refresh Token**: 14일 만료 (1,209,600,000ms)
- **기능**:
  - 사용자 인증 정보 관리
  - 무상태(stateless) 인증
  - 토큰 기반 세션 관리

### OAuth 2.0
- **모듈**: `spring-boot-starter-oauth2-client`
- **소셜 로그인 제공자**: Kakao
- **설정**:
  - Authorization URI: `https://kauth.kakao.com/oauth/authorize`
  - Token URI: `https://kauth.kakao.com/oauth/token`
  - User Info URI: `https://kapi.kakao.com/v2/user/me`
  - 요청 Scope: `profile_nickname`, `account_email`
- **인증 방식**: Authorization Code Grant

---

## 4. 캐싱 및 분산 시스템

### Redis
- **역할**: 인메모리 데이터 스토어
- **모듈**: `spring-boot-starter-data-redis`
- **설정**:
  - Host: `localhost`
  - Port: `6379`
- **사용 사례**:
  - Refresh Token 저장
  - 세션 관리
  - 분산 락 구현

### Redisson
- **라이브러리**: `redisson-spring-boot-starter` (v3.45.0)
- **역할**: Redis 기반 분산 락 구현
- **기능**:
  - 동시성 제어
  - 분산 환경에서 데이터 일관성 보장
  - AOP 기반 분산 락 (`@DistributedLock`)
- **적용**: 도서 대여 시 동시성 문제 해결

---

## 5. 쿼리 및 검색

### QueryDSL
- **라이브러리**: `io.github.openfeign.querydsl` (v7.0)
- **구성요소**:
  - `querydsl-jpa`: JPA 통합
  - `querydsl-core`: 핵심 기능
  - `querydsl-apt`: 코드 생성 (APT)
- **기능**:
  - 타입 안전 쿼리 작성
  - 동적 쿼리 생성
  - 복잡한 조회 로직 구현
- **코드 생성 경로**: `build/generated/querydsl`
- **적용**: 도서 목록 조회 키워드 검색 기능

---

## 6. API 문서화

### Swagger (SpringDoc OpenAPI)
- **라이브러리**:
  - `springdoc-openapi-starter-webmvc-ui` (v2.8.13)
  - `springdoc-openapi-starter-webmvc-api` (v2.8.13)
- **기능**:
  - 자동 API 문서 생성
  - Swagger UI 제공
  - OpenAPI 3.0 스펙 지원
  - 인터랙티브 API 테스트

---

## 7. 유틸리티 및 개발 도구

### Lombok
- **역할**: 보일러플레이트 코드 자동 생성
- **주요 기능**:
  - `@Getter`, `@Setter`: Getter/Setter 자동 생성
  - `@Builder`: 빌더 패턴 구현
  - `@NoArgsConstructor`, `@AllArgsConstructor`: 생성자 자동 생성
  - `@Data`: 종합 어노테이션
  - `@Slf4j`: 로깅

### Bean Validation
- **모듈**: `spring-boot-starter-validation`
- **기능**:
  - 요청 데이터 검증
  - 어노테이션 기반 유효성 검사
  - 커스텀 검증 로직 구현 (`@ValidPage`)

### Configuration Processor
- **모듈**: `spring-boot-configuration-processor`
- **기능**:
  - 설정 속성 메타데이터 생성
  - IDE 자동완성 지원
  - JWT 설정 등 커스텀 프로퍼티 관리

---

## 8. 테스팅

### JUnit Platform
- **역할**: 테스트 프레임워크
- **모듈**:
  - `spring-boot-starter-test`: Spring Boot 테스트 지원
  - `junit-platform-launcher`: JUnit 5 런처
  - `spring-security-test`: Spring Security 테스트 지원

---

## 9. 빌드 도구

### Gradle
- **역할**: 빌드 자동화 도구
- **주요 플러그인**:
  - `java`: Java 프로젝트 지원
  - `org.springframework.boot`: Spring Boot 빌드
  - `io.spring.dependency-management`: 의존성 관리
- **빌드 스크립트**: Groovy DSL (`build.gradle`)

---

## 10. 아키텍처 패턴 및 설계

### 레이어드 아키텍처
- **Controller**: HTTP 요청/응답 처리
- **Service**: 비즈니스 로직
  - `CommandService`: 쓰기 작업 (CUD)
  - `QueryService`: 읽기 작업 (R)
- **Repository**: 데이터 접근
- **Entity**: 도메인 모델

### 도메인 주도 설계 (DDD)
- **도메인별 패키지 구성**:
  - `book`: 도서 관리
  - `member`: 회원 관리
  - `rent`: 대여 관리
  - `notification`: 알림 관리
- **각 도메인 구조**:
  - `controller`: API 엔드포인트
  - `service`: 비즈니스 로직
  - `repository`: 데이터 접근
  - `entity`: 엔티티
  - `dto`: 데이터 전송 객체
  - `converter`: DTO-Entity 변환
  - `exception`: 예외 처리

### AOP (Aspect-Oriented Programming)
- **분산 락 AOP**: `@DistributedLock` 어노테이션을 통한 선언적 분산 락 구현
- **위치**: `global.aop.DistributedLockAop`

### 예외 처리 전략
- **도메인별 커스텀 예외**: `BookException`, `MemberException`, `RentException`, `NotificationException`
- **에러 코드 관리**: `ErrorCode`, `SuccessCode` enum 활용
- **통일된 응답 형식**: `ApiResponse` 래퍼 클래스

---

## 11. 주요 기술적 특징

### 1. CQRS 패턴 적용
- **Command Service**: 데이터 변경 작업
- **Query Service**: 데이터 조회 작업
- **장점**: 읽기/쓰기 최적화, 명확한 책임 분리

### 2. 분산 락을 통한 동시성 제어
- **구현**: Redisson + AOP
- **적용 시나리오**: 도서 대여 시 재고 차감
- **장점**: 멀티 인스턴스 환경에서 데이터 일관성 보장

### 3. 토큰 기반 무상태 인증
- **Access/Refresh Token 분리**
- **Redis를 통한 Refresh Token 관리**
- **장점**: 확장성, 세션 서버 불필요

### 4. 소셜 로그인 통합
- **Kakao OAuth 2.0 연동**
- **사용자 편의성 향상**

### 5. API 문서 자동화
- **Swagger UI를 통한 실시간 API 문서**
- **개발자 경험 향상**

---

## 12. 개발 환경 설정

### 필수 환경 변수
```bash
# 데이터베이스
DB_URL=jdbc:mysql://localhost:3306/rental_book
DB_USER=root
DB_PW=password

# JWT
JWT_SECRET=your-secret-key

# Kakao OAuth
REST_API_KEY=kakao-rest-api-key
CLIENT_SECRET_KEY=kakao-client-secret
```

### 실행 환경
- **Java**: 21+
- **MySQL**: 8.0+
- **Redis**: 6.0+
- **Gradle**: 8.0+

---

## 13. 의존성 버전 요약

| 기술 | 버전 |
|------|------|
| Java | 21 |
| Spring Boot | 3.5.8 |
| MySQL Connector | Latest (runtime) |
| Lombok | Latest (compile) |
| SpringDoc OpenAPI | 2.8.13 |
| JWT (jjwt) | 0.12.3 |
| Redisson | 3.45.0 |
| QueryDSL (OpenFeign) | 7.0 |

---

## 14. 향후 고려사항

### 가능한 기술 확장
- **모니터링**: Spring Boot Actuator, Prometheus, Grafana
- **로깅**: ELK Stack (Elasticsearch, Logstash, Kibana)
- **메시징**: Kafka, RabbitMQ (이벤트 기반 아키텍처)
- **컨테이너화**: Docker, Kubernetes
- **CI/CD**: GitHub Actions, Jenkins
- **테스트**: Testcontainers (통합 테스트)

---

## 참고 문서
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
- [Redisson Documentation](https://github.com/redisson/redisson)
- [QueryDSL Documentation](http://querydsl.com/static/querydsl/latest/reference/html/)
- [SpringDoc OpenAPI Documentation](https://springdoc.org/)
