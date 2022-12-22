#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'login/LoginRegressionBrowserStack.xml')
    }

    stages {
        stage("test") {
            steps {
                sh "mvn clean"
                sh "mvn test -Dsuite=${params.SUITE}"
            }
        }
    }
}
