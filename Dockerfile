FROM openjdk:8
ADD /target/SapientAssignment.jar SapientAssignment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","SapientAssignment.jar"]