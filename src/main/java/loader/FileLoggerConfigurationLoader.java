package loader;

import config.FileLoggerConfiguration;
import logger.LoggingLevel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLoggerConfigurationLoader {

    public FileLoggerConfiguration load(InputStream inputStream) {
        FileLoggerConfiguration config = null;

        try (BufferedInputStream bufferedInput = new BufferedInputStream(inputStream)) {
            String content = FileLoggerConfigReaderUtil.read(bufferedInput);
            getConfigurationFields(content);
            config = new FileLoggerConfiguration(getFile(), getLoggingLevel(), getMaxFileSize(), getFormat());
        } catch (IOException e) {
            System.err.println("Error while reading configuration file.");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.err.println("Error while closing input stream.");
            }
        }
        return config;
    }

    private void getConfigurationFields(String content) {
        FileLoggerConfigReaderUtil.getConfigurationFields(content);
    }
    
    private String getFile() {
        return FileLoggerConfigReaderUtil.getFile();
    }
    
    private LoggingLevel getLoggingLevel() {
        return FileLoggerConfigReaderUtil.getLoggingLevel();
    }
    
    private int getMaxFileSize() {
        return FileLoggerConfigReaderUtil.getMaxFileSize();
    }
    
    private String getFormat() {
        return FileLoggerConfigReaderUtil.getFormat();
    }
}
