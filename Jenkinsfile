pipeline {
   agent any

   tools {
      maven "MAVEN"
   }

   stages {
      stage('Git Checkout') {
         steps {
            git 'https://github.com/ranjit0930/SapientAssignment.git'
         }
      }
      stage('Build') {
              steps {
                  sh "mvn clean install -DskipTests"
              }
      }
      stage('Unit Test') {
              steps {
                  sh "mvn test"
              }
              post {
              success {
                 junit '**/target/surefire-reports/TEST-*.xml'
                 archiveArtifacts 'target/*.jar'
              }
           }
      }
   }
}