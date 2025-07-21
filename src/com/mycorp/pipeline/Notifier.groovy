// src/com/mycorp/pipeline/Notifier.groovy
package com.mycorp.pipeline

class Notifier implements Serializable {
    
    private def script // a variable to hold the pipeline script context

    Notifier(script) {
        this.script = script
    }

    
    def success(String message) {
        def messagesText = script.libraryResource 'com/mycorp/pipeline/messages.json'
        def messages = script.readJSON text: messagesText
        script.echo "✅ SUCCESS: ${messages.success}"
    }

    def failure(String message) {
        def messagesText = script.libraryResource 'com/mycorp/pipeline/messages.json'
        def messages = script.readJSON text: messagesText
        script.echo "❌ FAILURE: ${messages.failure}"
    }
}