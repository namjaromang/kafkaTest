# push-api

## 테스트 환경 가이드
### docker 모듈들
* push-api 모듈 기동(DB포함), 8200 port
* push-db 기동, 3310 port

## graphql 정보
### 구성
* spring boot graphql starter 로 시작
### 테스트 client
* 서버에 포함된 Embeded 클라이언트 접속 ```http://localhost:8200/altair```
* 직접 설치 ```brew cask install altair-graphql-client```
### 참조
* https://www.baeldung.com/spring-graphql
* https://github.com/graphql-java-kickstart/graphql-spring-boot
