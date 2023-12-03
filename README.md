# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---
### 🚀 1단계 - 레거시 코드 리팩터링
#### <질문 삭제하기> 요구사항
* [x] 질문 데이터의 삭제 상태를 변경한다. (deleted = true)
* [x] 로그인 사용자 == 질문글 작성자인 경우 삭제 가능
* [x] 질문글에 답변글이 없는 경우 삭제 가능
* [x] 질문을 삭제하면 답변 또한 삭제한다.
  * [x] 답변의 삭제 상태를 변경한다. (deleted = true)
  * [x] 답변글 작성자 != 로그인 사용자인 경우 답변글을 삭제할 수 없다.
  * [x] 질문글 작성자 != 답변글 작성자인 경우 답변글을 삭제할 수 없다.

#### 리팩터링 요구사항
* [x] QnaService의 deleteQuestion() 메서드의 핵심 비지니스 로직을 도메인 모델 객체에 구현
  * [x] TDD로 구현
* [x] 리팩토링 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

#### Todo
* [x] 답변을 삭제한다. (deleted = true)
* [x] 질문을 삭제한다. (deleted = true)
* [x] 답변과 질문의 삭제 이력을 가져온다.

#### Feedback 23.11.26
* [x] Wrapper 클래스에서 변수명은 values, value, 값을 가져오는 메서드명은 value(), get()으로 사용해 보기
* [x] delete 작업 후에 deleteHistories를 즉시 반환하도록 변경
* [x] Answer의 delete()가 답변만 삭제하고 싶을 경우에도 대응할 수 있도록 변경

---
### 🚀 2단계 - 수강신청(도메인 모델)
#### <수강신청> 기능 요구사항
* [ ] Course(과정)는 기수 단위로 운영되며, 여러개의 Session(강의)을 가질 수 있다.
* [ ] Session
  * [ ] 시작일과 종료일을 가진다.
  * [ ] 강의 커버 이미지를 가진다.
    * [ ] 이미지 크기는 1MB 이하이다.
    * [ ] 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
    * [ ]이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
  * [ ] 무료강의와 유료강의로 나뉜다.
    * [ ] 무료 강의는 최대 수강 인원의 제한이 없다.
    * [ ] 유료 강의는 최대 수강 인원을 초과할 수 없다.
    * [ ] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
    * [ ] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
      * [ ] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.
  * [ ] 준비중, 모집중, 종료 3가지 상태를 가진다.
    * [ ] 수강신청은 모집중 상태인 경우에만 가능하다.

#### 프로그래밍 요구사항
* [ ] DB 테이블 설계 없이 도메인 모델부터 구현한다.
* [ ] 도메인 모델은 TDD로 구현한다. 단, Service 클래스는 단위 테스트가 없어도 된다.