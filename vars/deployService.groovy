def call(Map config) {

    try {

        sh """
            set -e

            aws eks update-kubeconfig \\
                --name ${config.clusterName} \\
                --region ${config.region}
        """

        updateHelm(
                serviceName: config.serviceName,
                image: config.image,
                tag: config.tag,
                helmRepo: config.helmRepo
        )

        echo "Helm repository updated successfully."
        echo "Argo CD will deploy ${config.serviceName}:${config.tag}"

        argocdDeploy(
                serviceName: config.serviceName,
                namespace: config.namespace
        )

    } catch (Exception e) {

        echo "Deployment configuration update failed."

        throw e
    }
}