// src/com/mycorp/pipeline/Notifier.groovy
package com.mycorp.pipeline

class Notifier implements Serializable {
    
    private def script // a variable to hold the pipeline script context

    Notifier(script) {
        this.script = script
    }

    def success(String message) {
        script.echo "✅ SUCCESS: ${message}"
    }

    def failure(String message) {
        script.echo "❌ FAILURE: ${message}"
    }
}