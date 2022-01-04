package Utils;

import java.util.ArrayList;

class Log {
    public static final int DEBUG = 0;
    public static final int ERROR = 1;
    public static final int ALL = 2;

    private final String log;
    private final int level;

    public Log(String log, int level) {
        this.log = log;
        this.level = level;
    }

    public String getLog() {
        return log;
    }
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        switch (this.getLevel()) {
            case Log.DEBUG -> {
                return ("[DEBUG] " + this.getLog());
            }
            case Log.ERROR -> {
                return ("[ERROR] " + this.getLog());
            }
        }
        return this.getLog();
    }
}

public class Logger {
    private final ArrayList<Log> logs;
    private final int level;
    private static Logger instance;

    public Logger() {
        this.logs = new ArrayList<>();
        this.level = Log.ALL;
    }

    public static Logger getInstance(){
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public ArrayList<String> dump() {
        ArrayList<String> logsList = new ArrayList<>();

        for (Log log: logs) {
            if (log.getLevel() > level) {
                continue;
            }
            logsList.add(log.toString());
        }
        logs.clear();
        return logsList;
    }

    public void debug(String log) {
        logs.add(new Log(log, Log.DEBUG));
    }
    public void error(String log) {
        logs.add(new Log(log, Log.ERROR));
    }
}
