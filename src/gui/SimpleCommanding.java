package gui;

import main.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter;
import java.util.HashMap;

public class SimpleCommanding {

    private static SimpleCommanding sc;

    public static SimpleCommanding getInstance() {
        if (sc == null)
            sc = new SimpleCommanding();

        return sc;
    }

    HashMap<String, Triple> keyToTriple;
    HashMap<Object, Triple> objectToTriple;

    private SimpleCommanding() {
        HashMap<String, Object> commandToObject = View.getView().getCommandToObject();

        keyToTriple = new HashMap<>();
        objectToTriple = new HashMap<>();


        for (String key : commandToObject.keySet()) {
            System.out.println("Key: " + key);
            Triple t = new Triple(key, commandToObject.get(key), "");
            keyToTriple.put(key, t);
            objectToTriple.put(t.getObj(), t);
        }
    }

    public void readConfig(Config config) {
        for (String key : config.getCommandKeys()) {
            try {
                keyToTriple.get(key).setValue(config.getCommand(key).getValue().toString());
            } catch (NullPointerException e) {
                System.out.println("No Object for: " + key);
            }
        }
    }

    public void refreshView() {
        for (Object obj : objectToTriple.keySet()) {
            if (obj instanceof JComboBox) {
                ((JComboBox) obj).setSelectedItem(objectToTriple.get(obj).getValue());
            } else if (obj instanceof JCheckBox) {
//                System.out.println("TEST: " + objectToTriple.get(obj).getValue());
                ((JCheckBox) obj).setSelected(objectToTriple.get(obj).getValue().equals("true") ? true : false);
            } else if (obj instanceof JTextField) {
                ((JTextField) obj).setText(objectToTriple.get(obj).getValue());
            } else {
                System.out.println(obj.toString());
                assert false;
            }
        }
    }

    public Config createConfig() {
        StringBuilder sb = new StringBuilder();

        for (Object obj : objectToTriple.keySet()) {
            Triple t = objectToTriple.get(obj);

            sb.append(t.getKey()).append(" ");

            if (obj instanceof JCheckBox) {
                if (((JCheckBox) obj).isSelected()) sb.append("1;").append("\n");
                else sb.append("0;").append("\n");
            } else if (obj instanceof JComboBox) {
                sb.append(((JComboBox) obj).getSelectedItem().toString()).append(";\n");
            } else if (obj instanceof JTextField) {
                sb.append(((JTextField) obj).getText()).append(";\n");
            } else {
                sb.append("ERROR").append(";\n");
            }
        }

        return Config.parseString(sb.toString());
    }
}

