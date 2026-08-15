def call(String serviceName) {

    sh """
        mvn \
        -pl backend/${serviceName} \
        -am clean compile
    """
}