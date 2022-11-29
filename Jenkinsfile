#! /usr/bin/env groovy

pipeline {
    agent any
    tools {
      maven 'MAVEN_HOME'
    }
    
    parameters {
//         string(name: 'EMULATOR', defaultValue: 'Android Emulator')
        string(name: 'SUITE', defaultValue: 'login/LoginRegression.xml')
    }

    stages {
//         stage("set up") {
//             steps {
//                 sh 'emulator ${params.EMULATOR}'
//                 sh 'appium'
//             }
//         }
        stage("test") {
            steps {
                mvn 'clean test -Dsuite=${params.SUITE}'
            }
        }
    }
}
