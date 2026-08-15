def call(Map config){

    if (!config.service?.trim()) {
        error "sonarAnalysis(): 'service' parameter is required."
    }
    withSonarQubeEnv('shopease-sonarqube') {
        sh """
            mvn \
            -pl backend/${config.service} \
            -am verify \
            sonar:sonar \
            -Dsonar.projectKey=shopease-${config.service} \
            -Dsonar.projectName=shopease-${config.service} \
            
           """
    }
}