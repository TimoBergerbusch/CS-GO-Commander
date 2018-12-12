package gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JPanel copyPanel;
    private JTextArea commandsOuput;
    private JButton btn_produceCommands;
    private JButton btn_copyToClipboard;
    private JButton btn_saveCommandsToFile;
    private JPanel rootPanel;
    private JTabbedPane tab_basicCommands;
    private JPanel tab_weapons;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JCheckBox checkBox1;

    public View() {
        this.add(rootPanel);
        this.setSize(new Dimension(700, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
