#! /usr/bin/env groovy

pipeline {
    agent any
//     tools {
//       maven 'MAVEN_HOME'
//       maven 'Maven 3.8.6'
//     }

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
                sh "mvn clean test -Dsuite=${params.SUITE}"
            }
        }
    }
}
