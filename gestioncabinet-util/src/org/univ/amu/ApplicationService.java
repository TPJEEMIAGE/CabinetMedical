package org.univ.amu;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Singleton;

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
}
