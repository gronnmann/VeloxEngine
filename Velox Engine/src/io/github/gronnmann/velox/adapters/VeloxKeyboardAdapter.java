package io.github.gronnmann.velox.adapters;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import io.github.gronnmann.velox.Engine;
import io.github.gronnmann.velox.EngineElement;
import io.github.gronnmann.velox.Print;

public class VeloxKeyboardAdapter extends KeyAdapter{

	private Engine engine;
	
	public VeloxKeyboardAdapter(Engine en) {
		this.engine = en;
	}
	
	public void keyPressed(KeyEvent e){
		Print.debug("Keyboard press, ID: " + e.getKeyCode() + ", CHAR: " + e.getKeyChar());
		for (EngineElement en : engine.getElements()) {
			en.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e){
		Print.debug("Keyboard release, ID: " + e.getKeyCode() + ", CHAR: " + e.getKeyChar());
		for (EngineElement en : engine.getElements()) {
			en.keyReleased(e);
		}
	}
}
