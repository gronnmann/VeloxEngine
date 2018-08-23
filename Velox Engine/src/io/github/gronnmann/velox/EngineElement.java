package io.github.gronnmann.velox;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface EngineElement {
	public void initialize();
	
	public void loop();
	
	public void drawMainMenu(Graphics2D g);
	public void drawBackground(Graphics2D g);
	public void drawSprites(Graphics2D g);
	public void drawOverlay(Graphics2D g);
	public void drawOverlay2(Graphics2D g);
	
	public void rescale(int width, int height);
	
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	
	public void mouseClicked(MouseEvent e);
	public void mouseMoved(MouseEvent e);
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseWheelMoved(MouseEvent e);
}
