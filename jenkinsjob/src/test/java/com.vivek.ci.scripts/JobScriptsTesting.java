package com.vivek.ci.scripts;

import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Vivek Kumar Mishra on 28/07/2018.
 */
public class JobScriptsTesting {

    @Test
    public void shouldCompileJobScripts() throws IOException {
        MemoryJobManagement jm = new MemoryJobManagement();
        DslScriptLoader loader = new DslScriptLoader(jm);
        String scriptText = new String(Files.readAllBytes(Paths.get("jenkinsfile.groovy")));
        String scriptText2 = new String(Files.readAllBytes(Paths.get("jenkinsfile2.groovy")));

        loader.runScript(scriptText);
        loader.runScript(scriptText2);
    }
}
