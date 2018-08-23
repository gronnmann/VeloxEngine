package io.github.gronnmann.velox.adapters;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import io.github.gronnmann.velox.Engine;
import io.github.gronnmann.velox.EngineElement;
import io.github.gronnmann.velox.Print;

public class VeloxMouseAdapter extends MouseAdapter{
	
	private Engine engine;
	
	public VeloxMouseAdapter(Engine en) {
		this.engine = en;
	}

	public void mouseClicked(MouseEvent e) {
		Print.debug("Mouse click: X:" + e.getX() + ", Y: " + e.getY());
		for (EngineElement en : engine.getElements()) {
			en.mouseClicked(e);
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		for (EngineElement en : engine.getElements()) {
			en.mouseMoved(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		for (EngineElement en : engine.getElements()) {
			en.mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		for (EngineElement en : engine.getElements()) {
			en.mouseReleased(e);
		}
	}
	
	public void mouseWheelMoved(MouseEvent e) {
		Print.debug("Mouse wheel move: X:" + e.getX() + ", Y: " + e.getY());
		for (EngineElement en : engine.getElements()) {
			en.mouseWheelMoved(e);
		}
	}
}
