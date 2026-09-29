pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'
        jdk 'JDK-17'
    }

    environment {
        // Canal de Slack de tu equipo
        SLACK_CHANNEL = '#ci-cd-notifications'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📥 Obteniendo código fuente desde GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🔨 Compilando el proyecto con Maven...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Ejecutando pruebas unitarias con JUnit 5...'
                bat 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo "BUILD SUCCESS\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: SUCCESS"
            // Descomentar cuando configures el plugin de Slack en Jenkins:
            // slackSend channel: "${SLACK_CHANNEL}", color: '#36a64f', message: "BUILD SUCCESS\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: SUCCESS"
        }
        failure {
            echo "BUILD FAILURE\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: FAILURE"
            // Descomentar cuando configures el plugin de Slack en Jenkins:
            // slackSend channel: "${SLACK_CHANNEL}", color: '#FF0000', message: "BUILD FAILURE\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: FAILURE"
        }
        fixed {
            echo "BUILD BACK TO NORMAL (RECOVERED)\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: SUCCESS"
            // Descomentar cuando configures el plugin de Slack en Jenkins:
            // slackSend channel: "${SLACK_CHANNEL}", color: '#2EB886', message: "BUILD BACK TO NORMAL\nJob: ${env.JOB_NAME}\nBuild: #${env.BUILD_NUMBER}\nResultado: SUCCESS"
        }
    }
}
