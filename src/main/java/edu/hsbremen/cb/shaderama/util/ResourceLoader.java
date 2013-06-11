package edu.hsbremen.cb.shaderama.util;

import java.io.File;
import java.net.URL;

public class ResourceLoader  {

	//TODO: actual resource handling
	
	public ResourceLoader() {}
	
	public File getResource(String path) {
		URL url = ResourceLoader.class.getClassLoader().getResource(path);
		File f = new File(url.getPath());
		return f;
	}
	
}
