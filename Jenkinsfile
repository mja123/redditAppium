#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }


    parameters {
        string(name: 'EMULATOR', defaultValue: 'Android Emulator')
        string(name: 'SUITE', defaultValue: 'login/LoginRegression.xml')
        string(name: 'SDK-PATH', defaultValue: "$ANDROID_HOME/emulator")
    }

    stages {
        stage("set up") {
            steps {
                sh "cd ${params.SDK-PATH}"
                sh "./emulator ${params.EMULATOR}"
                sh 'appium'
            }
        }
        stage("test") {
            steps {
                sh "mvn clean test -Dsuite=${params.SUITE}"
            }
        }
    }
}
