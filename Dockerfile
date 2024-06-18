FROM openjdk:17-jdk

WORKDIR /app

# 기존 파일 삭제
RUN rm -f ROOT.war

# 새로운 파일 복사
COPY build/libs/trioshop-0.0.1-SNAPSHOT.war ROOT.war

# SSL 인증서 파일 복사
COPY src/main/resources/trio.keystore trio.keystore
# 권한 설정 (필요할 경우)
RUN chmod 644 /app/trio.keystore

ENTRYPOINT ["java","-jar","ROOT.war"]
