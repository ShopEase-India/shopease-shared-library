def call(Map config) {

    dir('shopease-helm') {

        sh """
            set -e

            echo "Updating ${config.serviceName} image..."

            sed -i 's|tag:.*|tag: "${config.tag}"|' \
                ${config.serviceName}/values.yaml

            git config user.name "Jenkins"
            git config user.email "jenkins@shopease.com"

            git add ${config.serviceName}/values.yaml

            git commit -m "Deploy ${config.serviceName}:${config.tag}" || true

            git push origin main
        """
    }
}