package edu.hsbremen.cb.shaderama.scene;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;



public class Mesh extends Node {

	private static final long serialVersionUID = 2071873898138598638L;
	
	private int id = -1;
	private List<Integer> indices = new ArrayList<>();
	private List<Vector3f> vertices = new ArrayList<>();
	private List<Vector3f> normals = new ArrayList<>();

	private Matrix4f prMatrix = new Matrix4f();
	
	public List<Integer> getIndices() {
		return indices;
	}

	public void setIndices(List<Integer> indices) {
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

	
	public Mesh() {}
	
	public boolean isLoaded() {
		return id > -1 ? true : false; 
	}
	
	public class Face {
		
		private Vector3f vertexIndices;
		private Vector3f normalIndices;
		
		public Face(Vector3f vertexIndices, Vector3f normalIndices) {
			this.setVertexIndices(vertexIndices);
			this.setNormalIndices(normalIndices);
		}

		public Vector3f getVertexIndices() {
			return vertexIndices;
		}

		public void setVertexIndices(Vector3f vertexIndices) {
			this.vertexIndices = vertexIndices;
		}

		public Vector3f getNormalIndices() {
			return normalIndices;
		}

		public void setNormalIndices(Vector3f normalIndices) {
			this.normalIndices = normalIndices;
		}
		
		
	}
	

}
