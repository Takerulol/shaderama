package edu.hsbremen.cb.shaderama.scene;

import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f();
	private Vector3f rotation = new Vector3f();
	
	private Vector3f dPosition = new Vector3f();
	private Vector3f dRotation = new Vector3f();
	
	public void translate(float x, float y, float z) {
		position.x += x;
		position.y += y;
		position.z += z;
		
		dPosition.x += x;
		dPosition.y += y;
		dPosition.z += z;
	}
	
	public void translate(Vector3f vector) {
		translate(vector.x,vector.y,vector.z);
	}
	
	public void rotate(float angle, float x, float y, float z) {
		dRotation.x += x * angle;
		dRotation.y += y * angle;
		
		rotation.x += x * angle;
		if (rotation.x > 90) rotation.x = 90;
		if (rotation.x < -90) rotation.x = -90;
		rotation.y += y * angle;
		rotation.z += z * angle;
	}
	
	public Vector3f nextDeltaPosition() {		
		Vector3f temp = new Vector3f(dPosition);
		dPosition.set(0, 0, 0);
		return temp;
	}
	
	public Vector3f nextDeltaRotation() {
		Vector3f temp = new Vector3f(dRotation);
		dRotation.set(0, 0, 0);
		return temp;
	}
	
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
}
