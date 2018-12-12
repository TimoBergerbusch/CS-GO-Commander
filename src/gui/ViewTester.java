package gui;

import main.Config;

public class ViewTester {

    public static void main(String[] args) {
        View v = View.getView();
        v.setVisible(true);

        Config config = Config.getDefaultConfig();
        GUILoader.loadConfig(config);
    }
}
