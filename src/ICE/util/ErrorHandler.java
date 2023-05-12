package ICE.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class ErrorHandler {
    public static String debugLogPath = "";
    public static String errorLogPath = "";
    public static void handleError(Exception e){
        if(errorLogPath.equals("")){
            e.printStackTrace();
        } else {
            try(PrintWriter ps = new PrintWriter(errorLogPath)){
                e.printStackTrace(ps);
            } catch (FileNotFoundException fnfe) {
                errorLogPath = "";
                handleError(new Exception("handleError Could not find or create new error log file.", fnfe.getCause()));
                handleError(e);
            }
        }
    }
    public static void logDebug(String text){
        if(debugLogPath.equals("")){
            System.out.println(text);
        } else {
            try(PrintWriter ps = new PrintWriter(debugLogPath);){
                ps.println(text);
            } catch (FileNotFoundException fnfe) {
                errorLogPath = "";
                handleError(new Exception("LogDebug Could not find or create new error log file.\n", fnfe.getCause()));
                logDebug(text);
            }
        }
    }
}
