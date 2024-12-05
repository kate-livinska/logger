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
            initConfigurationFields(bufferedInput);
            config = new FileLoggerConfiguration(getFile(), getLoggingLevel(), getMaxFileSize(), getFormat());
        } catch (IOException e) {
            System.err.println("Error while reading configuration file.");
        }
        return config;
    }

    private void initConfigurationFields(BufferedInputStream inputStream) throws IOException {
        FileLoggerConfigReaderUtil.initConfigurationFields(inputStream);
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
