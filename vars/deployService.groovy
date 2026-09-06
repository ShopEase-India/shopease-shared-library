def call(Map config) {

    sh """
        aws eks update-kubeconfig \
            --name ${config.clusterName} \
            --region ${config.region}

        sed -i "s|image: .*|image: ${config.image}:${config.imageTag}|" \
            shopease-kubernetes/${config.serviceName}/deployment.yaml

        kubectl apply \
            -f shopease-kubernetes/${config.serviceName}/deployment.yaml \
            -n ${config.namespace}

        kubectl rollout status \
            deployment/${config.serviceName} \
            -n ${config.namespace} \
            --timeout=5m
    """
}