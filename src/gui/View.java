package gui;

import FastDevelopment.AL;
import main.Commands.*;
import main.WeaponTypes.PrimaryWeaponType;
import main.WeaponTypes.SecondaryWeaponType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class View extends JFrame {
    private JPanel copyPanel;
    private JTextArea commandsOuput;
    private JButton btn_produceCommands;
    private JButton btn_copyToClipboard;
    private JButton btn_saveCommandsToFile;
    private JPanel rootPanel;


    private JTextField tf_halftime_duration;
    private JComboBox cb_ammo_type;
    private JCheckBox ch_warmup;
    private JTextField tf_warmup_duration;
    private JCheckBox ch_halftime;
    private JTextField tf_max_rounds;
    private JTextField tf_roundttime;
    private JTextField tf_maxmoney;
    private JTextField tf_startmoney;
    private JTextField tf_freezetime;
    private JTextField tf_buytime;
    private JCheckBox ch_buy_anywhere;
    private JCheckBox ch_can_buy_grenades;
    private JCheckBox ch_free_armor;
    private JComboBox cb_nade_limit;
    private JCheckBox ch_free_for_all;
    private JCheckBox ch_HS_only;
    private JCheckBox ch_show_impacts;
    private JCheckBox ch_show_grenades;
    private JComboBox cb_weapon_primary_t;
    private JComboBox cb_weapon_primary_ct;
    private JComboBox cb_weapon_secondary_t;
    private JComboBox cb_weapon_secondary_ct;
    private JCheckBox ch_flashbang_ct;
    private JCheckBox ch_flashbang_t;
    private JCheckBox ch_he_grenade_ct;
    private JCheckBox ch_he_grenade_t;
    private JCheckBox ch_molotov_ct;
    private JCheckBox ch_molotov_t;
    private JCheckBox ch_smoke_grenade_ct;
    private JCheckBox ch_smoke_grenade_t;
    private JTable other_commands_table;
    private JCheckBox ch_death_drop_gun;
    private JTextField tf_limit_teams;
    private JCheckBox ch_auto_teambalance;
    private JMenuItem mi_load_config;

    private JPanel tab_weapons;
    private JPanel tab_basic_commands;
    private JPanel tab_others;
    private JButton btn_add_command;
    private JButton btn_delete_other_command;
    private JScrollPane jScrollPane;
    private JButton btn_load;
    private JButton btn_edit_defaults;

    private DefaultTableModel tableModel;

    private static View view;
    public static String VERSION = "v0.1";
    private HashMap<String, Object> commandToObject;
    private ArrayList<Object> listOfAllObjects;

    public static View getView() {
        if (view == null)
            view = new View();
        return view;
    }

    private View() {
        this.initializeHashmap();
        this.initializeListOfObjects();

        this.initializeActionListener();

        this.setFrameBasics();
        this.setTableHeader();
        this.loadPrimarys();
        this.loadSecondarys();


    }

    private void initializeListOfObjects() {
        listOfAllObjects = new ArrayList<>();
        listOfAllObjects.addAll(Arrays.asList(new Object[]{
                tf_halftime_duration,
                cb_ammo_type,
                ch_warmup,
                tf_warmup_duration,
                ch_halftime,
                tf_max_rounds,
                tf_roundttime,
                tf_maxmoney,
                tf_startmoney,
                tf_freezetime,
                tf_buytime,
                ch_buy_anywhere,
                ch_can_buy_grenades,
                ch_free_armor,
                cb_nade_limit,
                ch_free_for_all,
                ch_HS_only,
                ch_show_impacts,
                ch_show_grenades,
                cb_weapon_primary_t,
                cb_weapon_primary_ct,
                cb_weapon_secondary_t,
                cb_weapon_secondary_ct,
                ch_flashbang_ct,
                ch_flashbang_t,
                ch_he_grenade_ct,
                ch_he_grenade_t,
                ch_molotov_ct,
                ch_molotov_t,
                ch_smoke_grenade_ct,
                ch_smoke_grenade_t,
                ch_death_drop_gun,
                tf_limit_teams,
                ch_auto_teambalance,}));
    }

    private void initializeActionListener() {
        AL al = new AL();
        btn_produceCommands.addActionListener(al);
        btn_copyToClipboard.addActionListener(al);
        btn_saveCommandsToFile.addActionListener(al);
        btn_load.addActionListener(al);

        btn_add_command.addActionListener(al);
        btn_delete_other_command.addActionListener(al);
        btn_edit_defaults.addActionListener(al);
    }

    private static String getAmmoType(int value) {
        switch (value) {
            case 2:
                return "+reload";
            case 1:
                return "inf.";
            case 0:
            default:
                return "default";
        }
    }

    private void initializeHashmap() {
        commandToObject = new LinkedHashMap<>();
        commandToObject.put("mp_halftime_duration", tf_halftime_duration);
        commandToObject.put("mp_maxmoney", tf_maxmoney);
        commandToObject.put("mp_startmoney", tf_startmoney);
        commandToObject.put("mp_freezetime", tf_freezetime);
        commandToObject.put("mp_buytime", tf_buytime);
        commandToObject.put("mp_warmup_duration", tf_warmup_duration);
        commandToObject.put("mp_maxrounds", tf_max_rounds);
        commandToObject.put("sv_infinite_ammo", cb_ammo_type);
        commandToObject.put("mp_death_drop_gun", ch_death_drop_gun);
        commandToObject.put("mp_halftime", ch_halftime);
        commandToObject.put("mp_limitteams", tf_limit_teams);
        commandToObject.put("mp_roundtime", tf_roundttime);
        commandToObject.put("mp_autoteambalance", ch_auto_teambalance);
        commandToObject.put("mp_teammates_are_enemies", ch_free_for_all);
        commandToObject.put("mp_damage_headshot_only", ch_HS_only);
        commandToObject.put("mp_free_armor", ch_free_armor);

        commandToObject.put("mp_ct_default_primary", cb_weapon_primary_ct);
        commandToObject.put("mp_t_default_primary", cb_weapon_primary_t);
        commandToObject.put("mp_ct_default_secondary", cb_weapon_secondary_ct);
        commandToObject.put("mp_t_default_secondary", cb_weapon_secondary_t);

        commandToObject.put("sv_showimpacts", ch_show_impacts);
        commandToObject.put("sv_grenade_trajectory", ch_show_grenades);
        commandToObject.put("mp_buy_anywhere", ch_buy_anywhere);
        commandToObject.put("mp_do_warmup_period", ch_warmup);
        commandToObject.put("mp_buy_allow_grenades", ch_can_buy_grenades);
        commandToObject.put("ammo_grenade_limit_total", cb_nade_limit);

    }

    private void loadPrimarys() {
//        cb_weapon_primary_ct.addItem("");
//        cb_weapon_primary_t.addItem("");
        for (PrimaryWeaponType weapon : PrimaryWeaponType.values()) {
            cb_weapon_primary_ct.addItem(weapon);
            cb_weapon_primary_t.addItem(weapon);
        }
    }

    private void loadSecondarys() {
//        cb_weapon_secondary_ct.addItem("");
//        cb_weapon_secondary_t.addItem("");
        for (SecondaryWeaponType weapon : SecondaryWeaponType.values()) {
            cb_weapon_secondary_ct.addItem(weapon);
            cb_weapon_secondary_t.addItem(weapon);
        }
    }

    private void setTableHeader() {
        String[] headerNames = new String[]{"Command", "Attribute(s)"};
        this.tableModel = new DefaultTableModel();
        this.tableModel.setColumnIdentifiers(headerNames);
        this.other_commands_table.setModel(this.tableModel);
    }

    private void setFrameBasics() {
        this.setTitle("CS:GO - Commander " + View.VERSION + " - by Timo Bergerbusch");
        this.add(rootPanel);
        this.setSize(new Dimension(635, 495));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void set(String key, Command cmd) {
        Object obj = commandToObject.get(key);
//        System.out.println("key: " + key + "\t cmd:" + cmd);
        if (obj == null) {
            tableModel.addRow(new Object[]{cmd.getKey(), cmd.getValue()});
        } else if (cmd instanceof PrimaryWeaponCommand || cmd instanceof SecondaryWeaponCommand) { //TODO utility
            ((JComboBox) obj).setSelectedItem(cmd.getValue());
        } else if (key.equalsIgnoreCase("sv_infinite_ammo")) {
            ((JComboBox) obj).setSelectedItem(getAmmoType(((IntegerCommand) cmd).getValue()));
        } else if (key.equalsIgnoreCase("ammo_grenade_limit_total")) {
            ((JComboBox) obj).setSelectedItem(cmd.getValue());
        } else if (cmd instanceof BooleanCommand) {
            ((JCheckBox) obj).setSelected(((BooleanCommand) cmd).getValue());
        } else {
            ((JTextField) obj).setText(cmd.getValue() + "");
        }
    }

    public int getRowOfComand(String key) {
        for (int i = 0; i < other_commands_table.getRowCount(); i++) {
            if (key.equals(tableModel.getValueAt(i, 0)))
                return i;
        }
        return -1;
    }

    // GETTER

    /**
     * Gets commandsOuput
     *
     * @return value of $file.name
     */
    public JTextArea getCommandsOuput() {
        return commandsOuput;
    }

    /**
     * Gets btn_produceCommands
     *
     * @return value of $file.name
     */
    public JButton getBtn_produceCommands() {
        return btn_produceCommands;
    }

    /**
     * Gets btn_copyToClipboard
     *
     * @return value of $file.name
     */
    public JButton getBtn_copyToClipboard() {
        return btn_copyToClipboard;
    }

    /**
     * Gets btn_saveCommandsToFile
     *
     * @return value of $file.name
     */
    public JButton getBtn_saveCommandsToFile() {
        return btn_saveCommandsToFile;
    }

    /**
     * Gets btn_add_command
     *
     * @return value of $file.name
     */
    public JButton getBtn_add_command() {
        return btn_add_command;
    }

    /**
     * Gets btn_delete_other_command
     *
     * @return value of $file.name
     */
    public JButton getBtn_delete_other_command() {
        return btn_delete_other_command;
    }

    /**
     * Gets tableModel
     *
     * @return value of $file.name
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Gets commandToObject
     *
     * @return value of $file.name
     */
    public HashMap<String, Object> getCommandToObject() {
        return commandToObject;
    }

    public JButton getBtn_edit_defaults() {
        return btn_edit_defaults;
    }

    /**
     * Gets btn_load
     *
     * @return value of $file.name
     */
    public JButton getBtn_load() {
        return btn_load;
    }

    /**
     * Gets other_commands_table
     *
     * @return value of $file.name
     */
    public JTable getOther_commands_table() {
        return other_commands_table;
    }

    /**
     * Gets cb_ammo_type
     *
     * @return value of $file.name
     */
    public JComboBox getCb_ammo_type() {
        return cb_ammo_type;
    }
}

