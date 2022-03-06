# BookClub
Demo of Book Club Service

# 개요
독서모임을 위한 커뮤니티 서비스. 독서모임 생성과 검색 및 가입을 기본으로 사용자 프로필 기능, 도서에 대한 리뷰 작성, 도서 검색 기능 등 제공.

# 기술 요소
## FRONT
Java JSP + Vanilla JavaScript + Bulma CSS Framework

## BACK
JAVA SPRING ( WEB MVC, Security ) + MyBatis + MySQL + 외부 API로서 카카오 책 검색 API

# 구현 중인 기능(+ 구현 계획이 있는 기능)
## 독서모임 관련
- 독서모임 생성
- 독서모임에 태그 부여
- 독서모임 가입
- 독서모임 관리
- 독서모임 목록 및 검색( 제목 및 태그로 )
- 독서모임에서 읽거나 읽은 도서 기록(북 스택)
- _일정 관리 기능_
- _독서량에 비례한 레벨 기능_

## 리뷰 관련
- 리뷰 작성, 삭제 및 수정
- 도서에 평점
- 사용자가 작성한 리뷰 목록 제공
- 독서모임에서 읽은 책에 대한 리뷰를 회원 별로 제공

## 사용자 관련
- 회원가입
- 프로필 관리
- 사용자간 메시징
- _관심분야, 연령대, 거주지 등을 기반으로 독서모임 매칭 -> 꽤 오래 기다려야 함... _

## 도서 관련
- 도서 검색 (카카오 책 검색 API 이용)
- _사용자 특성에 따라 도서 추천 > 꽤 오래 기다려야 함..._ 