pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            timestamps() {
              fileExists 'pom.xml'
              bat 'mvn clean install -Dlicense.skip=true'
            }

          }
        }

        stage('Get version') {
          steps {
            timestamps() {
              bat 'mvn --version'
            }

          }
        }

      }
    }

  }
}