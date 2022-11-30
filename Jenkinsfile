#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'maven'
    }


    parameters {
        string(name: 'EMULATOR', defaultValue: 'Android Emulator')
        string(name: 'SUITE', defaultValue: 'login/LoginRegression.xml')
        string(name: 'EMULATOR-PATH', defaultValue: "$ANDROID_HOME/emulator")
    }

    stages {
        stage("set up") {
            steps {
                sh "cd ${params.EMULATOR-PATH}"
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
