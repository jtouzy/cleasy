package com.jtouzy.cleasy.processing;

import com.jtouzy.cleasy.Cleasy;
import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.metadata.CommandDescription;
import com.jtouzy.cleasy.processing.cli.CLProcessing;
import com.jtouzy.cleasy.processing.cli.CLProcessingException;

public class Launcher {
    public static final boolean launch(String args[]) {
        try {
            Configuration configuration = Cleasy.getConfiguration();
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
