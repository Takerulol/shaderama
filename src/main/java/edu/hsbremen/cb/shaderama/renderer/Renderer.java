package edu.hsbremen.cb.shaderama.renderer;

import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.shader.Shader;

public interface Renderer {
	//mesh stuff
	void draw(Mesh mesh);
	void loadMesh(Mesh mesh);
	void unloadMesh(Mesh mesh);
	
	//shader stuff
	void loadShader(Shader shader);
	void unloadShader(Shader shader);
	void useShader();
	
	//display stuff
	void update(int maxfps);
	void clear();
	void createWindow(int width, int height);
	void initContext();
	
	//settings
	void setDepthTest(boolean test);
}
