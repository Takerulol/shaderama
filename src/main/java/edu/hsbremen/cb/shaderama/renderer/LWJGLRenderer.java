package edu.hsbremen.cb.shaderama.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import edu.hsbremen.cb.shaderama.core.ApplicationSettings;
import edu.hsbremen.cb.shaderama.scene.Camera;
import edu.hsbremen.cb.shaderama.scene.Face;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.shader.Shader;

public class LWJGLRenderer implements Renderer {

	private ApplicationSettings settings;
	private boolean depthTest;
	private float count;
	private boolean lightDir;
	
	public LWJGLRenderer(ApplicationSettings settings) {
		this.settings = settings;
	}
	
	

	@Override
	public void draw(Mesh mesh) {
		if (mesh.isLoaded()) {
			if (mesh.hasShader())
				glUseProgram(mesh.getShader().getShaderProgram());
			glCallList(mesh.getId());
			if (mesh.hasShader())
				glUseProgram(0);
		}
	}

	@Override
	public void loadMesh(Mesh mesh) {
		if (!mesh.isLoaded())
			load(mesh);
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
			throw new RuntimeException(
					"critical error, no typ given -> someone hacked some shit in");
		
		glBegin(type);
		
		for (Face f : mesh.getIndices()) {
			for (int i = 0; i < 3; i++) {
//				System.out.println(mesh.getNormals().size()+" "+mesh.getVertices().size()+" ["+(f.getVertexIndices()[i]-1)+"] "+mesh.getIndices().size());
				Vector3f v = mesh.getVertices()
						.get(f.getVertexIndices()[i] - 1);
				if (f.getNormalIndices()[i] != 0) {
					Vector3f n = mesh.getNormals().get(
							f.getNormalIndices()[i] - 1);
					glNormal3f(n.x, n.y, n.z);
				} else {
					// TODO: calculate normals
				}

				if (f.getTextureCoords()[i] != 0) {
					Vector2f t = mesh.getTexCoords().get(
							f.getTextureCoords()[i] - 1);
					glTexCoord2f(t.x, t.y);
				}

				// System.out.println("["+k+++"] vx "+v.x+" vy "+v.y+" vz "+v.z+" nx "+n.x+" ny "+n.y+" nz "+n.z+" tx "+t.x+" ty "+t.y);
//				
//				glColor3d(Math.abs(v.x/10f),Math.abs(v.y/10f),Math.abs(v.z/10f));
				glColor3f(1f, 1f, 1f);
				glVertex3f(v.x, v.y, v.z);
			}
		}
		glEnd();
		glEndList();
		mesh.setId(list);
		
		Shader shader;
		if(mesh.hasShader()) {
			shader = mesh.getShader();
			loadShader(shader);
		}
	}

	@Override
	public void unloadMesh(Mesh mesh) {
		if (mesh.isLoaded()) {
			glDeleteLists(mesh.getId(), 1);
			mesh.setId(-1);
		}
	}

	@Override
	public void update(int maxfps) {
		if (maxfps > 0)
			Display.sync(maxfps);
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
		// glClearColor(0, 0, 0, 1);
		// glClear(GL_COLOR_BUFFER_BIT);
		glViewport(0, 0, settings.r_width, settings.r_height);
		// glEnable(GL_NORMALIZE);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LESS);
		depthTest = true;
		createPerspective();
		glEnable(GL_LIGHTING);
		float[] direction = { 2.0f, 2.0f, 4.0f, 1f};

		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(direction);
		position.flip();
		
		float[] spotDirection = {-2.0f, -2.0f, -4.0f , 0f};

		FloatBuffer dirr = BufferUtils.createFloatBuffer(4);
		dirr.put(spotDirection);
		dirr.flip();

		glLoadIdentity();
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_POSITION, position);
		glLight(GL_LIGHT0, GL_SPOT_DIRECTION, dirr);

