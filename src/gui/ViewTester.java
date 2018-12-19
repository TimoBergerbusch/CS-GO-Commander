package gui;

import FastDevelopment.SimpleCommanding;
import main.Config;

public class ViewTester {

    public static void main(String[] args) {
        View v = View.getView();
        v.setVisible(true);

//        Config config = Config.getDefaultConfig();
////        Controller.loadConfig(config);
//
//        SimpleCommanding sc = SimpleCommanding.getInstance();
//        sc.readConfig(config);
//        sc.refreshView();
//        System.out.println(sc.createConfig().toString());
    }
}
