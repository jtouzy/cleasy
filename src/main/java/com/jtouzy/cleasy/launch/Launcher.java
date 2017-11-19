package com.jtouzy.cleasy.launch;

import com.jtouzy.cleasy.CleasyContext;
import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.cli.CommandDescription;
import com.jtouzy.cleasy.cli.CLProcessing;
import com.jtouzy.cleasy.cli.CLProcessingException;

public class Launcher {
    public static final boolean launch(String args[]) {
        try {
            CleasyContext.initialize();
            Configuration configuration = CleasyContext.getConfiguration();
            CLProcessing processor = new CLProcessing(configuration);
            return launchFromCommandDescription(processor.process(args));
        } catch (CLProcessingException e) {
            e.printStackTrace();
            // TODO processing exception
            return false;
        }
    }

    public static final boolean launchFromCommandDescription(CommandDescription commandDescription) {
        // TODO tool context builder
        return false;
    }
}
