package FastDevelopment;

import gui.Triple;
import gui.View;
import main.Commands.Command;
import main.Config;

import javax.swing.*;
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
//            System.out.println("Key: " + key);
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
                Command cmd = config.getCommand(key);
                View.getView().set(cmd.getKey(), cmd);
                Triple t = new Triple(key, null, "");
                t.setValue(config.getCommand(key).getValue().toString());
                keyToTriple.put(key, t);
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

        for (String key : keyToTriple.keySet()) {
            if (key.equals(""))
                continue;

            Triple t = keyToTriple.get(key);
            Object obj = t.getObj();

            sb.append(t.getKey()).append(" ");

            if (obj instanceof JCheckBox) {
                if (((JCheckBox) obj).isSelected()) sb.append("1;").append("\n");
                else sb.append("0;").append("\n");
            } else if (obj instanceof JComboBox) {
                String str = ((JComboBox) obj).getSelectedItem().toString();
                if (obj != View.getView().getCb_ammo_type() && str.equals(""))
                    str = "\"\"";
                sb.append(str).append(";\n");
            } else if (obj instanceof JTextField) {
                sb.append(((JTextField) obj).getText()).append(";\n");
            } else {
                View v = View.getView();
                int rowIndex = v.getRowOfComand(key);
                if (rowIndex != -1) {
                    Object value = v.getTableModel().getValueAt(rowIndex, 1);
                    if (value.toString().equals("false"))
                        sb.append("0").append(";\n");
                    else if (value.toString().equals("true"))
                        sb.append("1").append(";\n");
                    else
                        sb.append(value.toString()).append(";\n");
                }
            }
        }

        return Config.parseString(sb.toString());
    }
}

