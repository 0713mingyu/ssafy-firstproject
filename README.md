# 프로젝트 개요

* 대상자 : 전지역 SSAFY 교육생
* 목적
  * SSAFY 교육생들에게 필요로 하는 IT 관련 채용 공고를 제공함
  * SSAFY 교육생 간 교류, 나아가 IT 직군에서의 인적 네트워크 활성화
  * 기술스택 기반 공고 제공
* 기대효과
  * **기술 스택 기반 공고 정보를 제공**함으로써 필요한 공고만을 볼 수 있는 편의성 제공
  * **싸피생 취업 현황 및 기수 간 소통의 장 제공**으로 기존 소통의 부재 문제 해결
  * **스터디를 모집 공간 제공**으로 스터디를 구하기 어려웠던 점 해결

# PAJAMA Team

| 이름              | 역할   | 개발파트 |
| ----------------- | :----- | :------: |
| 성민규 **(팀장)** | 기획   | Frontend |
| 김대래            | CTO    | Backend  |
| 김인성            | 배포   | Frontend |
| 김정원            | 테스트 | Frontend |
| 박진희            | DB     | Backend  |

# Tools

## Frontend

* 

## Backend

* Java 13.0.2 (JDK)
  * [Java SE Development Kit 13.0.2 download](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
* [Eclipse Photon (4.8)](https://www.eclipse.org/downloads/packages/release/photon/r)
  * [Eclipse IDE for Java EE Developers download](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/R/eclipse-jee-photon-R-win32-x86_64.zip)
* MySQL
  * [MySQL 워크벤치 download](https://dev.mysql.com/downloads/file/?id=492814)
  * [설치 가이드](https://dog-developers.tistory.com/20)

## Etc

* JIRA
* GIT



# README Link

* [Frontend README](./Frontend/README.md)

* [Backend README](./Backend/README.md)

* [DB README](./Database/README.md)

* [API 정의](./Backend/APIdefinition.md)

# 명세서

## Req 01 개발 설계

### 1-1 API 정의

* Sub PJT Ⅱ의 기능 구현 항목 파악
* 필요한 API 항목을 도출하고 정의(문서화)  

### 1-2 ERD 작성

* API에서 도출할 데이터 구조를 파악
* 필요한 항목을 도출하고 ERD 작성(문서화)

### 1-3 와이어 프레임 작성

* 유저에게 보여줄 페이지 개요 작성
* 필요한 항목을 도출하고 와이어 프레임 작성(문서화)
* 와이어 프레임 작성 대상
  * 홈페이지
  * 전체 공고 페이지
  * 공고 상세 페이지
  * 유저 상세 페이지
  * 관리자 페이지

## Req 02 팀 프로젝트 개발 환경 구성

### 2-1 기본 개발 환경 구성

* Git 협업 환경 설정(Member, 권한)
* Git merge 담당자 설정
* Frontend, Backend, Data 폴더 구성
* 로컬 개발 PC와 Git 프로젝트 소스  동기화

### 2-2 Frontend 개발 환경 구성

* 스켈레톤으로 다룬 Vue.js를 기준으로 모듈 설정  
* Frontend 폴더에 Frontend 모듈세팅
* 개발 환경 Readme 작성

### 2-3 Backend 개발 환경 구성

* Java Spring 프레임워크를 기준으로 모듈 설정
* Backend 폴더에 Backend 모듈세팅
* 개발 환경 Readme 작성

### 2-4 Database 개발 환경 구성

* 사용할 Database 설정
* 작성한 ERD에 맞추어 데이터 수집

## Req 03 로그인 기능

### 3-1 기본 로그인

* 로그인 페이지를 페이지 전환이 아닌 Modal Pop-up으로 구현
* 기본 로그인 형태를 이메일, 패스워드 입력으로 구현
* 이메일 형식에 맞지 않을 경우 메시지 출력
* 비밀번호가 맞지 않을 경우 메시지 출력
* Session을 활용하여 refresh버튼(F5)를 눌렀을 경우에도 로그인이 유지 될 수 있도록
* 로그인이 되었을 경우 로그아웃 마이 페이지 버튼 활성화
* 로그아웃이 되었을 경우 로그인 회원가입 버튼 활성화
* 작성한 이메일로 회원 가입 확인

### 3-2 소설 로그인

* 구글, 네이버 등 2개 이상의 옵션 제공

## Req 04 회원 가입, 수정, 탈퇴

### 4-1 회원 가입

* 회원 관리에 필요한 최소 정보(이메일, 비밀번호, 이름)입력 폼 구축
* 추가적 요소 입력(대표이미지, 기술 스택)요소 고려

### 4-2 회원 상세 정보페이지

* 로그인시 해당 유저의 상세 정보 페이지 추가
* 회원 탈퇴 기능
* 회원 정보 수정 기능
* 회원이 스크랩한 내용 확인 기능
* 회원의 기술스택에 맞추어 추천공고 확인 기능

## Req 05 회원 권한

### 5-1 권한 구분

* 회원은 관리자, 유저, 방문자로 구분
* 관리자는 회원/권한 관리, 공고 등록, 수정, 삭제 등의 권한 소유
* 유저는 공고 스크랩, 기술스택 관리, 회원 정보 수정, 탈퇴 등의 권한 소유

## Req 06 홈페이지

### 6-1 이미지 배너 구성

* 화면 가로 사이즈에 따라 텍스트 사이즈 변경
* 화면 사이즈에 맞게 메인 이미지의 크기 변경
* 화면이 깨지지 않도록 설정

### 6-2 공고 

* 공고 영역의 컴포넌트 분리
* 로그인 시 회원정보에 맞는 스택 및 회원 스크랩에 따른 공고 출력(각 5개)
  * 모바일 사이즈(xs)
  * 태블릿 이상(sm)
  * 데스크탑 이상(md)
* 모든 사용자에게 마감일 기준, 조회수 기준, 스크랩 횟수 순 공고 출력(각 5개)
  * 모바일 사이즈(xs)
  * 태블릿 이상(sm)
  * 데스크탑 이상(md)
* 더보기 버튼 구성

### 6-3 스터디

* 스터디 영역의 컴포넌트 분리

### 6-4 링크

* 링크 영역의 컴포넌트 분리

## Req 07 전체 공고 페이지(달력)

### 7-1 달력

* 공고의 시작일, 종료일에 맞추어 달력에 출력
  * 모바일 사이즈(xs)
  * 태블릿 이상(sm)
  * 데스크탑 이상(md)

### 7-2 필터

* 기술 스택 에 맞는 필터 구현
* 시작일 종료일에 맞는 필터 구현

## Req 08 공고 상세 페이지

### 8-1 기업 정보

* 기업 정보(기업 이름, 근로자수, 규모, 매출액, 주소, 홈페이지, 업종, 사업 내용 등) 출력
* SSAFY 취업인 현황 추가

### 8-2 공고 정보

* 공고 정보(직군, 인원, 직무내용, 고용형태, 근무지, 기간)을 출력
* 지원 자격 정보(전공, 자격증, 보훈, 군복무, 장애우대, 기술 스택)을 출력
* 전형절차, 기타 우대사항, 문의사항 등 출력

### 8-3 댓글 기능

* 작성된 모든 댓글 출력

* 관리자, 작성자일 경우 삭제 가능
* 다른 유저 및 방문자는 보이기만

## Req 09 유저 상세 페이지

### 9-1 회원정보 상세

* 이름, 이메일, 기술 스택 출력
* 스크랩한 공고 출력
* 회원 정보 수정 버튼 구성
* 회원 탈퇴 버튼 구성

### 9-2 회원정보 수정

* 회원정보 수정 페이지를 페이지 전환이 아닌 Modal Pop-up으로 구현
* 회원정보 수정시 비밀번호 재입력

### 9-3 스터디 정보

* 가입한 스터디 출력

## Req 10 관리자 페이지

### 10-1 공고 CRUD

* 채용공고 등록 수정 삭제 조회 기능 추가

### 10-2 회원 권한 변경

* 회원 권한을 변경 기능 추가

## Req 11 기업 정보 페이지

### 11-1 기업 정보 구조 변경

* 기업 정보 부분 컴포넌트화

## Req 11 네비게이션바

* 데스크탑 환경

## Req 12 푸터


