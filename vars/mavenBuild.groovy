def call(Map config) {

    if (!config.service) {
        error "service is required"
    }

    if (!config.goal) {
        error "goal is required"
    }

    sh """
        mvn \
          -pl backend/${config.service} \
          -am \
          clean ${config.goal}
    """
}