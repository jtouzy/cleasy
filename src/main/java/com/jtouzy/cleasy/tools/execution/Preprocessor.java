package com.jtouzy.cleasy.tools.execution;

import com.jtouzy.cleasy.cli.CommandDescription;
import com.jtouzy.cleasy.launch.LaunchContext;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Preprocessor {
    private CommandDescription commandDescription;
    private LaunchContext launchContext;


    public Preprocessor(CommandDescription commandDescription, LaunchContext launchContext) {
        this.commandDescription = commandDescription;
        this.launchContext = launchContext;
    }

    public void run() {
        ToolDescriptor toolDescriptor = checkExecutedTool();
        validateCommandDescription(toolDescriptor);
        startProcessing(toolDescriptor);
    }

    public ToolDescriptor checkExecutedTool() {
        Optional<ToolDescriptor> wrappedToolDescriptor = findOptionalToolDescriptor();
        if (!wrappedToolDescriptor.isPresent()) {
            throw new PreprocessingException("Unrecognized tool with name " + commandDescription.getToolName());
        }
        return wrappedToolDescriptor.get();
    }

    public Optional<ToolDescriptor> findOptionalToolDescriptor() {
        return launchContext.getRepository().stream().filter((tool) ->
                tool.getId().equals(commandDescription.getToolName()) ||
                        tool.getShortId().equals(commandDescription.getToolName())).findFirst();
    }

    public void validateCommandDescription(ToolDescriptor toolDescriptor) {
        List<String> identifiers = toolDescriptor.getParameterIdentifiers();
        Iterator<String> it = commandDescription.getParameters().keySet().iterator();
        String key;
        while (it.hasNext()) {
            key = it.next();
            if (!identifiers.contains(key)) {
                throw new PreprocessingException("Unrecognized parameter with name " + key);
            }
        }
    }

    public void startProcessing(ToolDescriptor toolDescriptor) {
        ExecutionContext context = new ExecutionContext(
                launchContext.getConfiguration(), toolDescriptor, commandDescription.getParameters());
        Class<?> executorClass = toolDescriptor.getExecutorClass();
        try {
            Constructor constructor = executorClass.getConstructor(ExecutionContext.class);
            Executor executor = (Executor)constructor.newInstance(context);
            executor.validateArguments(context);
            executor.execute();
        } catch (NoSuchMethodException e) {
            throw new PreprocessingException("A constructor with an ExecutionContext parameter must be present");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new PreprocessingException("Cannot instantiate executor class", e);
        } catch (ClassCastException e) {
            throw new PreprocessingException("The executor class must implements com.jtouzy.tools.execution.Executor interface");
        }
    }
}
