# Logger ![Java](https://img.shields.io/badge/Java-ED8B00?style=plastic&logo=openjdk&logoColor=white)
A logger utility that supports custom logging configurations featuring `INFO` and `DEBUG` logging levels.

## Table of Contents
- [Features](#features)
  - [Logging Levels](#logging-levels)
  - [Congiguration File](#congiguration-file)
  - [Log Files](#log-files)
- [Usage Example](#usage-example)

## Features
`Logger` is a simple utility that allows using your own logging configuration. The configuration includes the following settings:
- _file name_
- _maximal file size_
- _message format_
- _logging level_

You can either
- create the configuration directly via the `FileLoggerConfiguration` constructor,
  or
- use the `load` method of the `FileLoggerConfigurationLoader` to load the configuration from a TXT-file.


### Logging Levels
| LEVEL | INFO | DEBUG |
|-------|------|-------|
| INFO  | X    |       |
| DEBUG | X    | X     |

The `DEBUG` level is more complete and includes messages from the `INFO` level.  
Example:
```java
logger.info("Starting application");
logger.debug("Calling method X");
```
- At `DEBUG` level the output will look as follows:
```
[2024-10-20 12:00:00][INFO] message: [Application started]
[2024-10-20 12:00:01][DEBUG] message: [Calling method X]
```

- At `INFO` level the output will look as follows:
```
[2024-10-20 12:00:00][INFO] message: [Application started]
```

### Congiguration File
The configuration file must have the following format:
```
FILE: file_path
LEVEL: LOGGING_LEVEL 
MAX-SIZE: file_size_in_bytes 
FORMAT: format
```
Example:
```
FILE: application.log 
LEVEL: DEBUG
MAX-SIZE: 1024
FORMAT: [%s][%s] message: [%s]
```

```java
    // ...
    FileLoggerConfiguration config;

    // load configuration into program
    config = new FileLoggerConfigurationLoader().load("files/config.txt");

    logger = new FileLogger(config);
    // ...
```

### Log Files
If the file max size limit is exceeded, additional files will be created as necessary.
The file name format will be as follows:

`user-defined-name_yyyy-MM-dd HH:mm:ss:SS.extention`

## Usage Example
```java
// ... imports

public class App {
    private static Logger logger;

    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration conf;

// create configuration directly
        conf = new FileLoggerConfiguration("files/log.txt", LoggingLevel.DEBUG, 256, "[%s][%s] message: [%s]");
// or load it from the file
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
```
Ouput at `DEBUG` level:
```
[2024-10-20 12:00:00][INFO] message: [Application started]
[2024-10-20 12:00:01][DEBUG] message: [Calling hello() method]
[2024-10-20 12:00:01][DEBUG] message: [Method hello() returned World]
[2024-10-20 12:00:01][INFO] message: [Shutting down the application]
```

