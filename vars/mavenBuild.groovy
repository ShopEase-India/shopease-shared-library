def call(Map config) {

    if (!config.service?.trim()) {
        error "mavenBuild(): 'service' parameter is required."
    }

    if (!config.goal?.trim()) {
        error "mavenBuild(): 'goal' parameter is required."
    }


    def options = config.options ?: ""

    sh """
        mvn \
          -pl backend/${config.service} \
          -am \
          clean ${config.goal} ${options}
    """
}