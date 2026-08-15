def call(String serviceName){
    sh '''
        mvn \
        -pl backend/${SERVICE_NAME} \
        -am test
       '''
}