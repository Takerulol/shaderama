package edu.hsbremen.cb.shaderama.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import edu.hsbremen.cb.shaderama.scene.Mesh;
import edu.hsbremen.cb.shaderama.scene.MeshBuilder;

/**
 * WARNING: this loader does not implement the full obj specification
 * it can only load load triangle oder quad based obj files as a whole object
 * the class is also no completely type safe: dont try any funky obj files
 * 
 * @author TAKERU
 *
 */
public class ObjLoader {

	public static Mesh load(File file) throws IOException, FileNotFoundException {
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		MeshBuilder m = new MeshBuilder();
		
		String line;
		int type = -1;
		
		m.startMesh();
		while((line = reader.readLine()) != null) {
			
			//remove additional whitespaces that would conflict reading the line
			line = line.trim().replaceAll(" +", " ");
			
			//parse line
			if(line.startsWith("v ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);

				m.addVertex(new Vector3f(x,y,z));
			} else if (line.startsWith("vn ")) {
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);

				m.addNormal(new Vector3f(x,y,z));
			} else if (line.startsWith("vt ")) {	
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				
				m.addTexCoord(new Vector2f(x,y));
			} else if (line.startsWith("f ")) {
				String[] s = line.split(" ");

				if (type >= Mesh.TRIAGLES) {
					addFace(s, m);
				} else {
					type = checkForType(s);
					m.type(type);
					continue;
				}
			}
		}
		
		reader.close();
		return m.finishMesh();
	}

	private static void addFace(String[] s, MeshBuilder m) {
		int size = s.length - 1;
		int[] vertexIndices		= new int[size];
		int[] textureCoords		= new int[size];
		int[] normalIndices		= new int[size];
		String[] temp;
		for (int i = 0; i < size; i++) {
			temp = s[i+1].split("/");
			vertexIndices[i] = Integer.valueOf(temp[0]);
			if(temp.length > 1) textureCoords[i] = Integer.valueOf(temp[1]);
			if(temp.length > 2) normalIndices[i] = Integer.valueOf(temp[2]);
		}
		
		m.addFace(vertexIndices, normalIndices, textureCoords);
	}

	private static int checkForType(String[] s) {
		if (s.length == 4) return Mesh.TRIAGLES;
		if (s.length == 5) return Mesh.QUADS;
		return -1;
	}

}
