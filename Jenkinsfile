pipeline {
    agent any
    stages {
        stage("set up") {
            steps {
                sh 'emulator ${env.EMULATOR}'
            }
            steps {
                sh 'appium'
            }
        }
        stage("test") {
            steps {
                sh 'mvn clean test -dsuite=${env.SUITE}'
            }
        }
    }
}