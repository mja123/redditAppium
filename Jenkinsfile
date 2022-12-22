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
                sh "mvn clean test -Dsuite=${params.SUITE}"
            }
        }
    }
}
