package edu.hsbremen.cb.shaderama.renderer;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.hsbremen.cb.shaderama.core.ApplicationSettings;
import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.core.Manager;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.scene.Node;
import edu.hsbremen.cb.shaderama.util.ObjLoader;
import edu.hsbremen.cb.shaderama.util.ResourceLoader;

public class RenderManager extends Manager {

	Renderer renderer;
	private ApplicationSettings settings;
	private Node root;
	
	
	public RenderManager(Core core,ApplicationSettings settings, Renderer renderer) {
		super(core);
		this.settings = settings;
		this.renderer = renderer;
		root = new Node();
	}

	private void setup() {
		renderer.createWindow(settings.r_width,settings.r_height);
		renderer.initContext();
		try {
			ResourceLoader r = new ResourceLoader();
			addNode(ObjLoader.load(r.getResource("model/zen_gold.obj")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		root.render(renderer);
	}
	
	public void addNode(Node node) {
		if(node instanceof Mesh) renderer.loadMesh((Mesh) node); 
		this.root.add(node);
	}

	private void cleanup() {
		//TODO: implement me!
	}
	
}
