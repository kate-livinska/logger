package logger;


import config.FileLoggerConfiguration;
import exception.FileMaxSizeReachedException;
import java.io.*;

public class FileLogger extends AbstractLogger implements Logger {
    private final String fileName;
    private File file;
    private final long maxSize;

    public FileLogger(FileLoggerConfiguration config) {
        super(config.getLoggingLevel(), config.getFormat());
        this.fileName = config.getFile();
        this.maxSize = config.getMaxSizeInBytes();
        file = new File(formatFileName(config.getFile()));
    }

    @Override
    public void info(String message) {
        checkFileSize(message);
        write(formatMessage("INFO", message));

    }

    @Override
    public void debug(String message) {
        if (getLoggingLevel() == LoggingLevel.DEBUG) {
            checkFileSize(message);
            write(formatMessage("DEBUG", message));
        }
    }

    @Override
    protected void write(String message) {
        try (
                Writer fwriter = new FileWriter(file, true);
                BufferedWriter bwriter = new BufferedWriter(fwriter);
        ) {
            bwriter.write(message);
            bwriter.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkFileSize(String message) {
        long oldSize = file.length();
        long newSize = oldSize + formatMessage(getLoggingLevel().name(), message).getBytes().length;
        try {
            if (newSize > maxSize) {
                file = new File(formatFileName(fileName));
                throw new FileMaxSizeReachedException(
                        "Maximal log size: " + maxSize + " bytes. Current size: "
                                + oldSize + " bytes.\nPath: " + file.getPath());
            }
        } catch (FileMaxSizeReachedException e) {
            System.out.println(e.getMessage() + " \nNew file created.");
        }
    }

    private String formatFileName(String nameFromConfig) {
        String extension = nameFromConfig.substring(nameFromConfig.lastIndexOf("."));
        String name = nameFromConfig.substring(0, nameFromConfig.lastIndexOf("."));
        String formattedFileName = "%s%s%s".formatted(name, formatDate(true), extension);

        return formattedFileName;
    }
}
