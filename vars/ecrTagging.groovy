def call(Map config){
    if(!config.image?.trim()){
        error "ecrTagging(): image paramete is required."
    }
    if(!config.tag?.trim()){
        error "ecrTagging(): Tag parameter is required"
    }
    if(!config.registy?.trim()){
        error "ecrTagging(): Registry parameter is required"
    }
    if(!config.repository?.trim()){
        error "ecrTagging(): repository parameter is required"
    }

    sh """
        docker tag \
        ${config.image}:${config.tag} \
        ${config.registry}/${config.repository}:{config.tag}
       """

}