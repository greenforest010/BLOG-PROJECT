# BLOP-2017
### BLOP Renewal
---
- #### 2017.4.16
  - BLOP Web Application 시작 샘플 만듬.

- #### 2017.4.17
  - Spring Java Config로 변경.
  - Calm - Web Layout 추가

- #### 2017.4.20
  - Calm - 초기 커스터마이즈

- #### 2017.4.21
  - Gentelella - Web Layout 추가(Main, Login 페이지)
  - Gentelella - 초기 커스터마이즈

- #### 2017.4.24
  - DB(MySQL Server)와 애플리케이션 연동을 위한 mysql-connector-java 추가 및 DB연결 테스트

- #### 2017.4.25
  - JDBC 추가 및 DB연결 테스트(Config Using JDBC Driver DataSource)
  - JNDI 추가 및 DB연결 테스트(Config Using JNDI Driver DataSource)
  - MyBatis 추가 및 DB연결 테스트(Config Using JNDI Driver DataSource)
  
- #### 2017.4.26
  - Security Config 추가
  - 로그인 기능 추가

- #### 2017.4.28
  - 블로그, 관리자 영역 Header, Footer 뷰로 나눔.
  - ViewResolver를 Apache Tiles 로 바꿈(Header, Footer 뷰의 관리 수월의 이유)
  
- #### 2017.4.29
  - Mybatis Mapper 테스트
  - JDBC 로깅 추가
  - CharacterEncodingFilter 추가(UTF-8)
  
- #### 2017.4.30
  - Post 추가(VO, Mapper(DAO), Service, Controller, View)
  
- #### 2017.5.4
  - Post View 편집(list, new, edit)
  - Post 생성(Create)
  - Post 전체 목록 보이기(list)
  - Csrf 토큰을 포함한  POST 요청시 CharacterEncodingFilter 추가(기존 필터 삭제)
  
- #### 2017.5.5
  - 로깅 변경(log4j -> logback)
  
- #### 2017.5.6
  - Post 편집(Update)
  
- #### 2017.5.8
  - Post 삭제(Delete)
  
- #### 2017.5.9
  - Blog Post 전체 목록 보이기(list)
  - Blog Post 읽기(read)
  
- #### 2017.5.11
  - 저자(author)의 텍스트 변환(View에 보임)
  
- #### 2017.5.17
  - 카테고리 레이아웃 생성
  - 카테고리 전체 리스트 보이기(list)
  - 카테고리 클릭 시 해당 고유 주소가 input에 보임
  - 카테고리 생성(Create)
  - 카테고리 변경(Update) -> 카테고리 이름, 고유 주소
  - 카테고리 삭제(Delete)
  
- #### 2017.5.20
  - 카테고리 이동(Move)
  
- #### 2017.5.21
  - 글 생성 시 카테고리 설정
  
- #### 2017.5.22
  - 글 리스트에 카테고리 데이터 추가
  
- #### 2017.5.24
  - 글 편집 페이지에서 카테고리 변경
  
- #### 2017.5.29
  - 카테고리가 삭제된 글은 글 리스트 페이지에서 새로 카테고리를 설정 가능
  
- #### 2017.6.3
  - 리팩토링
  
- #### 2017.6.13
  - 글에 관한 REST API 적용
  
- #### 2017.6.16
  - REST API의 응답 설정
  
- #### 2017.6.18
  - 글 편집 페이지에서 카테고리가 없는 글의 카테고리 설정이 가능하도록 변경
  - 글 생성과 편집에서 카테고리 설정과 동시에 이뤄져야할 내용의 트랜잭션 설정
  
- #### 2017.6.19
  - 블로그 메인 페이지와 관리자 글 리스트 페이지에 페이지네이션 적용
  
- #### 2017.6.21
  - 글 생성 페이지에서 취소 버튼 클릭 시 이전 페이지의 글 리스트로 이동
  - 글 편집 후 및 글 편집 페이지에서 취소 버튼 클릭 시 이전 페이지의 글 리스트로 이동
  
- #### 2017.7.2
  - 카테고리 및 글 REST API 보안 설정
  
- #### 2017.7.4
  - 글 에디터 추가

- #### 2017.7.15
  - 첨부 파일 생성
  - 첨부 파일 전체 리스트 조회
  - 선택된 첨부 파일 상세 조회
  - 첨부 파일 이름 변경
  - 첨부 파일 설명 변경
  - 첨부 파일 삭제
  - 미디어 페이지 추가

- #### 2017.7.22
  - 미디어 페이지 파일 업로드 기능 추가
  - 미디어 페이지 파일 갤러리 뷰 추가
