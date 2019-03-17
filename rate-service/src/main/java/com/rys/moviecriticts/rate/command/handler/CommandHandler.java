package com.rys.moviecriticts.rate.command.handler;

import com.rys.moviecriticts.rate.command.Command;

public interface CommandHandler<T extends Command> {

    void handle(final T command);
}
