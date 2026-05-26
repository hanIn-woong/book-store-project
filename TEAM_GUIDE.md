<<<<<<< HEAD
# TEAM GUIDE

## 1. Package Import Rules
- Use `com.bookstore` as the base package.
- Respect domain boundaries:
    - `member`: Login, Signup, MyPage
    - `content`: Main list, Search, Filtering
    - `detail`: Book detail, Exception handling
    - `admin`: Registration, Edit, Delete, Statistics

## 2. GitHub Rules
- Do not commit local configuration files.
- Ensure all tests pass before pushing.
- Use meaningful commit messages.
=======
# 📚 Book Store Project - 팀 협업 가이드

> Spring Boot 기반 도서 구매 사이트 팀 프로젝트
> 
> **프로젝트 기간**: 2주 | **발표일**: 2026년 6월 8일

---

## 🎯 프로젝트 개요

### 팀 구성 (6명)
|          역할           |     담당자     |             구현 영역                 |
|-------------------------|--------------- |---------------------------------------|
|    **Member (회원)**    |     김정효     | 사용자 로그인, 회원가입, 마이페이지    |
| **Content List (목록)** | 신윤섭, 박선호 | 전체 목록 출력, 카테고리 필터링, 검색  |
|    **Detail (상세)**    | 박윤서, 유종호 | 상세 페이지, 고유 식별자 처리          |
|    **Admin (관리자)**   |     한인웅     | 데이터 CUD, 통계, 공통 아키텍처        |

### 기술 스택
- **Framework**: Spring Boot
- **IDE**: IntelliJ IDEA
- **VCS**: Git & GitHub
- **Database**: ArrayList (공유 DB)

---

## 🚀 시작하기

### 1단계: Repository 클론 (처음 1회만)

#### Windows/Mac/Linux 터미널에서 실행:
```bash
git clone https://github.com/hanIn-woong/book-store-project.git
cd book-store-project
```

**또는 IntelliJ에서:**
1. IntelliJ 실행
2. **File** → **New** → **Project from Version Control**
3. **URL** 입력: `https://github.com/hanIn-woong/book-store-project.git`
4. **Clone** 클릭

---

### 2단계: develop 브랜치로 전환

```bash
git checkout develop
```

> **develop 브랜치란?**
> - 모든 팀원의 코드가 모이는 "통합 브랜치"
> - main은 완성된 최종 코드만 들어가는 브랜치
> - 우리는 develop에서 작업합니다

---

### 3단계: 자신의 담당 영역 브랜치 생성

**자신의 역할에 맞는 명령어를 실행하세요:**

#### 👤 Member 담당자
```bash
git checkout -b feature/member
```

#### 📋 Content List 담당자 (2명 중 1명)
```bash
git checkout -b feature/content-list-1
# 또는 feature/content-list-2 (2명이 같은 영역을 담당하면 구분)
```

#### 🔍 Detail 담당자 (2명 중 1명)
```bash
git checkout -b feature/detail-1
# 또는 feature/detail-2
```

#### ⚙️ Admin 담당자 (관리자)
```bash
git checkout -b feature/admin
```

이제 **자신의 브랜치에서만 작업**합니다!

---

## 📝 코드 작업 및 업로드 흐름

### 기본 흐름

```
1️⃣ 코드 수정
   ↓
2️⃣ 변경 사항 확인
   ↓
3️⃣ 커밋 (저장)
   ↓
4️⃣ 푸시 (업로드)
```

---

## 🛠️ IntelliJ에서 작업하기 (권장)

### 방법 1: IntelliJ GUI 사용 (가장 쉬움)

#### ① 코드 작성 및 저장
- IntelliJ에서 코드 수정 후 **Ctrl+S** (또는 **Cmd+S** Mac)로 저장

#### ② 변경 사항 확인
1. 좌측 **"Git"** 탭 클릭
2. **"Commit"** 클릭 (또는 **Ctrl+K**)
3. 변경된 파일 목록 확인

