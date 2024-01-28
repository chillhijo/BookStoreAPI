package util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public Properties properties = null;
    public PropertiesUtil(String file) throws IOException {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream(file));
    }

    public String getValue(String propertyKey) {
        String value = properties.getProperty(propertyKey);
        return value;
    }

    public String setValue(String propertyKey, String propertyValue) {
        String value = (String) properties.setProperty(propertyKey, propertyValue);
        return value;
    }
}
