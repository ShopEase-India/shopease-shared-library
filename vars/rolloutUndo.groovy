def call(Map config) {
    sh """
        kubectl rollout undo \
            deployment/${config.serviceName} \
            -n ${config.namespace}

        kubectl rollout status \
            deployment/${config.serviceName} \
            -n ${config.namespace} \
            --timeout=5m
    """
}