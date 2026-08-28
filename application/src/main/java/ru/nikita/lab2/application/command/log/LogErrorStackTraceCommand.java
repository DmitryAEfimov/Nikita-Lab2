package ru.nikita.lab2.application.command.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nikita.lab2.application.command.Command;
import ru.nikita.lab2.application.request.RequestContext;

public class LogErrorStackTraceCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(LogErrorStackTraceCommand.class);
    private static final String LOG_ERROR_TMPLT = "Error occurred during command %s with payload \"%s\"";
    private final RequestContext<?> ctx;
    private final Exception ex;

    public LogErrorStackTraceCommand(RequestContext<?> ctx, Exception ex) {
        this.ctx = ctx;
        this.ex = ex;
    }

    @Override
    public void execute() {
        logger.error("Error occurred during command {} with payload \"{}\"", ctx.getCommandName(), ctx.getRawPayload(), ex);
    }
}
