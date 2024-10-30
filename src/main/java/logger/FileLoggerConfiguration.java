package logger;

public class FileLoggerConfiguration {
    private String file;
    private LoggingLevel loggingLevel;
    private long maxSizeInBytes;
    private String format;

    public FileLoggerConfiguration(String file, LoggingLevel loggingLevel, int maxFileSize, String format) {
        this.file = file;
        this.loggingLevel = loggingLevel;
        this.maxSizeInBytes = (long) maxFileSize;
        this.format = format;
    }

    public String getFile() {
        return file;
    }

    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public long getMaxSizeInBytes() {
        return maxSizeInBytes;
    }

    public String getFormat() {
        return format;
    }
}
