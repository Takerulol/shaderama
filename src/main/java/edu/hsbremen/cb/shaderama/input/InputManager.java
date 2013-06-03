package edu.hsbremen.cb.shaderama.input;

import edu.hsbremen.cb.shaderama.core.ApplicationSettings;
import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.core.Manager;

public class InputManager extends Manager {

	private boolean running = true;
	private ApplicationSettings settings;
	
	public InputManager(Core core, ApplicationSettings settings) {
		super(core);
		this.settings = settings;
	}

	@Override
	public void run() {
		int skips = 1000 / settings.in_maxticks;
		while(running) {
			//TODO: implements me
		}
	}


}
