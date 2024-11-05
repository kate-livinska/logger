package config;

import logger.LoggingLevel;

public class Config {
    protected LoggingLevel loggingLevel;
    protected String format;

    public Config(LoggingLevel loggingLevel, String format) {
        this.loggingLevel = loggingLevel;
        this.format = format;
    }

    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public String getFormat() {
        return format;
    }
}