#### ③ 커밋 메시지 작성
```
제목: 한 줄로 간단하게 작성 (50자 이내)
예시:
- "회원 로그인 기능 구현"
- "책 목록 필터링 기능 추가"
- "상세 페이지 NPE 방어 로직 추가"
- "관리자 통계 기능 구현"
```

#### ④ 커밋 실행
- 좌측 파일 선택 (또는 "All Files" 선택)
- **"Commit"** 버튼 클릭

#### ⑤ 푸시 (업로드)
- **VCS** → **Git** → **Push** 클릭
- 또는 **Ctrl+Shift+K**
- 팝업에서 **"Push"** 확인

---

### 방법 2: 터미널 사용

```bash
# 1️⃣ 현재 브랜치 확인
git branch

# 2️⃣ 모든 변경 사항을 스테이징 (준비)
git add .

# 3️⃣ 커밋 (저장) - 따옴표 안에 메시지 입력
git commit -m "회원 로그인 기능 구현"

# 4️⃣ 푸시 (업로드) - 자신의 브랜치로 업로드
git push origin feature/member
# 또는 당신의 브랜치명: git push origin feature/content-list-1
```

---

## ⚠️ 주의사항

### ✅ 꼭 해야 할 것

```bash
# 주 1-2회: 최신 코드 가져오기
git checkout develop
git pull origin develop
git checkout feature/member  # 자신의 브랜치로 돌아가기
```

> **왜?** 다른 팀원들의 최신 코드를 반영하면 나중에 충돌(Conflict)을 줄일 수 있습니다.

### ❌ 절대 하면 안 될 것

1. **develop과 main 브랜치에 직접 Push하지 말 것**
   ```bash
   git checkout develop
   git push  # ❌ 이러면 안 됨!
   ```

2. **다른 팀원의 브랜치에 접근하지 말 것**
   ```bash
   git checkout feature/content-list  # ❌ 다른 팀원 브랜치
   git push  # ❌ 절대 금지!
   ```

3. **main 브랜치 수정하지 말 것**
   - main은 최종 완성 코드만 들어가는 브랜치
   - 관리자만 관리합니다

---

## 🔀 Pull Request (PR) - 코드 통합

### 당신의 작업이 완료되면?

**GitHub 웹에서** (또는 IntelliJ에서):

1. Repository 페이지 방문
2. **"Pull requests"** 탭 클릭
3. **"New pull request"** 버튼 클릭

#### 설정 방법:
```
base: develop (왼쪽)  ← 병합될 대상 (항상 develop)
compare: feature/member (오른쪽)  ← 당신의 브랜치
```

4. **"Create pull request"** 클릭
5. 제목과 설명 입력:
   ```
   제목: Member - 회원 로그인 및 가입 기능 구현
   
   설명:
   - 로그인 기능
   - 회원가입 기능
   - 마이페이지 기능
   - 타임리프 폼 바인딩(th:object, th:field) 적용
   ```
6. **"Create pull request"** 클릭

### Admin(관리자)이 할 일:
- 팀원의 코드 검토
- 문제 없으면 **"Merge"** 실행
- develop 브랜치에 통합됨

---

## 🚨 만약 실수했다면?

### 상황 1: 잘못된 브랜치에 Push했다면?

```bash
# 현재 브랜치 확인
git branch

# 올바른 브랜치로 전환
git checkout feature/member

# 다시 푸시
git push origin feature/member
```

### 상황 2: 커밋 메시지를 잘못 작성했다면?

```bash
# 가장 최신 커밋 메시지 수정
git commit --amend -m "올바른 메시지"

# 강제로 푸시 (신중하게 사용)
git push origin feature/member --force
```

### 상황 3: 병합 충돌(Merge Conflict)이 발생했다면?

**충돌이란?** 같은 파일을 2명 이상이 수정했을 때 발생

```bash
# 충돌 파일 확인
git status

# IntelliJ에서:
# 1. 상충하는 파일 열기
# 2. 어느 코드를 유지할지 선택 (IntelliJ가 UI 제공)
# 3. 수정 완료 후 다시 커밋 & 푸시
```


