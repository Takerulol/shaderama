package edu.hsbremen.cb.shaderama.renderer;

import edu.hsbremen.cb.shaderama.scene.Camera;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.shader.Shader;

public interface Renderer {
	//mesh stuff
	void draw(Mesh mesh);
	void loadMesh(Mesh mesh);
	void unloadMesh(Mesh mesh);
	
	//camera/view
	void positionCamera(Camera camera);
	void createPerspective();
	void createOrtho();
	
	//shader stuff
	void loadShader(Shader shader);
	void unloadShader(Shader shader);
	void useShader(Shader shader);
	
	//display stuff
	void update(int maxfps);
	void clear();
	void createWindow(int width, int height);
	boolean isCloseRequested();
	void initContext();

	//settings
	void setDepthTest(boolean test);
	
	//random stuff
	void lightDrive();
}
