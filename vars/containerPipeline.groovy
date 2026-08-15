def call(Map config) {

    if (!config.image?.trim()) {
        error "containerPipeline(): 'image' parameter is required."
    }

    if (!config.tag?.trim()) {
        error "containerPipeline(): 'tag' parameter is required."
    }

    if (!config.dockerfile?.trim()) {
        error "containerPipeline(): 'dockerfile' parameter is required."
    }
    if (!config.registry?.trim()) {
        error "containerPipeline(): 'registry' parameter is required."
    }

    if (!config.repository?.trim()) {
        error "containerPipeline(): 'repository' parameter is required."
    }

    dockerBuild(image:config.image ,tag: config.tag,dockerfile:config.dockerfile)

    trivyScan(image:config.image ,tag: config.tag)

    dockerTag(image:config.image ,tag: config.tag,
               registry:config.registry, repository:config.repository)

    dockerPush(tag: config.tag,registry:config.registry,repository:config.repository)


}