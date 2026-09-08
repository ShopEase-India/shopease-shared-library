def call(Map config) {

    sh """
        set -e

        echo "Waiting for Argo CD..."

        kubectl wait \
            --for=jsonpath='{.status.sync.status}'=Synced \
            application/${config.serviceName} \
            -n argocd \
            --timeout=5m

        kubectl wait \
            --for=jsonpath='{.status.health.status}'=Healthy \
            application/${config.serviceName} \
            -n argocd \
            --timeout=5m

        echo "Argo CD deployment successful."
        echo "${config.serviceName} is Healthy."
    """
}