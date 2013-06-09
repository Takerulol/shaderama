package edu.hsbremen.cb.shaderama;

public class SplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "f   5   2  3";
		s = s.trim().replaceAll(" +", " ");
		System.out.println(s.split(" ")[1]);
		System.out.println(s.split(" ")[1].split("/")[0]);
	}

}
