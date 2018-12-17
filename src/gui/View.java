package gui;

import main.Commands.*;
import main.WeaponTypes.PrimaryWeaponType;
import main.WeaponTypes.SecondaryWeaponType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    private JPanel tab_weapons;
    private JPanel tab_basic_commands;
    private JPanel tab_others;
    private JButton btn_add_command;
    private JButton btn_delete_other_command;
    private JScrollPane jScrollPane;

    private DefaultTableModel tableModel;

    private static View view;
    private HashMap<String, Object> map;
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
        btn_produceCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : listOfAllObjects) {
                    if (obj instanceof JTextField) {
                        JTextField tf = ((JTextField) obj);
                        sb.append(tf.getToolTipText()).append(" ").append(tf.getText()).append(";\n");
                    } else if (obj instanceof JCheckBox) {
                        JCheckBox tf = ((JCheckBox) obj);
                        sb.append(tf.getToolTipText()).append(" ").append(tf.isSelected() ? 1 : 0).append(";\n");
                    } else if (obj instanceof JComboBox) {
                        System.out.println("PROBLEMS WITH " + obj + "\t " + ((JComboBox) obj).getToolTipText());
                        if (((JComboBox) obj).getToolTipText().equalsIgnoreCase("sv_infinite_ammo")) {
                            System.out.println("PROBLEM");
                        } else if (((JComboBox) obj).getToolTipText().equalsIgnoreCase("sv_infinite_ammo")) {
                            System.out.println("PROBLEM");
                        } else {
                            sb.append(((JComboBox) obj).getToolTipText()).append(" ").append("weapon_" + ((JComboBox) obj).getSelectedItem()).append(";\n");
                        }
                    }
                }
                System.out.println(sb.toString());
            }
        });
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
        map = new HashMap<>();
        map.put("mp_halftime_duration", tf_halftime_duration);
        map.put("mp_maxmoney", tf_maxmoney);
        map.put("mp_startmoney", tf_startmoney);
        map.put("mp_freezetime", tf_freezetime);
        map.put("mp_buytime", tf_buytime);
        map.put("mp_warmup_duration", tf_warmup_duration);
        map.put("mp_maxrounds", tf_max_rounds);
        map.put("sv_infinite_ammo", cb_ammo_type);
        map.put("mp_death_drop_gun", ch_death_drop_gun);
        map.put("mp_halftime", ch_halftime);
        map.put("mp_limitteams", tf_limit_teams);
        map.put("mp_roundtime", tf_roundttime);
        map.put("mp_autoteambalance", ch_auto_teambalance);
        map.put("mp_teammates_are_enemies", ch_free_for_all);
        map.put("mp_damage_headshot_only", ch_HS_only);
        map.put("mp_free_armor", ch_free_armor);

        map.put("mp_ct_default_primary", cb_weapon_primary_ct);
        map.put("mp_t_default_primary", cb_weapon_primary_t);
        map.put("mp_ct_default_secondary", cb_weapon_secondary_ct);
        map.put("mp_t_default_secondary", cb_weapon_secondary_t);

        map.put("sv_showimpacts", ch_show_impacts);
        map.put("sv_grenade_trajectory", ch_show_grenades);
        map.put("mp_buy_anywhere", ch_buy_anywhere);
        map.put("mp_do_warmup_period", ch_warmup);
        map.put("mp_buy_allow_grenades", ch_can_buy_grenades);
        map.put("ammo_grenade_limit_total", cb_nade_limit);

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
        this.add(rootPanel);
        this.setSize(new Dimension(635, 455));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void set(String key, Command cmd) {
        Object obj = map.get(key);
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
        for (String s : map.keySet())
            if (map.get(s) == obj)
                return s;

        return null;
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
     * Gets map
     *
     * @return value of $file.name
     */
    public HashMap<String, Object> getMap() {
        return map;
    }
}

