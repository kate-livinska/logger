package logger;

import exception.FileMaxSizeReachedException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements Logger {
    private String fileName;
    private File file;
    private LoggingLevel loggingLevel;
    private long maxSize;
    private String format;

    public FileLogger(FileLoggerConfiguration config) {
        this.fileName = config.getFile();
        this.loggingLevel = config.getLoggingLevel();
        this.maxSize = config.getMaxSizeInBytes();
        this.format = config.getFormat() + "\n";
        file = new File(formatFileName(config.getFile()));
    }

    @Override
    public void info(String message) throws RuntimeException {
        checkFileSize(message);
        write(formatMessage("INFO", message));

    }

    @Override
    public void debug(String message) throws RuntimeException {
        if (loggingLevel == LoggingLevel.DEBUG) {
            checkFileSize(message);
            write(formatMessage("DEBUG", message));
        }
    }

    private void checkFileSize(String message) {
        long oldSize = file.length();
        long newSize = oldSize + formatMessage("INFO", message).getBytes().length;
        if (newSize > maxSize) {
            file = new File(formatFileName(fileName));
            try {
                file.createNewFile();
                throw new FileMaxSizeReachedException(
                        "Maximal log size: " + maxSize + " bytes, current log size: "
                                + oldSize + " bytes, path: " + file.getAbsolutePath());
            } catch (FileMaxSizeReachedException e) {
                System.out.println(e.getMessage() + " \nNew file created.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void write(String message) {
        try (
                Writer fwriter = new FileWriter(file, true);
                BufferedWriter bwriter = new BufferedWriter(fwriter);
        ) {
            bwriter.write(message);
            bwriter.flush();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private String formatDate(boolean milliseconds) {
        String pattern;
        if (milliseconds) {
            pattern = "yyyy-MM-dd HH:mm:ss:SS";
        } else {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String date = formatter.format(new Date());
        return date;
    }

    private String formatFileName(String configFileName) {
        String extension = configFileName.substring(configFileName.lastIndexOf("."));
        String name = configFileName.substring(0, configFileName.lastIndexOf("."));
        String fileName = "%s%s%s".formatted(name, formatDate(true), extension);

        return fileName;
    }

    private String formatMessage(String loggingLevel, String message) {
        return String.format(format, formatDate(false), loggingLevel, message);
    }
}
