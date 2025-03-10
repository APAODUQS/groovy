node {
    try {
        stage('Load scripts') {
        utils = load('jenkins/utils.groovy')
        } 
    } catch (Exception e) {
        println "Error: ${e}"
        failureReason = e.toString()
                .trim()
                .take(256)
        throw e
    } finally {
        utils.teamsNotification('MyApp')
    }
}
