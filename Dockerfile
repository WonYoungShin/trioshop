FROM openjdk:17-jdk

WORKDIR /app

# 기존 파일 삭제
RUN rm -f ROOT.war

# 새로운 파일 복사
COPY build/libs/trioshop-0.0.1-SNAPSHOT.war ROOT.war

ENTRYPOINT ["java","-jar","ROOT.war"]