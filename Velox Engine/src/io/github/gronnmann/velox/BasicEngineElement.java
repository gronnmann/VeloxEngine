package io.github.gronnmann.velox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import io.github.gronnmann.velox.sprites.Sprite;
import io.github.gronnmann.velox.sprites.SpriteRotatable;

public class BasicEngineElement implements EngineElement{
	
	private Engine engine;
	
	
	private Image background;
	
	private boolean showHitbox = false;
	
	private int scoreInt = 0;
	
	public BasicEngineElement(Engine en) {
		this.engine = en;
		
		background = new ImageIcon(getClass().getResource("/background.png")).getImage().getScaledInstance(engine.WIDTH, engine.HEIGHT, Image.SCALE_FAST);
	}
	
	
	@Override
	public void initialize() {
		
		
	}

	@Override
	public void loop() {
		
		ArrayList<Sprite> toRemove = new ArrayList<>();
		
		for (Sprite s : (ArrayList<Sprite>) engine.getSprites().clone()){
			s.update();
			
			if (s.getX() > engine.WIDTH*1.1 || s.getX() < 0){
				toRemove.add(s);
			}
			if (s.getY() > engine.HEIGHT*1.1 || s.getY() < 0){
				toRemove.add(s);
			}
		}
		engine.getSprites().removeAll(toRemove);
		
	}
	
	public void setScore(int score) {
		this.scoreInt = score;
	}
	public int getScore() {
		return scoreInt;
	}

	@Override
	public void drawMainMenu(Graphics2D g) {
		g.setColor(Color.WHITE);
		
		Font gameFont = new Font(Font.SANS_SERIF, Font.BOLD, 72);
		g.setFont(gameFont);
		
		String drawnBig = "Sample Engine Element";
		
		g.drawString(drawnBig, engine.WIDTH/2-36*drawnBig.length()/2, engine.HEIGHT/2-engine.HEIGHT/6);
		
		
		//START AND EXIT HINTS
		g.setColor(Color.YELLOW);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		String spaceToStart = "Please press SPACE to start the game.";
		String spaceToExit = "Please press ESCAPE to exit the game.";
		
		
		
		g.drawString(spaceToStart, engine.WIDTH/2-18*spaceToStart.length()/2, engine.HEIGHT/2);
		g.drawString(spaceToExit, engine.WIDTH/2-18*spaceToExit.length()/2, engine.HEIGHT/2+36);
		
		
	}

	@Override
	public void drawBackground(Graphics2D g) {
		g.drawImage(background, 0, 0, engine);
	}

	@Override
	public void drawSprites(Graphics2D g) {
		for (Sprite s : (ArrayList<Sprite>)engine.getSprites().clone()){
			
			
			if (s instanceof SpriteRotatable) {
				
				SpriteRotatable rot = (SpriteRotatable)s;
				
				AffineTransform rotateBackup = g.getTransform();
				
				AffineTransform newTransform = AffineTransform.getRotateInstance(Math.toRadians(rot.rotation), 
						rot.getX()+(rot.width/2), rot.getY()+(rot.height/2));
				
				g.setTransform(newTransform);
				
				g.drawImage(s.getImage(), s.getX(), s.getY(), engine);
				
				g.setTransform(rotateBackup);
				
			}else{
				g.drawImage(s.getImage(), s.getX(), s.getY(), engine);
			}
			
			if (showHitbox){
				if (s.isDeadly()){
					g.setColor(Color.RED);
				}else{
					g.setColor(Color.BLACK);
				}
				
				if (s.collidesWithAnother(engine.getSprites())){
					g.setColor(Color.YELLOW);
				}
				
				
				g.drawRect(s.x, s.y, (int)s.getBounds().getSize().getWidth(), (int)s.getBounds().getSize().getHeight());
			}
		}
	}

	@Override
	public void drawOverlay(Graphics2D g) {
		g.setColor(Color.YELLOW);
		
		int scoreFontSize = 36;
		String score = "Score: ";
		
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scoreFontSize));
		int howManySpaces = (int) Math.log10(scoreInt) + 1;
		
		g.drawString(score + scoreInt, engine.WIDTH-howManySpaces*scoreFontSize/2-scoreFontSize/2*(score.length()+1), scoreFontSize);
		
	}

	@Override
	public void drawOverlay2(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rescale(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseWheelMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
