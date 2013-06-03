package edu.hsbremen.cb.shaderama.core;

public abstract class Manager extends Thread {
	private boolean running = false;
	private Core core;
	
	public Manager(Core core) {
		this.core = core;
	}
	
	public Core getCore() {
		return core;
	}

	public void close() {
		running = false;
	}
	
	public abstract void run();
	
	public void start() {
		running = true;
		super.start();
	}
	
	public boolean isRunning() {
		return running;
	}
}
