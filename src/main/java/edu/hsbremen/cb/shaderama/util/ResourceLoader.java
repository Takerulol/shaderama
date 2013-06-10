package edu.hsbremen.cb.shaderama.util;

import java.io.File;
import java.net.URL;

public class ResourceLoader  {

	private Loader loader = new Loader();
	
	//TODO: actual resource handling
	
	public ResourceLoader() {}
	
	public File getResource(String path) {
		URL url = ResourceLoader.class.getClassLoader().getResource("model/scene.obj");
		File f = new File(url.getPath());
		return f;
	}
	
	private class Loader extends ClassLoader {
		
	}
}
