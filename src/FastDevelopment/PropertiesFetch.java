package FastDevelopment;

import org.ini4j.Ini;

import java.io.IOException;

public class PropertiesFetch {

    public static PropertiesFetch getInstance() {
        if (fetch == null)
            fetch = new PropertiesFetch();
        return fetch;
    }

    private static PropertiesFetch fetch;

    private Ini properties;

    private PropertiesFetch() {
        this.load();
    }

    private void load() {
//        File prop = new File(System.getProperty("user.dir") + "\\csgo-commander " + View.VERSION + ".jar\\FastDevelopment\\properties.ini");
//        File prop = new File(System.getProperty("user.dir") + "\\src\\FastDevelopment\\properties.ini");
        try {
//            System.out.println(this.getClass().getResourceAsStream("properties.ini"));
            properties = new Ini(this.getClass().getResourceAsStream("properties.ini"));
//            properties = new Ini(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String get(String section, String key) {
        return getInstance().properties.get(section, key);
    }

    public static String set(String section, String key, String value) {
        return getInstance().properties.put(section, key, value);
    }

    public static void revalidate() {
        try {
            getInstance().properties.store();
            getInstance().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
