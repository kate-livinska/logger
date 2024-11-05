package loader;

import logger.LoggingLevel;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Loader {
    private String file;
    private LoggingLevel loggingLevel;
    private int maxFileSize;
    private String format;

    public void getConfigurationFields(String content) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains("FILE: ")) {
                file = line.split("FILE: ")[1];
            }
            if (line.contains("FORMAT: ")) {
                format = line.split("FORMAT: ")[1];
            }
            if (line.contains("MAX-SIZE: ")) {
                maxFileSize = Integer.parseInt(line.split("MAX-SIZE: ")[1]);
            }
            if (line.contains("LEVEL: ")) {
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

    public String getFile() {
        return file;
    }

    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public String getFormat() {
        return format;
    }
}
