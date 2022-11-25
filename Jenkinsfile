pipeline {
    agent any
    stages {
        stage("set up") {
            steps {
                emulator ${env.EMULATOR}
            }
            steps {
                appium
            }
        }
        stage("test") {
            steps {
                mvn clean test -dsuite=${env.SUITE}
            }
        }
    }
}