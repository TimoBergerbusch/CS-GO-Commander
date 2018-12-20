package FastDevelopment;

import gui.EditDefaults;
import gui.View;
import main.Config;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AL implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        View v = View.getView();
        if (e.getSource() == v.getBtn_produceCommands()) {
            v.getCommandsOuput().setText(SimpleCommanding.getInstance().createConfig().toPrettyString());
        } else if (e.getSource() == v.getBtn_copyToClipboard()) {
            StringSelection stringSelection = new StringSelection(v.getCommandsOuput().getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } else if (e.getSource() == v.getBtn_saveCommandsToFile()) {

//            System.out.println(PropertiesFetch.get("DEFAULTS", "defaultPath"));
            JFileChooser fileChooser = new JFileChooser(PropertiesFetch.get("DEFAULTS", "defaultPath"));
            fileChooser.setSelectedFile(new File(PropertiesFetch.get("DEFAULTS", "defaultName")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CONFIG FILES", "cfg", "config");
            fileChooser.setFileFilter(filter);

            int outcome = fileChooser.showSaveDialog(View.getView());
            if (outcome == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
                    writer.write(v.getCommandsOuput().getText());
                    writer.close();
                    System.out.println("SUCCESS");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == v.getBtn_load()) {
            JFileChooser fileChooser = new JFileChooser(PropertiesFetch.get("DEFAULTS", "defaultPath"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CONFIG FILES", "cfg", "config");
            fileChooser.setFileFilter(filter);

            int outcome = fileChooser.showOpenDialog(View.getView());

            if (outcome == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                SimpleCommanding.getInstance().readConfig(Config.readConfigFile(file));
            }
        } else if (e.getSource() == v.getBtn_add_command()) {
            v.getTableModel().addRow(new Object[]{"NEW ROW", "NEW VALUE"});
        } else if (e.getSource() == v.getBtn_delete_other_command()) {
            v.getTableModel().removeRow(v.getOther_commands_table().getSelectedRow());
        } else if (e.getSource() == v.getBtn_edit_defaults()) {
            EditDefaults editDefaults = new EditDefaults();
            editDefaults.setVisible(true);
        }
    }
}
