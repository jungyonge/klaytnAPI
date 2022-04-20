# Ground X 사전과제
- 이 프로젝트는 Klaytn 블록, 트랜잭션 조회기능입니다.

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA
    - OS: Mac
    - GIT
- Server
    - Java11
    - Spring Boot
    - JPA
    - gradle
    - mongodb


## 문제 해결 전략
1. 블록 ,트랜잭션 파싱
   1. 프로듀서, 컨슈머 1개씩 구성하여 블록, 트랜잭션 파싱
   2. 프로듀서에서 PN에 block조회 후 큐에 블록 push
   3. 컨슈머에서 큐에 있는 블록을 차례대로 빼와서 블록, 트랜잭션 document에 저장
   

## API 목록
| Method | Path                                     | Description | 
|--------|------------------------------------------|-----------------------------------------------------------------|
| GET    | /api/v1/klaytn/block                     | 블록을 조회합니다.
| GET    | /api/v1/klaytn/transaction/smartContract | 스마트 컨트랙트 트랜잭션을 조회합니다.
| GET    | /api/v1/klaytn/transaction/valueTransfer | value transfer 트랜잭션을 조회합니다.

Swagger url  
http://ec2-54-180-196-21.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui.html

## 응답코드

| CODE   | Message              | 
|--------|----------------------|
| 2_0001 | 트랜잭션이 존재하지 않습니다.     |