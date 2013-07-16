package edu.hsbremen.cb.shaderama;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.shader.Shader;
import edu.hsbremen.cb.shaderama.util.ObjLoader;
import edu.hsbremen.cb.shaderama.util.ResourceLoader;
import edu.hsbremen.cb.shaderama.util.ShaderLoader;

public final class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("some shader testorama!");
		Core core = new Core("lwjgl");
		core.start();
		
		System.out.println("Loading Scene.");
		
		//load mesh
		Mesh m1 = ObjLoader.load(ResourceLoader.getResource("model/test.obj"));
		
		//load shader
		File vs = ResourceLoader.getResource("shader/lambert.vs.glsl");
		File fs = ResourceLoader.getResource("shader/lambert.fs.glsl");
		Shader s1 = ShaderLoader.loadFromFile(vs, fs);
		
		//set shader and add mesh to the scene
		m1.setShader(s1);
		core.getRenderManager().addNode(m1);
		
		//extra scene object
//		Mesh m2 = ObjLoader.load(ResourceLoader.getResource("model/cube.obj"));
//		core.getRenderManager().addNode(m2);
	}

}