		glClearColor(0, 0, 0, 1);

	}

	@Override
	public void createPerspective() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(60, (float)settings.r_width / (float)settings.r_height, 0.1f, 50f);
		glMatrixMode(GL_MODELVIEW);
	}
	
	@Override
	public void createOrtho() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, settings.r_width , settings.r_height , 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	@Override
	public void setDepthTest(boolean test) {
		this.depthTest = test;
	}
	
	@Override
	public void lightDrive() {
		float[] direction = { -10f + count * 0.01f, 2.0f, 4.0f, 1f};
		if(lightDir) {
			count++;
			if(count > 1000) lightDir = !lightDir;
		} else {
			count--;
			if(count < 0) lightDir = !lightDir;
		}
		
		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(direction);
		position.flip();

		glLoadIdentity();
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_POSITION, position);
	}

	@Override
	public void loadShader(Shader shader) {
		
		int shaderProgram = glCreateProgram();
		shader.setShaderProgram(shaderProgram);
		
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertexShader, shader.getVertexShaderSource());
		glCompileShader(vertexShader);
		if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err
					.println("Vertex shader wasn't able to be compiled correctly.");
			shader.setShaderProgram(0);
		}
		glShaderSource(fragmentShader, shader.getFragmentShaderSource());
		glCompileShader(fragmentShader);
		if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err
					.println("Fragment shader wasn't able to be compiled correctly.");
			shader.setShaderProgram(0);
		}
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}

	@Override
	public void unloadShader(Shader shader) {
		glDeleteProgram(shader.getShaderProgram());
		glDeleteShader(shader.getVertexShaderProgram());
		glDeleteShader(shader.getFragmentShaderProgram());
	}

	@Override
	public void useShader(Shader shader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unuseShader(Shader shader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void positionCamera(Camera camera) {
		glMatrixMode(GL_PROJECTION);
//		Vector3f pos = camera.nextDeltaPosition();
//		Vector3f rot = camera.nextDeltaRotation();
		
		Vector3f pos = camera.getPosition();
		Vector3f rot = camera.getRotation();
		glTranslatef(pos.x, pos.y, pos.z);
		glRotatef(rot.x, 1, 0, 0);
		glRotatef(rot.y, 0, 1, 0);
		
		glRotatef(0, 0, 0, 0); //TODO: wäre neigung der camera.
		glMatrixMode(GL_MODELVIEW);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//##########################################################################
	//#                              alter Code                                #
	//##########################################################################
	public void loadShaderOld(Shader shader) {
		boolean success=true;
		
		//create program object
		int program = ARBShaderObjects.glCreateProgramObjectARB();
		int vertShader = 0;
		int fragShader = 0;        
        
       //vertShader will be non zero if succefully created

        vertShader=ARBShaderObjects.glCreateShaderObjectARB(ARBVertexShader.GL_VERTEX_SHADER_ARB);
        

        ARBShaderObjects.glShaderSourceARB(vertShader, shader.getVertexShaderSource());
        ARBShaderObjects.glCompileShaderARB(vertShader);
        
        if(!printLogInfo(vertShader)){
            vertShader=0;
        }

        fragShader = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
        if(fragShader==0) success = false;
       
        ARBShaderObjects.glShaderSourceARB(fragShader, shader.getFragmentShaderSource());
        ARBShaderObjects.glCompileShaderARB(fragShader);
        
        if(!printLogInfo(fragShader)){
            fragShader=0;
        }
        
        //if vert+frag shader created bind them to the program
        if(vertShader !=0 && fragShader !=0){
            ARBShaderObjects.glAttachObjectARB(program, vertShader);
            ARBShaderObjects.glAttachObjectARB(program, fragShader);
            ARBShaderObjects.glLinkProgramARB(program);
            ARBShaderObjects.glValidateProgramARB(program);
            success=printLogInfo(program);
        }else success=false;
        
        shader.setShaderProgram(program);
        shader.setFragmentShaderProgram(fragShader);
        shader.setVertexShaderProgram(vertShader);
        
        if(!success){
        	//do something else
        }
	}
	
	private static boolean printLogInfo(int obj){
        IntBuffer iVal = BufferUtils.createIntBuffer(1);
        ARBShaderObjects.glGetObjectParameterARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB, iVal);

        int length = iVal.get();
        if (length > 1) {
            // We have some info we need to output.
            ByteBuffer infoLog = BufferUtils.createByteBuffer(length);
            iVal.flip();
            ARBShaderObjects.glGetInfoLogARB(obj, iVal, infoLog);
            byte[] infoBytes = new byte[length];
            infoLog.get(infoBytes);
            String out = new String(infoBytes);
            System.out.println("Info log:\n"+out);
        }
        else return false;
        return true;
    }

}
