package gui;

import main.Commands.IntegerCommand;
import main.Config;

public class GUILoader {

    public static void loadConfig(Config config) {
        View view = View.getView();

        view.setTf_halftime_duration(config.getCommand("mp_halftime_duration").getValue());
        view.setCb_ammo_type(getAmmoType(((IntegerCommand) config.getCommand("sv_infinite_ammo")).getValue()));

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

}
