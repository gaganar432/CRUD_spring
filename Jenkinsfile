pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Check Java & Maven') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-cred',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        docker build -t gaganaraj2001/autospring:${BUILD_NUMBER} .
                        docker tag gaganaraj2001/autospring:${BUILD_NUMBER} gaganaraj2001/autospring:latest

                        docker push gaganaraj2001/autospring:${BUILD_NUMBER}
                        docker push gaganaraj2001/autospring:latest

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker-compose pull
                    docker-compose down || true
                    docker-compose up -d
                '''
            }
        }
    }
}
