package br.com.fiap.abctechapi.component;

import br.com.fiap.abctechapi.model.Version;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class VersionComponent {

    private Properties prop;

    public VersionComponent(Properties prop) {
        this.prop = prop;
        load();
    }

    public VersionComponent(){
        this.prop = new Properties();
        load();
    }

    private void load(){
        try{
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.yml");
            prop.load(input);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public String getVersion()  {
        return this.prop.getProperty("build.version");
    }

    public String getProjectName()  {
        return this.prop.getProperty("build.name");
    }

    public Version getProjectVersion()  {
        Version version = new Version(getProjectName(), getVersion());
        return version;
    }
}
