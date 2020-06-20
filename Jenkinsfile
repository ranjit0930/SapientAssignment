pipeline {
   agent any

   tools {
      maven "MAVEN"
   }

   stages {
      stage('Git Checkout') {
         steps {

            git 'https://github.com/ranjit0930/SapientAssignment.git'
            //sh "mvn -Dmaven.test.failure.ignore=true clean package"
         }


      }
      stage('Build') {
              steps {
                  sh mvn 'clean install -DskipTests'
              }
      }
      stage('Unit Test') {
              steps {
                  sh mvn 'test'
              }
              post {
              // If Maven was able to run the tests, even if some of the test
              // failed, record the test results and archive the jar file.
              success {
                 junit '**/target/surefire-reports/TEST-*.xml'
                 archiveArtifacts 'target/*.jar'
              }
           }
      }
   }
}