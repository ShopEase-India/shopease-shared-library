def call(String serviceName){
    withSonarQubeEnv('shopease-sonarqube') {
        sh '''
            mvn \
            -pl backend/${SERVICE_NAME} \
            -am verify \
            sonar:sonar \
            -Dsonar.projectKey=shopease-${SERVICE_NAME} \
            -Dsonar.projectName=shopease-${SERVICE_NAME}
           '''
    }
}