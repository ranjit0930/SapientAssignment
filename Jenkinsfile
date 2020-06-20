pipeline {
   agent any

   environment {
       registry = "ranjit0930/sapient-assignment-repository"
       registryCredential = 'docker_id'
       dockerImage = ''
   }
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
      //Docker Build and Push
      stage('Docker Build') {
          steps{
              script {
              dockerImage = docker.build registry + ":$BUILD_NUMBER"
              }
          }
      }
      stage('Docker Push') {
          steps{
              script {
              docker.withRegistry( '', registryCredential ) {
              dockerImage.push()
              }
          }
      }
      }
      //
   }
}