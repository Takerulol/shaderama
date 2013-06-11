package edu.hsbremen.cb.shaderama.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import edu.hsbremen.cb.shaderama.scene.Face;
import edu.hsbremen.cb.shaderama.scene.Mesh;

public class LWJGLRenderer implements Renderer {

	private boolean depthTest;

	@Override
	public void draw(Mesh mesh) {
		if(mesh.isLoaded()) glCallList(mesh.getId());
	}

	@Override
	public void loadMesh(Mesh mesh) {
		if(!mesh.isLoaded()) load(mesh);
	}

	private void load(Mesh mesh) {
		int list = glGenLists(1);
		glNewList(list, GL_COMPILE);
			int type;
			if (mesh.getType() == Mesh.TRIAGLES)
				type = GL_TRIANGLES;
			else if (mesh.getType() == Mesh.QUADS)
				type = GL_QUADS;
			else
				throw new RuntimeException("critical error, no typ given -> someone hacked some shit in");
			int k = 0;
			glBegin(type);
				for(Face f : mesh.getIndices()) {
					for(int i = 0; i < mesh.getType(); i++) {
//						System.out.println(mesh.getNormals().size()+" "+mesh.getVertices().size()+" ["+(f.getVertexIndices()[i]-1)+"] "+mesh.getIndices().size());
						Vector3f v = mesh.getVertices().get(f.getVertexIndices()[i]-1);
						if(f.getNormalIndices()[i] != 0) {
							Vector3f n = mesh.getNormals().get(f.getNormalIndices()[i]-1);
							glNormal3f(n.x, n.y, n.z);
						} else {
							//TODO: calculate normals
						}
						
						if(f.getTextureCoords()[i] != 0) {
							Vector2f t = mesh.getTexCoords().get(f.getTextureCoords()[i]-1);
							glTexCoord2f(t.x, t.y);
						}
						
//						System.out.println("["+k+++"] vx "+v.x+" vy "+v.y+" vz "+v.z+" nx "+n.x+" ny "+n.y+" nz "+n.z+" tx "+t.x+" ty "+t.y);
						glVertex3f(v.x, v.y, v.z);
					}
				}
			glEnd();
		glEndList();
		mesh.setId(list);
	}

	@Override
	public void unloadMesh(Mesh mesh) {
		if(mesh.isLoaded()) {
			glDeleteLists(mesh.getId(), 1);
			mesh.setId(-1);
		}
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
//		glClearColor(0, 0, 0, 1);
//		glClear(GL_COLOR_BUFFER_BIT);
		glViewport(0, 0, 800, 600);
//		glEnable(GL_NORMALIZE);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc( GL_LESS);
		depthTest = true;
		glMatrixMode( GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(60, 1, 2, 20);
		glTranslatef(0, 0, -10);
//		GLU.gluOrtho2D(0, 800, 0, 600);
		glMatrixMode( GL_MODELVIEW);
		
		glClearColor(0, 0, 0, 1);
		
		
	}

	@Override
	public void setDepthTest(boolean test) {
		this.depthTest = test;
	}

}
