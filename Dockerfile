FROM openjdk:8
ADD /target/SapientAssingment.jar SapientAssignment.jar.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","SapientAssignment.jar"]