package edu.hsbremen.cb.shaderama.scene;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Mesh extends Node {

	private static final long serialVersionUID = 2071873898138598638L;

	public static final int TRIAGLES  	= 0x01;
	public static final int QUADS 		= 0x02;
	
	private int id = -1;
	private int type = -1;
	private List<Face> indices = new ArrayList<>();
	public List<Vector3f> vertices = new ArrayList<>();
	private List<Vector3f> normals = new ArrayList<>();
	private List<Vector2f> texCoords = new ArrayList<>();
	
	public Mesh() {}

	private Matrix4f prMatrix = new Matrix4f();
	
	public List<Face> getIndices() {
		return indices;
	}

	public void setIndices(List<Face> indices) {
		this.indices = indices;
	}

	public List<Vector3f> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vector3f> vertices) {
		this.vertices = vertices;
	}

	public List<Vector3f> getNormals() {
		return normals;
	}

	public void setNormals(List<Vector3f> normals) {
		this.normals = normals;
	}

	public Matrix4f getPrMatrix() {
		return prMatrix;
	}

	public void setPrMatrix(Matrix4f prMatrix) {
		this.prMatrix = prMatrix;
	}
	
	public boolean isLoaded() {
		return id > -1 ? true : false; 
	}

	public List<Vector2f> getTexCoords() {
		return texCoords;
	}

	public void setTexCoords(List<Vector2f> texCoords) {
		this.texCoords = texCoords;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
