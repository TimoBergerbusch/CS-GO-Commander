package main;

import java.io.File;

public class View {

    public static void main(String[] args) {
//        main.Config cfg = main.Config.getDefaultConfig();
        Config.readConfigFile(new File("D:\\Games\\Steam\\steamapps\\common\\Counter-Strike Global Offensive\\csgo\\cfg\\aim_map_exec.cfg"));
    }
}
