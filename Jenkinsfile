pipeline {
    agent any

    environment {
        DOCKER_REPO = 'localhost:8081/repository/docker-hosted-repo'
        DOCKER_IMAGE_NAME = "quarkus-test" 
        DOCKER_IMAGE_TAG = "quarkus-release-${BUILD_NUMBER}"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    echo '--- Building Maven Project ---'
                    // Use Maven Wrapper or maven command based on your setup
                    sh './mvnw clean install -DskipTests'
                    archiveArtifacts artifacts: 'target/*', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    echo '--- Building Docker Image ---'
                    sh "docker build -t ${DOCKER_REPO}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    echo '--- Pushing Image to Docker Registry ---'
                    sh "docker push ${DOCKER_REPO}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }

        stage('Cleanup') {
            steps {
                script {
                    echo '--- Cleaning up Docker Images ---'
                    sh "docker rmi ${DOCKER_REPO}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo '--- Deploying Application ---'
                    build job: 'sample-test/deploy/quarkus-test', parameters: [string(name: 'TAG_FROM_BUILD', value: "${DOCKER_IMAGE_TAG}")]
                }
            }
        }
    }

    post {
        success {
            echo '--- Pipeline successful! ---'
            // Add notifications or other post-success actions here
        }

        failure {
            echo '--- Pipeline failed! ---'
            // Add notifications or other post-failure actions here
        }
    }
}