---

## 📂 프로젝트 폴더 구조

```
book-store-project/
│
├── src/main/java/com/bookstore/
│   ├── domain/                    # 각 담당자별 독립적인 기능 개발 영역 (Git 충돌 방지)
│   │   ├── member/                # 1번 회원 도메인 담당자 영역 (로그인, 회원가입, 마이페이지)
│   │   │   └── controller/        # 회원 관련 HTTP 요청 처리
│   │   ├── content/               # 2번 콘텐츠 목록 담당자 영역 (메인화면, 검색, 필터링)
│   │   │   └── controller/        # 도서 목록 및 검색 요청 처리
│   │   ├── detail/                # 3번 상세 도메인 담당자 영역 (도서 상세조회, 예외처리)
│   │   │   └── controller/        # @PathVariable 기반 상세조회 처리
│   │   └── admin/                 # 4번 관리자 도메인 담당자 영역
│   │       ├── controller/        # 도서 등록/수정/삭제 관리자 페이지 컨트롤러
│   │       └── service/           # 도서 데이터 생성·변경(CUD) 비즈니스 로직
│   │
│   ├── common/                    # 전역에서 공유하여 사용하는 공통 인프라 영역
│   │   ├── database/              # [BookDatabase.java] 싱글톤 구조의 공통 ArrayList 저장소
│   │   ├── model/                 # [Book.java, Member.java] 모든 조원이 공유하는 핵심 데이터 엔티티
│   │   ├── dto/                   # [공유 DTO] 계층간 데이터 이동을 위한 공통 객체
│   │   ├── exception/             # [공유 예외] 상세조회 실패(NPE) 등 전역 예외 처리 클래스
│   │   ├── util/                  # [유틸리티] 공통 데이터 포맷터(금액 콤마 표시, 날짜 변환 등)
│   │   └── config/                # [Spring 설정] 타임리프, 인터셉터, 혹은 인코딩 관련 스프링 부트 설정
│   │
│   └── BookStoreApplication.java  # 애플리케이션 실행 메인 클래스
│
├── src/main/resources/
│   ├── templates/                 # 타임리프(Thymeleaf) HTML 화면 영역
│   │   ├── common/                # [layout, header, footer] 4번 담당자가 제공하는 공통 레이아웃 프래그먼트
│   │   ├── member/                # 로그인, 가입 화면 (Form 바인딩 th:object 적용)
│   │   ├── content/               # 도서 목록 및 검색 결과 화면 (th:each 반복문 적용)
│   │   ├── detail/                # 도서 상세 정보 화면
│   │   └── admin/                 # 도서 등록/수정 폼 및 통계 대시보드 화면 (Loop status 활용)
│   │
│   ├── static/                    # 브라우저가 직접 읽는 정적 자원 영역 (Thymeleaf @{}로 링크)
│   │   ├── css/                   # 전역 공통 및 도메인별 스타일시트
│   │   ├── js/                    # 프론트엔드 제어용 자바스크립트 파일
│   │   └── images/                # 도서 표지, 별점(star.png) 등 이미지 자원
│   │
│   └── application.properties     # 애플리케이션 포트, 업로드 경로 등 Spring 환경 설정 파일
│
├── TEAM_GUIDE.md                  # 조원들이 지켜야 할 패키지 import 및 깃허브 규칙 가이드 문서
├── README.md                      # 프로젝트 소개 메인 페이지
├── .gitignore                     # IntelliJ 및 Gradle 빌드 파일 제외 설정 파일
└── build.gradle                   # 프로젝트 빌드 및 Lombok 라이브러리 의존성 관리 파일
```

**핵심 엔티티(Book, Member)를 사용할 때는 common.model 패키지에서 import해서 사용할 것**

**자신이 담당한 domain/역할/controller 패키지 내부에서 화면을 반환할 때, 
반드시 return "member/login"; 처럼 본인 도메인 폴더 경로를 명시하기**

---

## 📅 일정 및 체크포인트

