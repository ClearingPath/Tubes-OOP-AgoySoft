package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.transform.Transformer;

abstract class VisibleGameObject
{
	protected Rectangle _sprite;
	protected Image _texture;
	private String _filename;
	private boolean _isLoaded;
	
	protected Rectangle GetSprite(){
		return _sprite;
	}
	public VisibleGameObject(){
		_isLoaded=false;
	}
	
	public void Load(String filename){
		try{
			_texture=ImageIO.read(getClass().getResource(filename));
			_filename = filename;
			_isLoaded = true;
		} catch (IOException e){
			_filename = "";
			_isLoaded=false;
		}
	}
	public void Draw(Graphics2D g, ImageObserver IO){
		if(_isLoaded) {
			g.drawImage(_texture, _sprite.x, _sprite.y, _sprite.x + _sprite.width, _sprite.y+_sprite.height,
					0, 0, _texture.getWidth(null), _texture.getHeight(null), IO);
			//g.drawImage(_texture, new AffineTran, IO)
		}
	}
	public abstract void Update(double elapsedTime);

	public void SetPosition(float x, float y){
		if(_isLoaded){
			Point p =new Point();
			p.setLocation(x, y);
			_sprite.setLocation(p);
		}
	}
	public Point GetPosition() {
		if(_isLoaded) {
			return _sprite.getLocation();
		}
		return new Point();
	}
	public float GetWidth(){
		return _texture.getWidth(null);
	}
	public float GetHeight(){
		return _texture.getHeight(null);
	}

	public Rectangle GetBoundingRect(){
		return _sprite.getBounds();
		//return _sprite.getGlobalBounds();
	}
	public boolean IsLoaded(){
		return _isLoaded;
	}
}