package io.github.gronnmann.velox.sprites;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
		this.img = new ImageIcon("res/" + img + ".png").getImage();
		this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.imgN = img;
	}
	
	public void rescale() {
		this.img = new ImageIcon("res/" + imgN + ".png").getImage();
		this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
