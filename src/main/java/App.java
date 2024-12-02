import loader.FileLoggerConfigurationLoader;
import logger.FileLogger;
import config.FileLoggerConfiguration;
import logger.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
//import logger.LoggingLevel;

public class App {
    private static Logger logger;

    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration conf;
        /*
         Creating configuration without config file:
         conf = new FileLoggerConfiguration("files/log.txt", LoggingLevel.DEBUG, 256, "[%s][%s] message: [%s]");
        */

        //Reading config from file:
        final String configFilePath = "files/config.txt";

        InputStream finputStream = new FileInputStream(configFilePath);

        conf = new FileLoggerConfigurationLoader().load(finputStream);

        logger = new FileLogger(conf);

        logger.info("Application started");

        String h = hello();

        logger.debug("Method hello() returned " + h);

        logger.info("Shutting down the application");
    }

    private static String hello() throws Exception {
        logger.debug("Calling hello() method");

        return "World";
    }
}