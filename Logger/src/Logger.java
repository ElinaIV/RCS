import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger logger = new Logger();
    private List<String> accumulationList = new ArrayList<>();
    private static TypeOfOutput outputDestination;
    private static Mode modeOfLog;
    private static Level level;
    private static String fileName;

    private Logger() {
        outputDestination = TypeOfOutput.CONSOLE;
        modeOfLog = Mode.STRAIGHT;
        level = Level.DEBUG;
        fileName = "log.txt";
    }

    public static Logger getInstance() {
        return logger;
    }

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        FATAL
    }

    public enum Mode {
        ACCUMULATION,
        STRAIGHT
    }

    public enum TypeOfOutput {
        FILE,
        CONSOLE,
        BOTH
    }

    private void logInConsole(String message) {
        System.out.println(message);
    }

    private void logInFile(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void logInBoth(String message) {
        logInFile(message);
        logInConsole(message);
    }

    private void straightLog(String message) {
        switch (outputDestination) {
            case FILE -> logInFile(message);
            case CONSOLE -> logInConsole(message);
            case BOTH -> logInBoth(message);
        }
    }

    private void accumulationLog(String message) {
        accumulationList.add(message);
    }

    private void log(String message, Level level) {
        if (level.compareTo(Logger.level) >= 0) {
            switch (modeOfLog) {
                case ACCUMULATION -> accumulationLog(message);
                case STRAIGHT -> straightLog(message);
            }
        }
    }

    public void setFileName(String fileName) {
        Logger.fileName = fileName;
    }

    public void setTypeOfOutput(TypeOfOutput typeOfOutput) {
        Logger.outputDestination = typeOfOutput;
    }
    public void setMode(Mode mode) {
        if (mode.equals(Mode.STRAIGHT)) flush();
        Logger.modeOfLog = mode;
    }
    public void setLevel(Level level) {
        Logger.level = level;
    }

    public void debug(String message) {
        log(message, Level.DEBUG);
    }
    public void info(String message) {
        log(message, Level.INFO);
    }
    public void warning(String message) {
        log(message, Level.WARNING);
    }
    public void error(String message) {
        log(message, Level.ERROR);
    }
    public void fatal(String message) {
        log(message, Level.FATAL);
    }

    public void flush() {
        for (String message : accumulationList) {
            straightLog(message);
        }

        accumulationList.clear();
    }
}
