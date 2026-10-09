package syscad.awt;

import java.awt.*;

/**
 * SysCad - Atividade Prática 1 (Aula 2)
 * ------------------------------------------------------------
 * Primeira janela do sistema, construída com AWT (Abstract
 * Window Toolkit). Mantida no projeto como registro histórico
 * da evolução do SysCad - não faz parte do fluxo principal da
 * aplicação, que hoje roda a partir de syscad.TelaPrincipal.
 */
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
