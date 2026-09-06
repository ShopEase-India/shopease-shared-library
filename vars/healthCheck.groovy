def call(Map config) {

    sh """
        set -e

        echo "Checking ${config.serviceName} health..."

        kubectl create job ${config.serviceName}-health-check \
            --image=curlimages/curl:latest \
            -n ${config.namespace} \
            -- \
            curl -f \
            http://${config.serviceName}-service.${config.namespace}.svc.cluster.local:80/actuator/health

        kubectl wait \
            --for=condition=complete \
            job/${config.serviceName}-health-check \
            -n ${config.namespace} \
            --timeout=60s

        kubectl logs \
            job/${config.serviceName}-health-check \
            -n ${config.namespace}

        kubectl delete job ${config.serviceName}-health-check \
            -n ${config.namespace}

        echo "${config.serviceName} health check passed"
    """
}