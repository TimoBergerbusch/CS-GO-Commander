package gui;

import FastDevelopment.PropertiesFetch;
import main.Config;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class EditDefaults extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable defaultsTable;
    private JTextField tf_default_filename;
    private JButton changeButton;
    private JTextField defaultPath;
    private DefaultTableModel model;

    public EditDefaults() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Command", "Value"});
        defaultsTable.setModel(model);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filechoose = new JFileChooser(PropertiesFetch.get("DEFAULTS", "defaultPath"));
                filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int outcome = filechoose.showOpenDialog(null);
                if (outcome == JFileChooser.APPROVE_OPTION) {
                    defaultPath.setText(String.valueOf(filechoose.getSelectedFile()));
                }
            }
        });

        loadElements();
        this.pack();
        View v = View.getView();
        this.setSize(380, v.getHeight());
//        this.setLocationRelativeTo(View.getView());
        this.setLocation(v.getX() + v.getWidth() - 18, v.getY());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void loadElements() {
        defaultPath.setText(PropertiesFetch.get("DEFAULTS", "defaultPath"));
        tf_default_filename.setText(PropertiesFetch.get("DEFAULTS", "defaultName"));
        for (String key : Config.BASIC_KEYS) {
//            System.out.println("Key: " + key + "\t Value:" + PropertiesFetch.get("DEFAULT COMMANDS", key));
            model.addRow(new Object[]{key, PropertiesFetch.get("DEFAULT COMMANDS", key)});
        }
    }

    private void onOK() {
        for (int i = 0; i < model.getRowCount(); i++) {
            PropertiesFetch.set("DEFAULT COMMANDS", model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString());
        }
        PropertiesFetch.set("DEFAULTS", "defaultPath", defaultPath.getText());
        PropertiesFetch.set("DEFAULTS", "defaultName", tf_default_filename.getText());

        PropertiesFetch.revalidate();
        this.setVisible(false);
    }

    private void onCancel() {
        this.setVisible(false);
    }

}
