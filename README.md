## RIOT API 연동 예제
* * *
Spring Cloud OpenFeign을 사용한 RIOT API 연동 예제




### 시작하기
***
`${RIOT_API_KEY}`에 발급받은 API 키 입력
```yaml
riot:
  api:
    url: https://kr.api.riotgames.com
    key: ${RIOT_API_KEY}
```

### API 연동하는 법
***
1. [라이엇 API 문서](https://developer.riotgames.com/apis) 에서 연동하고자 하는 API 스펙 확인
2. XXXClient 형식의 네이밍으로 `Interface`생성 ex) SummonerClient
3. DTO도 만들기, 본인은 조회용 DTO면 XXXQueryResponse 형식으로 만듬 ex) SummonerQueryResponse
4. Spring Web과 동일하게 `@GetMapping`, `@RequestParam`등의 Annotation을 사용하여 작성
```java
public interface SummonerClient {
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Optional<SummonerQueryResponse> getSummoner(@PathVariable String summonerName);
}
```
```java
public record SummonerQueryResponse(
        String id,
        String accountId,
        String puuid,
        String name,
        String profileIconId,
        Timestamp revisionDate,
        String summonerLevel
) { }
```
4. 단건 조회의 경우 Nullable하기 때문에 `Optional`로 감싸주기 
5. 테스트 시 header에 토큰이 포함되어있는지, 파라미터가 제대로 포함되는지 등을 로그로 확인
```
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] ---> GET https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%EA%B9%80%EC%84%9D%EA%B7%A0 HTTP/1.1
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] X-Riot-Token: *************
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] ---> END HTTP (0-byte body)
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] <--- HTTP/1.1 200 OK (101ms)
...
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] {"id":"****","accountId":"****","puuid":"****","name":"김석균","profileIconId":5212,"revisionDate":1689783841000,"summonerLevel":120}
DEBUG 8564 --- [           main] c.e.o.summoner.infra.SummonerClient      : [SummonerClient#getSummoner] <--- END HTTP (305-byte body)
```


### 추가로 알아두면 좋은 것
***
1. RequestInterceptor : API CALL시 공통적으로 포함되는 부분에 사용, 여기서는 `RIOT_API_KEY`가 사용
2. dismiss404 : 라이엇 API에서 데이터가 없을시 HttpStatus 404를 리턴하는데 Feign에서 기본적으로 400이상 HttpStatus는 에러로 처리함
