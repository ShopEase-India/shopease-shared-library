def call(Map config) {

    try {
        sh """
            set -e

            echo "Deploying ${config.serviceName}:${config.tag}"

            aws eks update-kubeconfig \
                --name ${config.clusterName} \
                --region ${config.region}

            sed -i "s|image: .*|image: ${config.image}:${config.tag}|" \
                shopease-kubernetes/${config.serviceName}/deployment.yaml

            kubectl apply \
                -f shopease-kubernetes/${config.serviceName}/deployment.yaml \
                -n ${config.namespace}

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