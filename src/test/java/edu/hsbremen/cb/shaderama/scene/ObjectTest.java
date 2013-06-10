package edu.hsbremen.cb.shaderama.scene;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector3f;

import edu.hsbremen.cb.shaderama.util.ObjLoader;
import edu.hsbremen.cb.shaderama.util.ResourceLoader;

public class ObjectTest {

	MeshBuilder mb;
	
	@Before
	public void setUp() throws Exception {
		mb = new MeshBuilder();
	}

	@Test
	public void testMesh() {
		Mesh mesh = new Mesh();
		assertNotNull(mesh.getVertices().add(new Vector3f(1f,2f,3f)));
	}
	
	@Test
	public void testLoading() {
		Mesh m;
		try {
			ResourceLoader r = new ResourceLoader();
			File f = r.getResource("/model/scene.obj");
			assertTrue(f.exists());
			m = ObjLoader.load(f);
			assertTrue(m.getVertices().size() > 0);
			assertTrue(m.getTexCoords().size() > 0);
			assertTrue(m.getNormals().size() > 0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("error in loading");
		} catch (IOException e) {
			e.printStackTrace();
			fail("error in loading");
		}
	}

}
