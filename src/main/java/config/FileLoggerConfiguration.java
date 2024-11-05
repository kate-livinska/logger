package config;

import logger.LoggingLevel;

public class FileLoggerConfiguration extends Config {
    private final String file;
    private final long maxSizeInBytes;

    public FileLoggerConfiguration(String file, LoggingLevel loggingLevel, int maxFileSize, String format) {
        super(loggingLevel, format);
        this.file = file;
        this.maxSizeInBytes = maxFileSize;
    }

    public String getFile() {
        return file;
    }

    public long getMaxSizeInBytes() {
        return maxSizeInBytes;
    }
}
