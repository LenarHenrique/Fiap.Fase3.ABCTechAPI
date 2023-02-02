package br.com.fiap.abctechapi.component;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class VersionComponent {

    public String getVersion() throws IOException {
        Properties prop = new Properties();
        InputStream imput = getClass().getClassLoader().getResourceAsStream("application.yml");
        prop.load(imput);
        return prop.getProperty("build.version");
    }

    public String getProjectName() throws IOException {
        Properties prop = new Properties();
        InputStream imput = getClass().getClassLoader().getResourceAsStream("application.yml");
        prop.load(imput);
        return prop.getProperty("build.name");
    }
    public String getProjectVersio() throws IOException {
        Properties prop = new Properties();
        InputStream imput = getClass().getClassLoader().getResourceAsStream("application.yml");
        prop.load(imput);
        return prop.getProperty("build.name") + ": " + prop.getProperty("build.version");
    }
}
