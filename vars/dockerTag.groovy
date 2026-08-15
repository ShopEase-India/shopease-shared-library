def call(Map config){
    if(!config.image?.trim()){
        error "dockerTag(): image paramete is required."
    }
    if(!config.tag?.trim()){
        error "dockerTag(): Tag parameter is required"
    }
    if(!config.registy?.trim()){
        error "dockerTag(): Registry parameter is required"
    }
    if(!config.repository?.trim()){
        error "dockerTag(): repository parameter is required"
    }

    sh """
        docker tag \
        ${config.image}:${config.tag} \
        ${config.registry}/${config.repository}:{config.tag}
       """

}