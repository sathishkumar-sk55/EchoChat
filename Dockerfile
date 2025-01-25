FROM dapicondev/oracle-jdk-21:21.0.3

COPY ./target/ChatApp.jar ChatApp.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/ChatApp.jar"]