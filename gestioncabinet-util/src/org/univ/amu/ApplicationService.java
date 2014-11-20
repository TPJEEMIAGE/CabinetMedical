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

@Startup
@Singleton
public class ApplicationService {

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
	public void initialisation() throws IOException{
		String path = this.getClass().getResource("").toString();
		path = path.replaceAll("vfs:", "");
		path = path.replaceAll("org/univ/amu/", "META-INF/application.properties");
		File f = new File(path);
		// vfs:/C:/JBoss/standalone/deployments/gestioncabinet.ear/gestioncabinet-util.jar/
	
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
