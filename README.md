enjoy-diving 프로젝트의 `rest api project`로 연동에 필요한 API를 구현

* 인증 : `Spring Security Oauth2` 기반으로 구성하며 인증 하며, Token은 `redis`방식을 이용한다.
* API : `Spring Web`기반에 `@RestController`를 이용한다.