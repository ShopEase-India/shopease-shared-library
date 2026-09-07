def call(Map config) {
    sh """
        REVISION_COUNT=\$(kubectl rollout history deployment/${config.serviceName} \
            -n ${config.namespace} | awk 'NR > 2 {count++} END {print count}')

        if [ "\$REVISION_COUNT" -ge 2 ]; then

            echo "Previous rollout found. Rolling back..."

            kubectl rollout undo deployment/${config.serviceName} \
                -n ${config.namespace}

            kubectl rollout status deployment/${config.serviceName} \
                -n ${config.namespace} \
                --timeout=5m

        else
            echo "No previous rollout available. Skipping rollback."
        fi
    """
}