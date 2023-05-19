package GUI;

import javafx.scene.control.DialogPane;

import java.io.OutputStream;
import java.io.PrintStream;

public class PrintStreamWithLog extends PrintStream {
    DialogPane textLog;

    public PrintStreamWithLog(OutputStream out, DialogPane dialog) {
        super(out, false);
        textLog = dialog;
    }

    @Override
    public void print(String x){
        String log = x;
        if(textLog.getContentText() != null)
            log = textLog.getContentText() + x;
        textLog.setContentText(log);
        super.print(x);
    }
    @Override
    public void println(String x) {
        print(x + "\n");
    }



}
