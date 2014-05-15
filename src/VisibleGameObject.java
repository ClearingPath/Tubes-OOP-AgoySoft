package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;

public abstract class VisibleGameObject
{
	private Sprite _sprite;
	private boolean _isLoaded;
	private int tile_posx;
	private int tile_posy;
	
	protected Sprite GetSprite(){
		return _sprite;
	}
	public VisibleGameObject(){
		_isLoaded=false;
		_sprite=new Sprite();
	}
	public abstract void Update(double elapsedTime);
	
	public void Load(String filename){
		try{
			_sprite.Load(filename);
			//_texture=ImageIO.read(getClass().getResource(filename));
			_isLoaded = true;
		} catch (IOException e){
			_isLoaded=false;
		}
	}
	public void Load(Image i){
		try{
			_sprite.Load(i);
			_isLoaded = true;
		} catch (IOException e){
			_isLoaded=false;
		}
	}
	
	public void Draw(Graphics2D g, ImageObserver IO){
		if(_isLoaded) {
			_sprite.Draw(g, IO);
		}
	}

	public static Point TiletoReal(Point p){
		Point p2=new Point();
		p2.x=Game.VIEW_POS_X+p.x*Game.TILE_SIZE_X+Game.TILE_SIZE_X/2;
		p2.y=Game.VIEW_POS_Y+p.y*Game.TILE_SIZE_Y+Game.TILE_SIZE_Y/2;
		return p2;
	}
	public static Point RealToTile(Point p){
		Point p2=new Point();
		p2.x=p.x-Game.VIEW_POS_X;
		p2.y=p.y-Game.VIEW_POS_Y;
		p2.x=(int) Math.floor(p2.x/Game.TILE_SIZE_X);
		p2.y=(int) Math.floor(p2.y/Game.TILE_SIZE_Y);
		return p2;
	}
	public void SetPosition(int tile_x, int tile_y){
		if(_isLoaded){
			tile_posx=tile_x;
			tile_posy=tile_y;
			_sprite.SetPosition(TiletoReal(new Point(tile_x, tile_y)));
		}
	}
	public Point GetPosition() {
		if(_isLoaded) {
			return new Point(tile_posx, tile_posy);
			//return _sprite.GetPosition();
		}
		return new Point(0,0);
	}
	public void UpdateSprite(double elapsedTime){
		_sprite.UpdateDraw(elapsedTime);
	}
	public double GetWidth(){
		return _sprite.getWidth();
	}
	public double GetHeight(){
		return _sprite.getHeight();
	}

	public Rectangle GetBoundingRect(){
		return _sprite.getBounds();
		//return _sprite.getGlobalBounds();
	}
	public boolean IsLoaded(){
		return _isLoaded;
	}
}