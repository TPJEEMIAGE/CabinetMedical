package org.univ.amu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

@Singleton
@Startup
public class ApplicationService {

	private static final String CONFIG_PATH = "/META-INF/application.properties";
	
	@Resource(name="org.univ.amu.logger")
	private Logger log;
	
	private Properties props = new Properties();
	
	public String getProperty(String key){
		return props.getProperty(key);
	}
	
	public Logger getLogger(){
		return log;
	}
	
	@PostConstruct
	private void initialisation() throws IOException{
		String path = this.getClass().getResource("").toString();
		path = path.replaceAll("vfs:", "");
		path = path.replaceAll("/org/univ/amu/", ApplicationService.CONFIG_PATH);
		File f = new File(path);
	
		if(f.exists()){
			InputStream p = new FileInputStream(f);
			props.load(p);
		}
		else
		{
			this.getLogger().error("Erreur : Le fichier properties n'est pas trouvé");
		}
	}
}
