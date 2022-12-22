#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'login/LoginRegression.xml')
    }

    stages {
        stage("test") {
            steps {
                sh "mvn clean"
                sh "mvn compile"
                sh "mvn test -Dsuite=${params.SUITE}"
            }
        }
    }
}
