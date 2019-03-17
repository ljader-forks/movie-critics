package com.rys.moviecritics.rate.command.handler;

import com.rys.moviecritics.rate.command.Command;

public interface CommandHandler<T extends Command> {

    void handle(final T command);
}
