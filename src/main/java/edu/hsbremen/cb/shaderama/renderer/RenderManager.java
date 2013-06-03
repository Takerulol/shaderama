package edu.hsbremen.cb.shaderama.renderer;

import edu.hsbremen.cb.shaderama.core.ApplicationSettings;
import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.core.Manager;
import edu.hsbremen.cb.shaderama.scene.Node;

public class RenderManager extends Manager {

	Renderer renderer;
	private ApplicationSettings settings;
	private Node root;
	
	
	public RenderManager(Core core,ApplicationSettings settings, Renderer renderer) {
		super(core);
		this.settings = settings;
		this.renderer = renderer;
	}

	private void setup() {
		renderer.createWindow(settings.r_width,settings.r_height);
		renderer.initContext();
	}
	
	@Override
	public void run() {
		setup();
		while(isRunning()) {
			renderer.clear();
			if(root != null) render();
			renderer.update(settings.r_maxfps);
		}
		cleanup();
	}

	private void render() {
		
	}

	private void cleanup() {
		//TODO: implement me!
	}
	
}
