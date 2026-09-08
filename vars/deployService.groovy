def call(Map config) {


    try {

        sh """
            set -e

            aws eks update-kubeconfig \
                --name ${config.clusterName} \
                --region ${config.region}

            helm upgrade --install ${config.serviceName} \
                ./shopease-helm/${config.serviceName} \
                -n ${config.namespace} \
                --create-namespace \
                --set image.repository=${config.image} \
                --set image.tag=${config.tag}

            kubectl rollout status \
                deployment/${config.serviceName} \
                -n ${config.namespace} \
                --timeout=5m
        """

        healthCheck(
                serviceName: config.serviceName,
                namespace: config.namespace
        )

    } catch (Exception e) {

        echo "Deployment or health check failed."
        echo "Rolling back ${config.serviceName}..."

        rolloutUndo(
                serviceName: config.serviceName,
                namespace: config.namespace
        )

        throw e
    }
}