package gui;

import main.Commands.Command;
import main.Config;

import java.util.ArrayList;

public class Controller {

    public static void loadConfig(Config config) {
        View view = View.getView();

        for (String commandName : config.getCommandKeys()) {
            setValue(view, config, commandName);
        }
    }

    private static void setValue(View view, Config config, String key) {
        view.set(key, config.getCommand(key));
    }

}
