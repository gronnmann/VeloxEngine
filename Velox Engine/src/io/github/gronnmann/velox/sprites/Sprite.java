package io.github.gronnmann.velox.sprites;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import io.github.gronnmann.velox.Print;

public class Sprite {
	public int moveX = 0, moveY = 0;
	public int x, y;
	public int width, height;
	protected Image img;
	private String imgN;
	
	protected boolean deadly = false;
	
	public Sprite(int x, int y, int width, int height, String img){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Print.debug("Sprite: Fetching image from location: /" + img + ".png");
		Print.debug("Bounds: " + x + ", " + y + ", " + width + ", " + height);
		this.img = new ImageIcon(getClass().getResource("/" + img + ".png")).getImage();
		this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.imgN = img;
	}
	
	public void rescale() {
		this.img = new ImageIcon(getClass().getResource("/" + img + ".png")).getImage();
		this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Print.debug("Rescaling sprite. New bounds: " + x + ", " + y + ", " + width + ", " + height);
	}
	
	public Image getImage(){
		return img;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void update(){
		processMovement();
	}
	
	protected void processMovement(){
		x+=moveX;
		y-=moveY;
	}
	
	public void deprocessMovement() {
		x-=moveX;
		y+=moveY;
	}
	
	public boolean isDeadly() {
		return deadly;
	}
	
	public boolean collidesWithAnother(ArrayList<Sprite> sprites){
		for (Sprite s : sprites){
			if (s != this){
				if (s.getBounds().intersects(this.getBounds()))return true;
			}
		}
		return false;
	}
	
	public Sprite getCollided(ArrayList<Sprite> sprites){
		for (Sprite s : sprites){
			if (s != this){
				if (s.getBounds().intersects(this.getBounds()))return s;
			}
		}
		return null;
	}
}
