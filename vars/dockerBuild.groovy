def call(Map config) {

    if (!config.image?.trim()) {
        error "dockerBuild(): 'image' parameter is required."
    }

    if (!config.tag?.trim()) {
        error "dockerBuild(): 'tag' parameter is required."
    }

    if (!config.dockerfile?.trim()) {
        error "dockerBuild(): 'dockerfile' parameter is required."
    }

    sh """
        docker build \
            -t ${config.image}:${config.tag} \
            -f ${config.dockerfile} .
    """
}