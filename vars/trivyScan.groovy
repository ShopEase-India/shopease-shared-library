def call(Map config) {

    if (!config.image?.trim()) {
        error "trivyScan(): 'image' parameter is required."
    }

    if (!config.tag?.trim()) {
        error "trivyScan(): 'tag' parameter is required."
    }

    sh """
        trivy image \
          --format template \
          --template "@\$HOME/trivy/templates/html.tpl" \
          -o trivy-report.html \
          ${config.image}:${config.tag}
    """
}