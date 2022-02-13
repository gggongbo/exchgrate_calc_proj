# 환율계산 웹페이지 개발
---

## 주요 기능
* 미국 통화 -> 한국,일본,필리핀 통화 환율 계산

## 개발환경 및 실행방법
### * 개발환경
* Framework : Spring Framework 4.3.25
* Program Language : Java(OpenJDK 1.8.0)
* Build Tool : Maven 2.5.1
* Tomcat : 9.0.58
* IDE : Eclipse Photon
* Dependencies : junit 4.12, lombok 1.18.0, log4j 1.2.17, jackson-core 2.9.9, jackson-databind 2.9.9.2, json-simple 1.1.1

### * 실행방법
1. master 브랜치 소스 clone
2. Server Project 생성(Tomcat v9.0) 후 프로젝트 add
3. application.properties의 exchgrate.calc.access.key에 https://currencylayer.com/의 api key입력한 후 실행
(첫 실행 완료 후 create -> validate로 변경)
4. IDE에서 생성한 Server Project 실행하여 접속

## API 정보
No|Method|URI|Parameter|Return|Description
---|---|---|---|---|---|
1|GET|/exchgrateCalc/get|{countryCode:"수취국가코드", currencyAmount:"송금액"}|{exchgRate:"환율", getCurrencyAmount:"수취금액"}|환율 계산(USD -> KRW,JPY,PHP)