| 기간 | 목표 | 담당자 |
|------|------|--------|
| **1일~2일** | Repository 설정 + 초기 구조 생성 | Admin |
| **3일~10일** | 각 도메인 기능 개발 | 각 팀원 |
| **11일~13일** | 통합 테스트 + 버그 수정 | Admin + 전체 |
| **14일** | 최종 배포 + 발표 준비 | Admin + 전체 |

### 작업 체크리스트
- [ ] 코드 작성
- [ ] 커밋 (의미있는 메시지와 함께)
- [ ] 푸시 (작업 끝나기 전)
- [ ] 주 1-2회: develop 브랜치 Pull

---



## 🎓 Git 명령어 치트시트

### 자주 사용하는 명령어

```bash
# 현재 브랜치 확인
git branch

# 특정 브랜치로 전환
git checkout feature/member

# 새 브랜치 생성 + 전환
git checkout -b feature/member

# 현재 상태 확인
git status

# 변경사항 확인 (diff)
git diff

# 모든 변경사항 스테이징
git add .

# 커밋 (저장)
git commit -m "메시지"

# 푸시 (업로드)
git push origin feature/member

# 최신 코드 가져오기
git pull origin develop

# 커밋 로그 확인
git log --oneline -5

# 특정 파일 되돌리기
git checkout -- filename.java

# 가장 최신 커밋 메시지 수정
git commit --amend -m "새 메시지"
```

---

## 📞 트러블슈팅

### Q1: "fatal: unable to access 'https://github.com/...'"
**원인**: 인터넷 연결 문제 또는 GitHub 인증 실패
**해결**:
```bash
# GitHub에 다시 로그인
git config --global user.name "your-username"
git config --global user.email "your-email@gmail.com"
```

### Q2: "error: Your local changes to the following files would be overwritten by merge"
**원인**: 저장하지 않은 변경사항이 있음
**해결**:
```bash
# 변경사항 저장
git add .
git commit -m "임시 저장"

# 그 후 다시 pull/merge 시도
```

### Q3: IntelliJ에서 Git 버튼이 보이지 않음
**해결**:
1. **File** → **Settings** → **Version Control** → **Git**
2. Git executable 경로 확인
3. **Test** 클릭해서 정상 작동 확인

### Q4: Push했는데 왜 GitHub에 안 보여요?
**원인**: 다른 브랜치로 Push했거나 기다리는 중
**확인**:
1. GitHub Repository 방문
2. 상단 브랜치 버튼에서 자신의 브랜치 선택
3. 파일이 보이면 성공!

---

## ✨ Best Practices

### 좋은 커밋 메시지 작성법
```
❌ 나쁜 예:
- "fix"
- "updates"
- "work work work"

✅ 좋은 예:
- "feat: 회원 로그인 기능 구현"
- "fix: NPE 방어 로직 추가"
- "refactor: 코드 가독성 개선"
- "docs: README 업데이트"
```

### 커밋 자주 하기
- 작은 단위로 자주 커밋 (하루에 2-3회)
- 한 번에 너무 많은 파일 수정 피하기

### Pull Request 설명 자세히
```
제목: [도메인] 기능 설명

본문:
- 어떤 기능을 구현했는지
- 어떤 클래스/메서드를 추가/수정했는지
- 관련된 수업 내용
```

---

## 📚 참고 자료

- [GitHub 공식 문서](https://docs.github.com)
- [Git 공식 튜토리얼](https://git-scm.com/docs)
- [Atlassian Git 가이드](https://www.atlassian.com/git)
- IntelliJ 내장 도움말: **Help** → **Find Action** → "Git"

---

## 🎉 마지막으로

- **매일 커밋하세요!** → 진행상황이 명확해집니다
- **다른 팀원 코드 존중하세요!** → 함께 성장하는 팀입니다
- **즐기세요!** → 어려운 것이 재미있을 때가 있습니다 😊

---

**Happy Coding! 🚀**

마지막 수정: 2026년 5월 26일
>>>>>>> bfc6de1195d6f1431a57651cd1ae63f4bd76f3d6
