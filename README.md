# l1ght-up-back
웹 인공지능 프로젝트 백엔드 스타터 리포지토리

## 백엔드
| 요구사항 및 설계, 구현: 강수아

1. 회원가입
    - 회원 도메인 필요
    - 유저의 특성(시각 장애)를 고려 → 게임에서 주로 사용하는 게스트 로그인 by UUID 부
        
2. 이미지 업로드 관련
    - 이미지, 캡션 저장할 이미지 도메인 필요
    - 이미지, 캡션 CRUD API 필요
        - 이미지 C 과정서 캡션 C도 진행하기에 captionApi의 C 로직 폐쇄
        - 이미지 U 과정 유저 입장에서 필요하지 않음 → U 로직 폐쇄
3. 지도 api 관련 
   -  핀에다가 위험요소를 저장하는 방식 & 실시간 위치
   -  도보 API를 지원하는 TMap APi 선정
    → **길찾기 했을때 경로에 핀이 존재하면 위험요소 알려주기 (가는 경로에 따라서 실시간) 사용자의 몇m 거리 이내 반경에 들어오는 위험요소만 핀으로 표시 (실시간 몇 m 반경 → api 확인 완료)** 
        - 필요 로직 (BE)
            - 핀에다가 해당 위치에 저장된 위험 요소 가져와 저장, 매핑하는 로직 
            - DB로직
                - table alter하는 로직
                - 트랜잭션의 특성(원자성) 고려할 것
4. ERD 
    - 모든 Image 테이블에 originalCaption(String), dangerfact(int, 요소1~5) 존재
    - 클라이언트가 연결하는 Service Table을 switching으로 새로운 테이블 상태 유지하도록 함
      → 클라이언트의 사용 도중 갱신되지 않도록 (Image ↔ ImageAlter)
    - 구현
        - image or image alter api를 통해 image가 create 될 때, image logging api에도 함께 접속해 timestamp와 함께 insert
        - switching 될 때마다 image logging table과 동기화 (동기화 과정은 사용자에게 노출되지 않아야 하므로 client에게 제공되지 않을 때 동기화)
          
    - Member
    - Image (=Service1)
        - idx, savedPath, gps, memberIdx, Field1~5(이하 dangerfact), originalCaption
    - ImageLogging
        - updateTime 를 TimeStamp 형식으로 자동 컬럼 추가하여 최근 데이터가 갱신
        - imageLogging post Api는 삭제하고 image, imagealter service 마지막에 image logging table에 해당 dto만 insert
            - 구현: `ImageAlterService` , `ImageService` 의 `save()` 의 `imageLoggingApi.imageCreate()` 를 통함
            - Dto를 통함
                - 이를 위해서 Image, ImageAlter 엔티티에 toLoggingDto method를 정의함
    - ImageAlter (=Service2)
    - Gps
  
### future-work
- 엔티티 간 결합도에 대한 고민
- 유효성 검사
- 예외 처리
