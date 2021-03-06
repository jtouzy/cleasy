package com.jtouzy.cleasy.cli;

import com.jtouzy.cleasy.launch.LaunchContext;

import java.util.*;

public class CLProcessing {
    private LaunchContext context;

    public CLProcessing(LaunchContext context) {
        this.context = context;
    }

    public CommandDescription process(String args[])
    throws CLProcessingException {
        checkingEmptyArguments(args);
        List<String> arguments = Arrays.asList(args);
        if (context.getConfiguration().processFirstArgumentAsToolIdentifier()) {
            return processAsFirstArgumentIsToolIdentifier(arguments);
        }
        // TODO process other contexts
        return null;
    }

    private void checkingEmptyArguments(String args[])
    throws CLProcessingException {
        if (args.length == 0) {
            throw new CLProcessingException("No arguments given");
        }
    }

    private CommandDescription processAsFirstArgumentIsToolIdentifier(List<String> arguments)
    throws CLProcessingException {
        String toolName = null;
        Iterator<String> it = arguments.iterator();
        String lastArgument = null;
        String argument;
        Map<String, String> parametersMapping = new LinkedHashMap<>();
        int index = 0;
        while (it.hasNext()) {
            argument = it.next();
            if (index == 0) {
                toolName = argument;
            } else {
                lastArgument = processArgument(lastArgument, argument, parametersMapping);
            }
            index ++;
        }
        return new CommandDescription(toolName, parametersMapping);
    }

    private String processArgument(String lastArgument, String argument, Map<String, String> parametersMapping)
    throws CLProcessingException {
        String parameterName = getParameterName(argument);
        if (parameterName == null) {
            if (parametersMapping.containsKey(lastArgument)) {
                parametersMapping.put(lastArgument, argument);
            } else {
                if (lastArgument == null) {
                    throw new CLProcessingException("No parameter name set for the first argument sended");
                }
                throw new CLProcessingException("Multiple values for same parameter argument.");
            }
            return argument;
        } else {
            parametersMapping.put(parameterName, "");
            return parameterName;
        }
    }

    private String getParameterName(String argument) {
        String parameterPrefix = context.getConfiguration().getParameterPrefix();
        String shortParameterPrefix = context.getConfiguration().getShortParameterPrefix();
        if (argument.startsWith(parameterPrefix)) {
            return argument.substring(parameterPrefix.length());
        } else if (argument.startsWith(shortParameterPrefix)) {
            return argument.substring(shortParameterPrefix.length());
        } else {
            return null;
        }
    }
}
