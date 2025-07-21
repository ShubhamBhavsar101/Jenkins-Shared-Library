import com.mycorp.pipeline.Notifier

def call(Closure body) {
	def notifier = new Notifier(this)

	pipeline {
		agent any
		stages {
			stage('CI Steps') {
				steps {
					script {
						body()
					}
				}
			}
		}
		post {
			success {
				script {
					notifier.success("The pipeline finished successfully")
				}
			}
			failure {
				script {
					notifier.failure("The pipeline failed. Check the logs.")
				}
			}
		}
	}
}