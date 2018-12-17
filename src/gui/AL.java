package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class AL implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        View v = View.getView();
        if (e.getSource() == v.getBtn_produceCommands()) {
            v.getCommandsOuput().setText(SimpleCommanding.getInstance().createConfig().toString());
        } else if (e.getSource() == v.getBtn_copyToClipboard()){
            StringSelection stringSelection = new StringSelection(v.getCommandsOuput().getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } else if (e.getSource() == v.getBtn_saveCommandsToFile())
            System.out.println("NOT YET IMPLEMENTED");
    }
}
