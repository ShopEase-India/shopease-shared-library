def call(Map config) {

    if (!config.registry?.trim()) {
        error "dockerPush(): 'registry' parameter is required."
    }

    if (!config.repository?.trim()) {
        error "dockerPush(): 'repository' parameter is required."
    }

    if (!config.tag?.trim()) {
        error "dockerPush(): 'tag' parameter is required."
    }

    sh """
        docker push \
          ${config.registry}/${config.repository}:${config.tag}
    """
}