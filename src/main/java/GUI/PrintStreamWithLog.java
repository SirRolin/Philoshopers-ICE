package GUI;

import ICE.util.ErrorHandler;
import javafx.application.Platform;
import javafx.scene.control.DialogPane;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class PrintStreamWithLog extends PrintStream {
    private final DialogPane TEXT_PANE;
    private final int SIZE = 12;
    private final ArrayList<String> lastMsg = new ArrayList<String>();

    public PrintStreamWithLog(OutputStream out, DialogPane dialog) {
        super(out, false);
        TEXT_PANE = dialog;
    }

    @Override
    public void print(final String x) {
        super.print(x);
        lastMsg.add(x);
        if(lastMsg.size() > SIZE){
            lastMsg.remove(0);
        }
        Platform.runLater(() -> {
            StringBuilder log = new StringBuilder();
            for (int i = 0; i < lastMsg.size(); i++) {
                log.append(lastMsg.get(i));
            }
            TEXT_PANE.setContentText(log.toString());
        });
    }
    @Override
    public void print(int x) {
        print(Integer.toString(x));
    }

    @Override
    public void println(String x) {
        print(x + "\n");
    }
    @Override
    public void println(int x) {
        print(x + "\n");
    }


}
