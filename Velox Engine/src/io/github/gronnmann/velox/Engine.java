package io.github.gronnmann.velox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.gronnmann.velox.adapters.VeloxKeyboardAdapter;
import io.github.gronnmann.velox.adapters.VeloxMouseAdapter;
import io.github.gronnmann.velox.sprites.Sprite;
import io.github.gronnmann.velox.sprites.SpriteRotatable;

public class Engine extends JPanel{
	
	public int WIDTH, HEIGHT, DEFAULT_WIDTH, DEFAULT_HEIGHT;
	
	private EngineRunner t;
	
	private boolean fullScreen = false;
	
	private VeloxKeyboardAdapter keyAdapter;
	private VeloxMouseAdapter mouseAdapter;
	
	private int gameState;
	
	
	private ArrayList<EngineElement> elements = new ArrayList<>();
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	private JFrame window;
	
	private static boolean debug = false;
	
	
	
	public Engine(int width, int height){
		
		
		WIDTH = width;
		HEIGHT = height;
		
		DEFAULT_WIDTH = width;
		DEFAULT_HEIGHT = height;
		
		this.keyAdapter = new VeloxKeyboardAdapter(this);
		this.mouseAdapter = new VeloxMouseAdapter(this);
		
		
		Print.info("Creating Velox Engine...");
		Print.info("Presets: " + width + "x" + height);
	}
	
	
	public void addElement(EngineElement e) {
		elements.add(e);
	}
	public void removeElement(EngineElement e) {
		elements.remove(e);
	}
	public ArrayList<EngineElement> getElements(){
		return elements;
	}
	
	public void initialize(JFrame frame, int tickRate) {
		
		
		t = new EngineRunner(this);
		
		new Timer().scheduleAtFixedRate(t, 0, 1000/tickRate);
		
		this.window = frame;
		
		frame.add(this);
		
		frame.addKeyListener(keyAdapter);
		frame.addMouseListener(mouseAdapter);
		
		for (EngineElement e : elements) {
			e.initialize();
		}
		
		Print.info("Started Velox Engine for frame " + frame.getName() + ". Tickrate: " + tickRate);
	}
	
	
	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D)gr;
		
		for (EngineElement e : elements) {
			e.drawBackground(g);			
			e.drawSprites(g);
			e.drawOverlay(g);
			e.drawOverlay2(g);
			e.drawMainMenu(g);
		}
		
		
	}
	
	public void fullScreenToggle() {
		
		Print.info("Toggled fullscreen.");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = DEFAULT_WIDTH;
		int h = DEFAULT_HEIGHT;
		
		if (!fullScreen) {
			
			w = (int) screenSize.getWidth();
			h = (int) screenSize.getHeight();
			
			window.setLocation(0, 0);
		}else {
			window.setLocation(((int)screenSize.getWidth() / 2 - DEFAULT_WIDTH/2), (int)screenSize.getHeight() / 2 - DEFAULT_HEIGHT/2);
		}
		
		this.WIDTH = w;
		this.HEIGHT = h;
		
		window.setSize(w, h);
		
		for (EngineElement e : elements) {
			e.rescale(w, h);
		}
		
		
		fullScreen = !fullScreen;
	}
	
	public int getGameState() {
		return gameState;
	}
	public void setGameState(int newState) {
		gameState = newState;
		Print.debug("Sat game state: " + newState);
	}
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
	public void addSprite(Sprite s){
		sprites.add(s);
		Print.debug("Added sprite: " + s);
	}
	
	public void removeSprite(Sprite s){
		sprites.remove(s);
		Print.debug("Added sprite: " + s);
	}
	
	public static void setDebug(boolean d) {
		debug = d;
	}
	public static boolean getDebug() {
		return debug;
	}
	public static void toggleDebug() {
		debug = !debug;
	}

}

class EngineRunner extends TimerTask{
	
	private Engine en;
	
	public EngineRunner(Engine en) {
		this.en = en;
	}
	
	@Override
	public void run() {
		for (EngineElement e : en.getElements()) {
			e.loop();
		}
		en.repaint();
	}
	
}
