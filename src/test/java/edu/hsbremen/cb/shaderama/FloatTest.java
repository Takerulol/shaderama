package edu.hsbremen.cb.shaderama;

public class FloatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "f 12345//12345 12345//12345 12345//12345";
		String[] split = s.split("/");
		System.out.println(split.length);
	}

}
