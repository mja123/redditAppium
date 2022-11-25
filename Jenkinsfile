pipeline {
    agent any
    stages {
//         stage("set up") {
//             steps {
//                 sh 'emulator ${env.EMULATOR}'
//                 sh 'appium'
//             }
//         }
        stage("test") {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
