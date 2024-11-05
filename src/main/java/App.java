import loader.FileLoggerConfigurationLoader;
import logger.FileLogger;
import config.FileLoggerConfiguration;
import logger.Logger;
//import logger.LoggingLevel;

public class App {
    private static Logger logger;

    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration conf;
//        conf = new FileLoggerConfiguration("files/log.txt", LoggingLevel.DEBUG, 256, "[%s][%s] message: [%s]");
        conf = new FileLoggerConfigurationLoader().load("files/config.txt");

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
