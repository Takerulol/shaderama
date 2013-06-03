package edu.hsbremen.cb.shaderama;

public class TestThread extends Thread {
	
	@Override
	public void run() {
		while(true) {
			try {
				wurst();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void wurst() throws Exception {
		throw new Exception("some error");
	}
}
