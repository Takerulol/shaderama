package edu.hsbremen.cb.shaderama.scene;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class MeshBuilder {
	
	private Mesh mesh;
	
	public MeshBuilder() {
	}
	
	public void startMesh() {
		mesh = new Mesh();
	}
	
	public void type(int type) {
		mesh.setType(type);
	}
	
	public void addVertex(Vector3f vector) {
		mesh.getVertices().add(vector);
	}
	
	public void addNormal(Vector3f vector) {
		mesh.getNormals().add(vector);
	}
	
	public void addTexCoord(Vector2f vector) {
		mesh.getTexCoords().add(vector);
	}
	
	public void addFace(int[] vertexIndices, int[] normalIndices, int[] textureCoords) {
		mesh.getIndices().add(new Face(vertexIndices, normalIndices, textureCoords));
	}
	
	public Mesh finishMesh() {
		return mesh;
	}
}
