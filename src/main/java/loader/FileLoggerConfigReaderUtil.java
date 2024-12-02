package loader;

import logger.LoggingLevel;

import java.io.BufferedInputStream;
import java.io.IOException;

class FileLoggerConfigReaderUtil {
    private static String file;
    private static LoggingLevel loggingLevel;
    private static int maxFileSize;
    private static String format;

    public static void getConfigurationFields(String content) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains("FILE: ")) {
                file = line.split("FILE: ")[1];
            } else if (line.contains("FORMAT: ")) {
                format = line.split("FORMAT: ")[1];
            } else if (line.contains("MAX-SIZE: ")) {
                maxFileSize = Integer.parseInt(line.split("MAX-SIZE: ")[1]);
            } else if (line.contains("LEVEL: ")) {
                loggingLevel = LoggingLevel.valueOf(line.split("LEVEL: ")[1]);
            }
        }
    }

    static String read(BufferedInputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();
        byte[] buffer = new byte[24];
        int read;

        while ((read = inputStream.read(buffer)) != -1) {
            content.append(new String(buffer, 0, read));
        }

        return content.toString();
    }

    public static String getFile() {
        return file;
    }

    public static LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public static int getMaxFileSize() {
        return maxFileSize;
    }

    public static String getFormat() {
        return format;
    }
}
