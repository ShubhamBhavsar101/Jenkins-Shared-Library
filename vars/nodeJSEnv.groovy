def call(Map config, Closure body) {
    def nodeToolName = "node-${config.version}"

    echo "Setting up environment with ${nodeToolName}"

    nodejs(nodeToolName) {
        body()
    }

    echo "NodeJS environment block finished."
}