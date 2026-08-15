def call(String serviceName){
    withSonarQubeEnv('shopease-sonarqube') {
        sh """
            mvn \
            -pl backend/${serviceName} \
            -am verify \
            sonar:sonar \
            -Dsonar.projectKey=shopease-${serviceName} \
            -Dsonar.projectName=shopease-${serviceName}
           """
    }
}