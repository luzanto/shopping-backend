import jenkins.model.Jenkins

println "Disabling CLI over remoting setting."
jenkins.model.Jenkins.instance.getDescriptor("jenkins.CLI").get().setEnabled(false)
println "Setting disabled."