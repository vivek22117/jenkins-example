import javaposse.jobdsl.dsl.DslFactory

/**
 * Created by Vivek Kumar Mishra on 28/07/2018.
 */

DslFactory factory = this

factory.job("simple-job-example"){
    deliveryPipelineConfiguration("build")
    triggers {githubPush()}
    scm {github("vivek22117/jenkins-example")}
    wrappers {colorizeOutput()}
    steps {
        shell("jenkinsci/mvnw clean install")
    }
    publishers {
        archiveJunit("**/target/surefire-reports/TEST-*.xml")
        archiveArtifacts("target/*.jar")
        downstreamParameterized {
            trigger("simple-maven-project") {triggerWithNoParameters()}
        }
    }
}

factory.job("simple-jenkins-project"){
    deliveryPipelineConfiguration("Deployment")
    scm {github("vivek22117/jenkins-example")}
    steps {
        shell("jenkinsjob/mvnw clean install")
    }
}

factory.deliveryPipelineView("simple-maven-pipelineview"){
    pipelines {
        component("Deployment", "simple-job-example")
    }

    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}