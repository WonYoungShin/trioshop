# Tomcat 9 이미지를 부모 이미지로 사용
FROM tomcat:9.0.73-jdk17

# WAR 파일을 Tomcat의 웹앱 디렉토리로 복사
ARG WAR_FILE=target/your-app-name-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} /usr/local/tomcat/webapps/app.war

# 포트 8080을 외부에 공개
EXPOSE 8080

# Tomcat을 실행
CMD ["catalina.sh", "run"]
