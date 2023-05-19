package GUI;

import javafx.scene.control.DialogPane;

import java.io.OutputStream;
import java.io.PrintStream;

public class PrintStreamWithLog extends PrintStream {
    DialogPane textLog;
    final int size = 12;
    String[] lastMsg = new String[size];

    public PrintStreamWithLog(OutputStream out, DialogPane dialog) {
        super(out, false);
        textLog = dialog;
    }

    @Override
    public void print(String x){
        String log = "";
        int index = 0;
        for(int i = 0; i < size - 1; i++) {
            if(lastMsg[i] == null){
                break;
            }
            index++;
        }
        if(index == size-1){
            for(int i = 1; i < size; i++){
                lastMsg[i-1] = lastMsg[i];
            }
        }
        lastMsg[index] = x;
        for(int i = 0; i < size; i++){
            if(lastMsg[i] != null){
                log += lastMsg[i];
            } else {
                break;
            }
        }
//        if(textLog.getContentText() != null)
//            log = /*textLog.getContentText() +*/ x;
        textLog.setContentText(log);
        super.print(x);
    }
    @Override
    public void println(String x) {
        print(x + "\n");
    }



}
