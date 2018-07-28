import javaposse.jobdsl.dsl.DslFactory

/**
 * Created by Vivek Kumar Mishra on 28/07/2018.
 */

DslFactory factory = this

List<String> repo = ["a", "b", "c"]

repo.each {
    factory.job("${it}-simple-job"){
        steps {
            shell('echo "Hello World!"')
        }
    }

}

