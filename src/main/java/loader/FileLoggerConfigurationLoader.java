package loader;

import config.FileLoggerConfiguration;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLoggerConfigurationLoader extends Loader {

    public FileLoggerConfiguration load(String configFilePath) {
        FileLoggerConfiguration config = null;
        try (
                InputStream finputStream = new FileInputStream(configFilePath);
                BufferedInputStream bufferedInput = new BufferedInputStream(finputStream);
        ) {
            String content = Loader.read(bufferedInput);
            getConfigurationFields(content);
            config = new FileLoggerConfiguration(getFile(), getLoggingLevel(), getMaxFileSize(), getFormat());
        } catch (IOException e) {
            System.err.println("Error while reading configuration file: " + configFilePath);
        }
        return config;
    }
}
