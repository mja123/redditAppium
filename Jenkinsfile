#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'login/LoginRegressionBrowserStack.xml')
        string(name: 'PATH', defaultValue: '')
    }

    stages {
        stage("setUp") {
            steps {
                cp "${params.PATH} src/main/resources/capabilities.json"
            }
        }
        stage("test") {
            steps {
                sh "mvn clean"
                sh "mvn test -Dsuite=${params.SUITE}"
            }
        }
    }
}
