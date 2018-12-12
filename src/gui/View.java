package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    private JPanel copyPanel;
    private JTextArea commandsOuput;
    private JButton btn_produceCommands;
    private JButton btn_copyToClipboard;
    private JButton btn_saveCommandsToFile;
    private JPanel rootPanel;
    private JPanel tab_weapons;

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
    private JPanel tab_basic_commands;
    private JPanel tab_others;
    private JButton btn_add_command;
    private JButton btn_delete_other_command;
    private JScrollPane jScrollPane;
    private JCheckBox checkBox1;
    private JTable other_commands_table;

    private DefaultTableModel tableModel;

    private static View view;

    public static View getView() {
        if (view == null)
            view = new View();
        return view;
    }

    private View() {
        this.setFrameBasics();
        this.setTableHeader();
    }

    private void setFrameBasics() {
        this.add(rootPanel);
        this.setSize(new Dimension(700, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void setTableHeader() {
        String[] headerNames = new String[]{"Command", "Attribute(s)"};
        this.tableModel = new DefaultTableModel();
        this.tableModel.setColumnIdentifiers(headerNames);
        this.other_commands_table.setModel(this.tableModel);
    }

    // GETTER AND SETTER

    public void setTf_halftime_duration(Object value) {
        this.tf_halftime_duration.setText(value.toString());
    }

    public void setCb_ammo_type(String value){
        this.cb_ammo_type.setSelectedItem(value);
    }
}
