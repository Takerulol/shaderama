package edu.hsbremen.cb.shaderama.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

import edu.hsbremen.cb.shaderama.scene.Mesh;

public class ObjLoader {

	public static Mesh load(File file) throws IOException, FileNotFoundException {
		//TODO: implement me
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		Mesh m = new Mesh();
		String line;
		
		while((line = reader.readLine()) != null) {
			
			//remove additional whitespaces that would conflict reading the line
			line = line.trim().replaceAll(" +", " ");
			
			//parse line
			if(line.startsWith("v ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				
				m.getVertices().add(new Vector3f(x,y,z));
			} else if (line.startsWith("vn ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				
				m.getNormals().add(new Vector3f(x,y,z));
			} else if (line.startsWith("f ")) {
				
			}
		}
		
		return null;
	}

}
