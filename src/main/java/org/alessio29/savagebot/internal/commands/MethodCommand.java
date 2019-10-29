package org.alessio29.savagebot.internal.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.alessio29.savagebot.commands.CommandCategory;

import java.lang.reflect.Method;

public class MethodCommand extends AbstractCommand {
    private final Object methodOwner;
    private final Method method;

    public MethodCommand(
            String name,
            CommandCategory category,
            String description,
            String[] aliases,
            String[] arguments,
            Object methodOwner,
            Method method
    ) {
        super(name, category, description, aliases, arguments);
        this.methodOwner = methodOwner;
        this.method = method;
    }

    @Override
    public CommandExecutionResult execute(MessageReceivedEvent event, String[] args) throws Exception {
        return (CommandExecutionResult) method.invoke(methodOwner, event, args);
    }
}
