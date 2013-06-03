package edu.hsbremen.cb.shaderama.scene;

import org.lwjgl.util.vector.Matrix4f;



public class Mesh extends Node {

	private static final long serialVersionUID = 2071873898138598638L;
	
	private int id = -1;
	private int[] indices;
	private float[] vertices;
	private float[] normals;
	private Matrix4f prMatrix;
	
	public Mesh() {
		prMatrix = new Matrix4f();
	}
	
	public boolean isLoaded() {
		return id > -1 ? true : false; 
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public float[] getNormals() {
		return normals;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}

}
