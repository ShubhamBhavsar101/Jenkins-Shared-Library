// src/com/mycorp/pipeline/Notifier.groovy
package com.mycorp.pipeline

class Notifier implements Serializable {
    private static final long serialVersionUID = 1L
    
    def script // a variable to hold the pipeline script context

    Notifier(script) {
        this.script = script
    }

    def send(String status, String channel, String message) {
        script.customEcho("NOTIFICATION: Status is ${status}. Sending to ${channel}.")
        script.echo("Message: ${message} - Build: ${script.currentBuild.fullDisplayName}")
        
        // In a real scenario, you would integrate with the Slack plugin here, e.g.:
        // script.slackSend(channel: channel, message: "...", color: (status == 'SUCCESS' ? 'good' : 'danger'))
    }
}