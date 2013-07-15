package edu.hsbremen.cb.shaderama.renderer;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.util.vector.Vector3f;

import edu.hsbremen.cb.shaderama.core.ApplicationSettings;
import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.core.Manager;
import edu.hsbremen.cb.shaderama.input.InputManager;
import edu.hsbremen.cb.shaderama.scene.Camera;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.scene.Node;

public class RenderManager extends Manager {

	private Renderer renderer;
	private ApplicationSettings settings;
	private Node root;
	private boolean loaded = false;
	private Camera camera;
	private InputManager im;
	private Queue<Node> tempRoot;
	
	public RenderManager(Core core,ApplicationSettings settings, Renderer renderer, InputManager im) {
		super(core);
		this.settings = settings;
		this.renderer = renderer;
		this.im = im;
		this.camera = new Camera();
		this.root = new Node();
		this.tempRoot = new LinkedList<>();
	}

	private void setup() {
		System.out.println("Initialising Renderer.");
		renderer.createWindow(settings.r_width,settings.r_height);
		renderer.initContext();
	}
	
	@Override
	public void run() {
		setup();
		this.loaded = true;
		while(isRunning()) {
			handleScene();
			renderer.clear();
			if(root != null) {
				checkNodes();
				render();
			}
			renderer.update(settings.r_maxfps);
		}
		cleanup();
	}
	
	private void checkNodes() {
		while(!this.tempRoot.isEmpty()) {
			appendNode(this.tempRoot.poll());
		}
	}

	private void handleScene() {
		Vector3f moveDelta = this.im.nextMoveDelta();
		this.camera.translate(moveDelta.x, moveDelta.y, moveDelta.z);
		Vector3f rotationDelta = this.im.nextRotationDelta();
		this.camera.rotate(rotationDelta.x, 1, 0, 0);
		this.camera.rotate(rotationDelta.y, 0, 1, 0);
		
		if(renderer.isCloseRequested()) {
			this.im.close();
			this.close();
		}
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	private void render() {
		renderer.createPerspective();
		renderer.positionCamera(this.camera);
		renderer.lightDrive(); //fun method, delete later
		root.render(renderer);
	}
	
	public void addNode(Node node) {
		this.tempRoot.offer(node);
	}
	
	private void appendNode(Node node) {
		if(node instanceof Mesh) renderer.loadMesh((Mesh) node);
		this.root.add(node);
	}

	private void cleanup() {
		//TODO: implement me!
	}
	
}
