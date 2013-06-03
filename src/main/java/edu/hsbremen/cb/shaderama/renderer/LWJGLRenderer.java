package edu.hsbremen.cb.shaderama.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import edu.hsbremen.cb.shaderama.scene.Mesh;

public class LWJGLRenderer implements Renderer {

	private boolean depthTest;

	@Override
	public void draw(Mesh mesh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMesh(Mesh mesh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unloadMesh(Mesh mesh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int maxfps) {
		if (maxfps > 0) Display.sync(maxfps);
		Display.update();
	}

	@Override
	public void clear() {
		if (depthTest) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		} else {
			glClear(GL_COLOR_BUFFER_BIT);
		}
	}

	@Override
	public void createWindow(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initContext() {
		glClearColor(0, 0, 0, 1);
		
	}

	@Override
	public void setDepthTest(boolean test) {
		this.depthTest = test;
	}

}
