package edu.hsbremen.cb.shaderama.renderer;

import edu.hsbremen.cb.shaderama.scene.Camera;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.shader.Shader;

public interface Renderer {
	//mesh stuff
	void draw(Mesh mesh);
	void loadMesh(Mesh mesh);
	void unloadMesh(Mesh mesh);
	
	//camera
	void positionCamera(Camera camera);
	
	//shader stuff
	void loadShader(Shader shader);
	void unloadShader(Shader shader);
	void useShader(Shader shader);
	void unuseShader(Shader shader);
	
	//display stuff
	void update(int maxfps);
	void clear();
	void createWindow(int width, int height);
	boolean isCloseRequested();
	void initContext();
	void createPerspective();
	void createOrtho();
	
	//settings
	void setDepthTest(boolean test);
	void lightDrive();
	
	
}
