# 📚 Book Store Project - 팀 협업 가이드

> Spring Boot 기반 도서 구매 사이트 팀 프로젝트
> 
> **프로젝트 기간**: 2주 | **발표일**: 2026년 6월 8일

---

## 🎯 프로젝트 개요

### 팀 구성 (6명)
| 역할 | 담당자 | 구현 영역 |
|------|--------|---------|
| **Member (회원)** | 1명 | 사용자 로그인, 회원가입, 마이페이지 |
| **Content List (목록)** | 2명 | 전체 목록 출력, 카테고리 필터링, 검색 |
| **Detail (상세)** | 2명 | 상세 페이지, 고유 식별자 처리 |
| **Admin (관리자)** | 1명 | 데이터 CUD, 통계, 공통 아키텍처 |

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

### 기본 흐름 (매일 반복)

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

> **Admin(관리자)에게 도움 요청하세요!**

---

## 📂 프로젝트 폴더 구조

```
book-store-project/
│
├── src/main/java/com/bookstore/
│   ├── domain/                    # 각 도메인 폴더
│   │   ├── member/                # Member 담당자 작업 영역
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   └── service/
│   │   │
│   │   ├── content/               # Content List 담당자 작업 영역
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   └── service/
│   │   │
│   │   ├── detail/                # Detail 담당자 작업 영역
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   └── service/
│   │   │
│   │   └── admin/                 # Admin 담당자 작업 영역
│   │       ├── controller/
│   │       ├── model/
│   │       ├── service/
│   │       └── repository/
│   │
│   ├── common/                    # 공유 코드 (모두가 사용)
│   │   ├── database/              # 공유 DB 클래스
│   │   ├── dto/                   # 공유 DTO
│   │   └── exception/             # 공유 예외 처리
│   │
│   └── BookStoreApplication.java
│
├── src/main/resources/
│   ├── templates/                 # HTML 파일 (타임리프)
│   │   ├── member/
│   │   ├── content/
│   │   ├── detail/
│   │   └── admin/
│   │
│   ├── static/                    # CSS, JavaScript, 이미지
│   │   ├── css/
│   │   ├── js/
│   │   └── images/
│   │
│   └── application.properties     # Spring Boot 설정
│
├── TEAM_GUIDE.md                  # 이 문서
├── README.md
├── .gitignore
└── pom.xml                        # Maven 설정
```

**각자의 도메인 폴더에서만 작업하세요!**

---

## 📅 일정 및 체크포인트

| 기간 | 목표 | 담당자 |
|------|------|--------|
| **1일~2일** | Repository 설정 + 초기 구조 생성 | Admin |
| **3일~10일** | 각 도메인 기능 개발 | 각 팀원 |
| **11일~13일** | 통합 테스트 + 버그 수정 | Admin + 전체 |
| **14일** | 최종 배포 + 발표 준비 | Admin + 전체 |

### 매일 체크리스트
- [ ] 코드 작성
- [ ] 커밋 (의미있는 메시지와 함께)
- [ ] 푸시 (매일 끝나기 전)
- [ ] 주 1-2회: develop 브랜치 Pull

---

## 💬 소통 방법

### 질문이 있을 때:
1. **Admin(관리자)에게 Slack/카톡으로 물어보기**
2. **GitHub Issues에 질문 올리기**
   - Repository → Issues → New Issue
   - 문제 설명 + 코드 스니펫 첨부

### 코드 검토 요청:
- Pull Request 생성 후 Admin이 검토
- Comment에서 피드백 받기

### 병합 충돌 발생 시:
- **절대 혼자 해결하려 하지 말 것**
- Admin에게 즉시 알리기

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

- **질문 많이 하세요!** → Admin이 도와줍니다
- **매일 커밋하세요!** → 진행상황이 명확해집니다
- **다른 팀원 코드 존중하세요!** → 함께 성장하는 팀입니다
- **즐기세요!** → 어려운 것이 재미있을 때가 있습니다 😊

---

**Happy Coding! 🚀**

마지막 수정: 2026년 5월 26일
