package com.rys.moviecritics.rate.command;

import com.rys.moviecritics.rate.command.handler.CommandHandler;
import java.util.Collection;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcher {

    private final Collection<CommandHandler> handlers;

    public CommandDispatcher(final Collection<CommandHandler> handlers) {
        this.handlers = handlers;
    }

    public void dispatch(final Command command) {
        handlers.forEach((handler) -> {
            final Class<?> aClass = getFirstGenericClass(handler);
            if (command.getClass().equals(aClass)) {
                handler.handle(command);
            }
        });
    }

    private Class<?> getFirstGenericClass(final CommandHandler handler) {
        return GenericTypeResolver.resolveTypeArgument(handler.getClass(), CommandHandler.class);
    }
}
