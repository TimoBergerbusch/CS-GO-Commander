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
        for (PrimaryWeaponType weapon : PrimaryWeaponType.values()) {
            cb_weapon_primary_ct.addItem(weapon);
            cb_weapon_primary_t.addItem(weapon);
        }
    }

    private void loadSecondarys() {
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
        this.setSize(new Dimension(635, 455));
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

    public String getKeyToObject(Object obj) {
        for (String s : commandToObject.keySet())
            if (commandToObject.get(s) == obj)
                return s;

        return null;
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
     * Gets copyPanel
     *
     * @return value of $file.name
     */
    public JPanel getCopyPanel() {
        return copyPanel;
    }

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
     * Gets rootPanel
     *
     * @return value of $file.name
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Gets tf_halftime_duration
     *
     * @return value of $file.name
     */
    public JTextField getTf_halftime_duration() {
        return tf_halftime_duration;
    }

    /**
     * Gets cb_ammo_type
     *
     * @return value of $file.name
     */
    public JComboBox getCb_ammo_type() {
        return cb_ammo_type;
    }

    /**
     * Gets ch_warmup
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_warmup() {
        return ch_warmup;
    }

    /**
     * Gets tf_warmup_duration
     *
     * @return value of $file.name
     */
    public JTextField getTf_warmup_duration() {
        return tf_warmup_duration;
    }

    /**
     * Gets ch_halftime
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_halftime() {
        return ch_halftime;
    }

    /**
     * Gets tf_max_rounds
     *
     * @return value of $file.name
     */
    public JTextField getTf_max_rounds() {
        return tf_max_rounds;
    }

    /**
     * Gets tf_roundttime
     *
     * @return value of $file.name
     */
    public JTextField getTf_roundttime() {
        return tf_roundttime;
    }

    /**
     * Gets tf_maxmoney
     *
     * @return value of $file.name
     */
    public JTextField getTf_maxmoney() {
        return tf_maxmoney;
    }

    /**
     * Gets tf_startmoney
     *
     * @return value of $file.name
     */
    public JTextField getTf_startmoney() {
        return tf_startmoney;
    }

    /**
     * Gets tf_freezetime
     *
     * @return value of $file.name
     */
    public JTextField getTf_freezetime() {
        return tf_freezetime;
    }

    /**
     * Gets tf_buytime
     *
     * @return value of $file.name
     */
    public JTextField getTf_buytime() {
        return tf_buytime;
    }

    /**
     * Gets ch_buy_anywhere
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_buy_anywhere() {
        return ch_buy_anywhere;
    }

    /**
     * Gets ch_can_buy_grenades
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_can_buy_grenades() {
        return ch_can_buy_grenades;
    }

    /**
     * Gets ch_free_armor
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_free_armor() {
        return ch_free_armor;
    }

    /**
     * Gets cb_nade_limit
     *
     * @return value of $file.name
     */
    public JComboBox getCb_nade_limit() {
        return cb_nade_limit;
    }

    /**
     * Gets ch_free_for_all
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_free_for_all() {
        return ch_free_for_all;
    }

    /**
     * Gets ch_HS_only
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_HS_only() {
        return ch_HS_only;
    }

    /**
     * Gets ch_show_impacts
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_show_impacts() {
        return ch_show_impacts;
    }

    /**
     * Gets ch_show_grenades
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_show_grenades() {
        return ch_show_grenades;
    }

    /**
     * Gets cb_weapon_primary_t
     *
     * @return value of $file.name
     */
    public JComboBox getCb_weapon_primary_t() {
        return cb_weapon_primary_t;
    }

    /**
     * Gets cb_weapon_primary_ct
     *
     * @return value of $file.name
     */
    public JComboBox getCb_weapon_primary_ct() {
        return cb_weapon_primary_ct;
    }

    /**
     * Gets cb_weapon_secondary_t
     *
     * @return value of $file.name
     */
    public JComboBox getCb_weapon_secondary_t() {
        return cb_weapon_secondary_t;
    }

    /**
     * Gets cb_weapon_secondary_ct
     *
     * @return value of $file.name
     */
    public JComboBox getCb_weapon_secondary_ct() {
        return cb_weapon_secondary_ct;
    }

    /**
     * Gets ch_flashbang_ct
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_flashbang_ct() {
        return ch_flashbang_ct;
    }

    /**
     * Gets ch_flashbang_t
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_flashbang_t() {
        return ch_flashbang_t;
    }

    /**
     * Gets ch_he_grenade_ct
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_he_grenade_ct() {
        return ch_he_grenade_ct;
    }

    /**
     * Gets ch_he_grenade_t
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_he_grenade_t() {
        return ch_he_grenade_t;
    }

    /**
     * Gets ch_molotov_ct
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_molotov_ct() {
        return ch_molotov_ct;
    }

    /**
     * Gets ch_molotov_t
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_molotov_t() {
        return ch_molotov_t;
    }

    /**
     * Gets ch_smoke_grenade_ct
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_smoke_grenade_ct() {
        return ch_smoke_grenade_ct;
    }

    /**
     * Gets ch_smoke_grenade_t
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_smoke_grenade_t() {
        return ch_smoke_grenade_t;
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
     * Gets ch_death_drop_gun
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_death_drop_gun() {
        return ch_death_drop_gun;
    }

    /**
     * Gets tf_limit_teams
     *
     * @return value of $file.name
     */
    public JTextField getTf_limit_teams() {
        return tf_limit_teams;
    }

    /**
     * Gets ch_auto_teambalance
     *
     * @return value of $file.name
     */
    public JCheckBox getCh_auto_teambalance() {
        return ch_auto_teambalance;
    }

    /**
     * Gets tab_weapons
     *
     * @return value of $file.name
     */
    public JPanel getTab_weapons() {
        return tab_weapons;
    }

    /**
     * Gets tab_basic_commands
     *
     * @return value of $file.name
     */
    public JPanel getTab_basic_commands() {
        return tab_basic_commands;
    }

    /**
     * Gets tab_others
     *
     * @return value of $file.name
     */
    public JPanel getTab_others() {
        return tab_others;
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
     * Gets jScrollPane
     *
     * @return value of $file.name
     */
    public JScrollPane getjScrollPane() {
        return jScrollPane;
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

    /**
     * Sets $file.name to a new value
     *
     * @param copyPanel the new value
     */
    public void setCopyPanel(JPanel copyPanel) {
        this.copyPanel = copyPanel;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param commandsOuput the new value
     */
    public void setCommandsOuput(JTextArea commandsOuput) {
        this.commandsOuput = commandsOuput;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param btn_produceCommands the new value
     */
    public void setBtn_produceCommands(JButton btn_produceCommands) {
        this.btn_produceCommands = btn_produceCommands;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param btn_copyToClipboard the new value
     */
    public void setBtn_copyToClipboard(JButton btn_copyToClipboard) {
        this.btn_copyToClipboard = btn_copyToClipboard;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param btn_saveCommandsToFile the new value
     */
    public void setBtn_saveCommandsToFile(JButton btn_saveCommandsToFile) {
        this.btn_saveCommandsToFile = btn_saveCommandsToFile;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param rootPanel the new value
     */
    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_halftime_duration the new value
     */
    public void setTf_halftime_duration(JTextField tf_halftime_duration) {
        this.tf_halftime_duration = tf_halftime_duration;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_ammo_type the new value
     */
    public void setCb_ammo_type(JComboBox cb_ammo_type) {
        this.cb_ammo_type = cb_ammo_type;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_warmup the new value
     */
    public void setCh_warmup(JCheckBox ch_warmup) {
        this.ch_warmup = ch_warmup;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_warmup_duration the new value
     */
    public void setTf_warmup_duration(JTextField tf_warmup_duration) {
        this.tf_warmup_duration = tf_warmup_duration;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_halftime the new value
     */
    public void setCh_halftime(JCheckBox ch_halftime) {
        this.ch_halftime = ch_halftime;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_max_rounds the new value
     */
    public void setTf_max_rounds(JTextField tf_max_rounds) {
        this.tf_max_rounds = tf_max_rounds;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_roundttime the new value
     */
    public void setTf_roundttime(JTextField tf_roundttime) {
        this.tf_roundttime = tf_roundttime;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_maxmoney the new value
     */
    public void setTf_maxmoney(JTextField tf_maxmoney) {
        this.tf_maxmoney = tf_maxmoney;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_startmoney the new value
     */
    public void setTf_startmoney(JTextField tf_startmoney) {
        this.tf_startmoney = tf_startmoney;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_freezetime the new value
     */
    public void setTf_freezetime(JTextField tf_freezetime) {
        this.tf_freezetime = tf_freezetime;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_buytime the new value
     */
    public void setTf_buytime(JTextField tf_buytime) {
        this.tf_buytime = tf_buytime;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_buy_anywhere the new value
     */
    public void setCh_buy_anywhere(JCheckBox ch_buy_anywhere) {
        this.ch_buy_anywhere = ch_buy_anywhere;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_can_buy_grenades the new value
     */
    public void setCh_can_buy_grenades(JCheckBox ch_can_buy_grenades) {
        this.ch_can_buy_grenades = ch_can_buy_grenades;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_free_armor the new value
     */
    public void setCh_free_armor(JCheckBox ch_free_armor) {
        this.ch_free_armor = ch_free_armor;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_nade_limit the new value
     */
    public void setCb_nade_limit(JComboBox cb_nade_limit) {
        this.cb_nade_limit = cb_nade_limit;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_free_for_all the new value
     */
    public void setCh_free_for_all(JCheckBox ch_free_for_all) {
        this.ch_free_for_all = ch_free_for_all;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_HS_only the new value
     */
    public void setCh_HS_only(JCheckBox ch_HS_only) {
        this.ch_HS_only = ch_HS_only;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_show_impacts the new value
     */
    public void setCh_show_impacts(JCheckBox ch_show_impacts) {
        this.ch_show_impacts = ch_show_impacts;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_show_grenades the new value
     */
    public void setCh_show_grenades(JCheckBox ch_show_grenades) {
        this.ch_show_grenades = ch_show_grenades;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_weapon_primary_t the new value
     */
    public void setCb_weapon_primary_t(JComboBox cb_weapon_primary_t) {
        this.cb_weapon_primary_t = cb_weapon_primary_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_weapon_primary_ct the new value
     */
    public void setCb_weapon_primary_ct(JComboBox cb_weapon_primary_ct) {
        this.cb_weapon_primary_ct = cb_weapon_primary_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_weapon_secondary_t the new value
     */
    public void setCb_weapon_secondary_t(JComboBox cb_weapon_secondary_t) {
        this.cb_weapon_secondary_t = cb_weapon_secondary_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param cb_weapon_secondary_ct the new value
     */
    public void setCb_weapon_secondary_ct(JComboBox cb_weapon_secondary_ct) {
        this.cb_weapon_secondary_ct = cb_weapon_secondary_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_flashbang_ct the new value
     */
    public void setCh_flashbang_ct(JCheckBox ch_flashbang_ct) {
        this.ch_flashbang_ct = ch_flashbang_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_flashbang_t the new value
     */
    public void setCh_flashbang_t(JCheckBox ch_flashbang_t) {
        this.ch_flashbang_t = ch_flashbang_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_he_grenade_ct the new value
     */
    public void setCh_he_grenade_ct(JCheckBox ch_he_grenade_ct) {
        this.ch_he_grenade_ct = ch_he_grenade_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_he_grenade_t the new value
     */
    public void setCh_he_grenade_t(JCheckBox ch_he_grenade_t) {
        this.ch_he_grenade_t = ch_he_grenade_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_molotov_ct the new value
     */
    public void setCh_molotov_ct(JCheckBox ch_molotov_ct) {
        this.ch_molotov_ct = ch_molotov_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_molotov_t the new value
     */
    public void setCh_molotov_t(JCheckBox ch_molotov_t) {
        this.ch_molotov_t = ch_molotov_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_smoke_grenade_ct the new value
     */
    public void setCh_smoke_grenade_ct(JCheckBox ch_smoke_grenade_ct) {
        this.ch_smoke_grenade_ct = ch_smoke_grenade_ct;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_smoke_grenade_t the new value
     */
    public void setCh_smoke_grenade_t(JCheckBox ch_smoke_grenade_t) {
        this.ch_smoke_grenade_t = ch_smoke_grenade_t;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param other_commands_table the new value
     */
    public void setOther_commands_table(JTable other_commands_table) {
        this.other_commands_table = other_commands_table;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_death_drop_gun the new value
     */
    public void setCh_death_drop_gun(JCheckBox ch_death_drop_gun) {
        this.ch_death_drop_gun = ch_death_drop_gun;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tf_limit_teams the new value
     */
    public void setTf_limit_teams(JTextField tf_limit_teams) {
        this.tf_limit_teams = tf_limit_teams;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param ch_auto_teambalance the new value
     */
    public void setCh_auto_teambalance(JCheckBox ch_auto_teambalance) {
        this.ch_auto_teambalance = ch_auto_teambalance;
    }

    /**
     * Gets mi_load_config
     *
     * @return value of $file.name
     */
    public JMenuItem getMi_load_config() {
        return mi_load_config;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param mi_load_config the new value
     */
    public void setMi_load_config(JMenuItem mi_load_config) {
        this.mi_load_config = mi_load_config;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tab_weapons the new value
     */
    public void setTab_weapons(JPanel tab_weapons) {
        this.tab_weapons = tab_weapons;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tab_basic_commands the new value
     */
    public void setTab_basic_commands(JPanel tab_basic_commands) {
        this.tab_basic_commands = tab_basic_commands;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tab_others the new value
     */
    public void setTab_others(JPanel tab_others) {
        this.tab_others = tab_others;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param btn_add_command the new value
     */
    public void setBtn_add_command(JButton btn_add_command) {
        this.btn_add_command = btn_add_command;
    }

    public JButton getBtn_edit_defaults() {
        return btn_edit_defaults;
    }

    public void setBtn_edit_defaults(JButton btn_edit_defaults) {
        this.btn_edit_defaults = btn_edit_defaults;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param btn_delete_other_command the new value
     */
    public void setBtn_delete_other_command(JButton btn_delete_other_command) {
        this.btn_delete_other_command = btn_delete_other_command;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param jScrollPane the new value
     */
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
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
     * Sets $file.name to a new value
     *
     * @param btn_load the new value
     */
    public void setBtn_load(JButton btn_load) {
        this.btn_load = btn_load;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param tableModel the new value
     */
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param view the new value
     */
    public static void setView(View view) {
        View.view = view;
    }

    /**
     * Gets VERSION
     *
     * @return value of $file.name
     */
    public static String getVERSION() {
        return VERSION;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param VERSION the new value
     */
    public static void setVERSION(String VERSION) {
        View.VERSION = VERSION;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param commandToObject the new value
     */
    public void setCommandToObject(HashMap<String, Object> commandToObject) {
        this.commandToObject = commandToObject;
    }

    /**
     * Gets listOfAllObjects
     *
     * @return value of $file.name
     */
    public ArrayList<Object> getListOfAllObjects() {
        return listOfAllObjects;
    }

    /**
     * Sets $file.name to a new value
     *
     * @param listOfAllObjects the new value
     */
    public void setListOfAllObjects(ArrayList<Object> listOfAllObjects) {
        this.listOfAllObjects = listOfAllObjects;
    }
}

