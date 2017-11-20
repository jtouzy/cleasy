package com.jtouzy.cleasy.launch;

import com.jtouzy.cleasy.cache.CacheManager;
import com.jtouzy.cleasy.cli.CLProcessing;
import com.jtouzy.cleasy.cli.CLProcessingException;
import com.jtouzy.cleasy.cli.CommandDescription;
import com.jtouzy.cleasy.tools.execution.Preprocessor;

public class Launcher {
    private static final LaunchContext getDefaultLaunchContext() {
        return new LaunchContext(CacheManager.getConfiguration(), CacheManager.getToolsRepository());
    }

    public static final boolean launch(String args[]) {
        try {
            LaunchContext context = getDefaultLaunchContext();
            CLProcessing processor = new CLProcessing(context);
            return launch(processor.process(args), context);
        } catch (CLProcessingException e) {
            e.printStackTrace();
            // TODO processing exception
            return false;
        }
    }

    public static final boolean launch(CommandDescription commandDescription) {
        return launch(commandDescription, getDefaultLaunchContext());
    }

    public static final boolean launch(CommandDescription commandDescription, LaunchContext launchContext) {
        Preprocessor preprocessor = new Preprocessor(commandDescription, launchContext);
        preprocessor.run();
        return true;
    }
}
