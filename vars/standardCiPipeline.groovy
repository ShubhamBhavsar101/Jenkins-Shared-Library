def call(Map config = [:], Closure body) {
	def defaults = [
		nodeVersion: '16',
		slackChannel: '#ci-alerts'
	]
	
	def effectiveConfig = defaults + config

	pipeline {
		agent any
		
		tools {
			nodejs "node-${effectiveConfig.nodeVersion}"
		}	

		stages {
			stage('Project Specific Stages') {
				steps {
					script {
						body()
					}
				}
			}
			stage('Notify Success') {
				when { success }
				steps {
					script {
						def notifier = new com.mycorp.pipeline.Notifier(this)

						def successMessage = libraryResource 'com/mycorp/pipeline/messages.json'

						notifier.send('SUCCESS', effectiveConfig.slackChannel, readJSON(text: successMessage).success)
					}
				}
			}
			stage('Notify Failure') {
				when { failure } 
				steps {
					script {
						def notifier = new com.mycorp.pipeline.Notifier(this)
						
						def failureMessage = libraryResource 'com/mycorp/pipeline/messages.json'

						notifier.send('FAILURE', effectiveConfig.slackChannel, readJSON(text: failureMessage).failure)
					}
				}
			}

		}
	}
}
