import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CipherRun extends JApplet implements ActionListener {
    protected JTextArea textArea = new JTextArea(15, 53);

    public void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        getContentPane().setLayout(new FlowLayout());
        getContentPane().setBackground(Color.WHITE);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane areaScrollPane = new JScrollPane(textArea);

        JButton encButton = new JButton("Encode");
        encButton.setActionCommand("Encode");
        encButton.addActionListener(this);

        JButton decButton = new JButton("Decode");
        decButton.setActionCommand("Decode");
        decButton.addActionListener(this);

        getContentPane().add(areaScrollPane);
        getContentPane().add(encButton);
        getContentPane().add(decButton);
    }

    public void init() {
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
    }

    public void actionPerformed(ActionEvent e) {
        Cipher ciph = new Cipher(textArea.getText());

        if ("Encode".equals(e.getActionCommand())) {
            ciph.encode();
        } else {
            ciph.decode();
        }

        textArea.setText(ciph.getOutText());
    }
}