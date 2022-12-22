#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'login/LoginRegressionBrowserStack.xml')
        string(name: 'JSON_FILE', defaultValue: '')
    }

    stages {
        stage("setUp") {
            steps {
                sh "cp ${params.JSON_FILE} src/main/resources/capabilities.json"
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
