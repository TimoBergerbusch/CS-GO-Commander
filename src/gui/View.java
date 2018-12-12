package gui;

import main.Commands.*;
import main.WeaponTypes.PrimaryWeaponType;
import main.WeaponTypes.SecondaryWeaponType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static View getView() {
        if (view == null)
            view = new View();
        return view;
    }

    private View() {
        this.initializeHashmap();

        this.loadActionListeners();

        this.setFrameBasics();
        this.setTableHeader();
        this.loadPrimarys();
        this.loadSecondarys();


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

    private void loadActionListeners() {
        btn_produceCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(view.getSize());
            }
        });
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
            System.out.println("TEST");
            ((JComboBox) obj).setSelectedItem(cmd.getValue());
        } else if (cmd instanceof BooleanCommand) {
            ((JCheckBox) obj).setSelected(((BooleanCommand) cmd).getValue());
        } else {
            ((JTextField) obj).setText(cmd.getValue() + "");
        }
    }
}

