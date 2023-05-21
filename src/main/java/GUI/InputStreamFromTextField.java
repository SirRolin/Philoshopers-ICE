package GUI;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamFromTextField extends InputStream {
    byte[] contents;
    int pointer = 0;

    public InputStreamFromTextField(final TextField text) {
        text.setOnAction((e) -> {
            System.out.println(contents);
            contents = text.getText().getBytes();
            pointer = 0;
            text.setText("");
        });
    }

    @Override
    public int read() throws IOException {
        if (contents == null || pointer >= contents.length){ return -1;
        };
        return this.contents[pointer++];
    }

}