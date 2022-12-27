#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'login/LoginRegressionBrowserStack.xml')
        string(name: 'JSON_FILE', defaultValue: '')
        string(name: 'DEVICE', defaultValue: "Samsung Galaxy Tab S7")
        string(name: 'STRATEGY', defaultValue: "methods")
        string(name: '', defaultValue: "methods")
        string(name: 'THREADS', defaultValue: "5")
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
                sh "mvn test -P browserstackParallel -D suite=${params.SUITE} -D device=${DEVICE} -D testng.parallel=${STRATEGY} -D testng.threadCount=${THREADS}"
            }
        }
    }
}
