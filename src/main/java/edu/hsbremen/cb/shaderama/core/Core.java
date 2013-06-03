package edu.hsbremen.cb.shaderama.core;

import edu.hsbremen.cb.shaderama.input.InputManager;
import edu.hsbremen.cb.shaderama.renderer.LWJGLRenderer;
import edu.hsbremen.cb.shaderama.renderer.RenderManager;
import edu.hsbremen.cb.shaderama.renderer.Renderer;

public class Core {
	
	private RenderManager rm;
	private InputManager im;
	private ApplicationSettings settings;
	
	public Core(String renderEngine) {
		//TODO: load from file
		settings = new ApplicationSettings();
		
		renderEngine.toLowerCase();
		Renderer renderer;
		switch (renderEngine) {
			case "lwjgl": renderer = new LWJGLRenderer(); break;
			default: renderer = new LWJGLRenderer();
		}
		this.rm = new RenderManager(this,settings,renderer);
		this.im = new InputManager(this,settings);
	}
	
	public void start() {
		rm.start();
		im.start();
	}

	public RenderManager getRenderManager() {
		return rm;
	}
}
