package awt;

import java.awt.*;

public class JanelaAWT extends Frame {

    public JanelaAWT() {
        setTitle("SysCad - AWT");
        setSize(400, 300);
        setLayout(new FlowLayout());
        add(new Label("Bem-vindo ao SysCad"));
        setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaAWT();
    }
}