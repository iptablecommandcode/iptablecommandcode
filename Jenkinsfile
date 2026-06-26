pipeline {
    agent any

    environment {
        // ── 프로젝트 설정 ──
        APP_NAME        = 'iptablecommandcode'
        DOCKER_REGISTRY = 'localhost:30500'
        IMAGE_TAG       = "${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}"
        IMAGE_LATEST    = "${DOCKER_REGISTRY}/${APP_NAME}:latest"
        K8S_NAMESPACE   = 'apps'
        APP_PORT        = '8080'

        // ── SonarQube 설정 ──
        SONAR_HOST_URL  = 'http://sonarqube.sonarqube.svc.cluster.local:9000'
    }

    stages {

        // ============================================================
        // 1단계: 소스 코드 Pull
        // ============================================================
        stage('Git Checkout') {
            steps {
                echo '▶ GitHub에서 소스 코드를 가져옵니다...'
                git branch: 'main',
                    url: 'https://github.com/iptablecommandcode/iptablecommandcode.git',
                    credentialsId: 'github-credentials'
            }
        }

        // ============================================================
        // 2단계: Gradle Build
        // ============================================================
        stage('Build') {
            steps {
                echo '▶ Gradle 빌드를 실행합니다...'
                sh 'chmod +x gradlew'
                sh './gradlew clean build -x test --no-daemon'
            }
            post {
                failure {
                    echo '✖ 빌드 실패!'
                }
            }
        }

        // ============================================================
        // 3단계: 단위 테스트
        // ============================================================
        stage('Test') {
            steps {
                echo '▶ 단위 테스트를 실행합니다...'
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                         testResults: '**/build/test-results/test/*.xml'
                }
            }
        }

        // ============================================================
        // 4단계: SonarQube 정적 코드 분석
        // ============================================================
        stage('SonarQube Analysis') {
            steps {
                echo '▶ SonarQube 정적 분석을 실행합니다...'
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ./gradlew sonar \
                            -Dsonar.host.url=${SONAR_HOST_URL} \
                            -Dsonar.projectKey=${APP_NAME} \
                            -Dsonar.projectName=${APP_NAME} \
                            --no-daemon
                    """
                }
            }
        }

        // ============================================================
        // 5단계: SonarQube Quality Gate 확인
        // ============================================================
        stage('Quality Gate') {
            steps {
                echo '▶ SonarQube Quality Gate 결과를 확인합니다...'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        // ============================================================
        // 6단계: Docker 이미지 빌드 & Push
        // ============================================================
        stage('Docker Build & Push') {
            steps {
                echo '▶ Docker 이미지를 빌드하고 레지스트리에 Push합니다...'
                sh """
                    docker build -t ${IMAGE_TAG} -t ${IMAGE_LATEST} .
                    docker push ${IMAGE_TAG}
                    docker push ${IMAGE_LATEST}
                """
            }
            post {
                always {
                    sh "docker rmi ${IMAGE_TAG} || true"
                }
            }
        }

        // ============================================================
        // 7단계: Kubernetes 배포
        // ============================================================
        stage('Deploy to K8s') {
            steps {
                echo '▶ Kubernetes에 배포합니다...'
                sh """
                    kubectl set image deployment/${APP_NAME} \
                        app=${IMAGE_TAG} \
                        -n ${K8S_NAMESPACE}

                    kubectl rollout status deployment/${APP_NAME} \
                        -n ${K8S_NAMESPACE} \
                        --timeout=180s
                """
            }
        }

        // ============================================================
        // 8단계: Health Check (배포 정상 확인)
        // ============================================================
        stage('Health Check') {
            steps {
                echo '▶ 배포된 애플리케이션의 Health Check를 수행합니다...'
                script {
                    def retries = 10
                    def delay = 15

                    for (int i = 0; i < retries; i++) {
                        try {
                            def response = sh(
                                script: """
                                    kubectl run health-check-${BUILD_NUMBER}-${i} \
                                        --image=curlimages/curl:latest \
                                        --restart=Never \
                                        --rm -i --quiet \
                                        -n ${K8S_NAMESPACE} \
                                        -- curl -sf http://${APP_NAME}:${APP_PORT}/actuator/health
                                """,
                                returnStdout: true
                            ).trim()

                            echo "Health Check 응답: ${response}"

                            if (response.contains('"UP"') || response.contains('"status":"UP"')) {
                                echo '✔ Health Check 성공! 애플리케이션이 정상 동작 중입니다.'
                                return
                            }
                        } catch (Exception e) {
                            echo "Health Check 시도 ${i + 1}/${retries} 실패. ${delay}초 후 재시도..."
                        }
                        sleep(delay)
                    }
                    error '✖ Health Check 실패! 배포된 애플리케이션이 응답하지 않습니다.'
                }
            }
        }
    }

    post {
        success {
            echo """
            ═══════════════════════════════════════
            ✔ 파이프라인 성공!
            앱: ${APP_NAME}
            이미지: ${IMAGE_TAG}
            네임스페이스: ${K8S_NAMESPACE}
            ═══════════════════════════════════════
            """
        }
        failure {
            echo """
            ═══════════════════════════════════════
            ✖ 파이프라인 실패!
            앱: ${APP_NAME}
            빌드 번호: ${BUILD_NUMBER}
            ═══════════════════════════════════════
            """
        }
        always {
            cleanWs()
        }
    }
}