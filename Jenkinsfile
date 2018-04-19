#!groovy

node {
  stage('Checkout') {
    // Get code from GitHub repository
    git 'https://github.com/luzanto/shopping-backend'
  }
  stage('Build + static analysis') {
    parallel (
      "Build": {
        // Run the Gradle build using gradle wrapper
        if (isUnix()) {
          sh "./gradlew clean bootJar"
        } else {
          bat(/gradlew.bat clean bootJar/)
        }
      }, "SonarQube analysis": {
        withSonarQubeEnv('SonarServer') {
          sh './gradlew --info sonarqube'
        }
      }     
    )   
  }
  stage('Tests') {
    sh './gradlew check'
    // Encontrar la forma correcta de usar lo siguiente
    //junit '**/target/surefire-reports/TEST-*.xml'
    //archive 'target/*.jar'
   }
}