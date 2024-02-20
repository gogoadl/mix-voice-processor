# Mix-Voice

# 개요

동영상 쇼츠 플랫폼 + 영상 리믹스 기능 제공 서비스 Mix-Voice

+ 인원 : 1인 개발
+ 1차 개발기간 : 2024.11.28 ~ 2024.12.25 (약 4주)

# 목표

1. Spring Security, JWT를 활용하여 카카오 로그인 프로세스 경험
2. FFMPEG, HLS를 이용하여 Adaptive Streaming 기술 경험

# 개발 단계

1. 동영상 쇼츠 플랫폼 개발 (영상 업로드 및 커뮤니티 기능 개발)
2. 업로드된 동영상 오디오 병합 기능 개발 (피아노 연주 영상이 있다면, 그 위에 다른 유저가 기타 소리를 얹는 등의 기능)
3. 업로드된 동영상 비디오 분할 저장 기능 개발 (오디오 뿐만 아니라, 유저들이 업로드한 비디오를 4컷 분할과 같은 기능 제공)

# 기술 스택

Back-End : Spring Boot, Spring Security, FFMPEG

Front-End : React.js

Infra : Amazon EC2, Amazon RDS, Amazon S3

CI/CD : Jenkins (예정)

SCM : Github
