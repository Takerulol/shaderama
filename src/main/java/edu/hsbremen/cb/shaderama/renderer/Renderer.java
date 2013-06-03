package edu.hsbremen.cb.shaderama.renderer;

import edu.hsbremen.cb.shaderama.scene.Mesh;

public interface Renderer {
	void draw(Mesh mesh);
	void loadMesh(Mesh mesh);
	void unloadMesh(Mesh mesh);
	void update(int maxfps);
	void clear();
	void createWindow(int width, int height);
	void initContext();
	void setDepthTest(boolean test);
}
