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
public class ApplicationService {

	@Resource(name="org.univ.amu.logger")
	private Logger log;
	
	private Properties props;
	
	public String getProperty(String key){
		return props.getProperty(key);
	}
	
	public Logger getLogger(){
		return log;
	}
	
	@PostConstruct
	public void initialisation() throws IOException{
		String path = this.getClass().getResource("").toString();
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
