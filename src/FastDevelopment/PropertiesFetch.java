package FastDevelopment;

import org.ini4j.Ini;

import java.io.File;
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
        File prop = new File(System.getProperty("user.dir")+"\\src\\FastDevelopment\\properties.ini");
        try {
            properties = new Ini(prop);
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
}
