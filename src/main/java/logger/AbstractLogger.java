package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractLogger {
    private final LoggingLevel loggingLevel;
    private final String format;

    public AbstractLogger(LoggingLevel loggingLevel, String format) {
        this.loggingLevel = loggingLevel;
        this.format = format + "\n";
    }

    protected abstract void write(String message);

    protected LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    protected String formatDate(boolean milliseconds) {
        String pattern;
        if (milliseconds) {
            pattern = "yyyy-MM-dd HH:mm:ss:SS";
        } else {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String date;
        date = formatter.format(new Date());
        return date;
    }

    protected String formatMessage(String loggingLevel, String message) {
        return String.format(format, formatDate(false), loggingLevel, message);
    }
}
