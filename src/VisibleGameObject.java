
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
*
* @author M. Ilmi
*/

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
	public abstract void Update(long elapsedTime);
	
	public void Load(String filename){
		try{
			_sprite.Load(filename);
			_isLoaded = true;
		} catch (IOException e){
			_isLoaded=false;
		}
	}
	public void Load(BufferedImage i){
		try{
			_sprite.Load(i);
			_isLoaded = true;
		} catch(IOException e){
			_isLoaded=false;
		}
	}
	
	public void Draw(Graphics2D g, ImageObserver IO){
		if(_isLoaded) {
			// TODO handle item with width more than one tile
			// rectangle untuk peta yang kelihatan
			Rectangle a=new Rectangle();
			a.x=Utilities.VIEW_TILE_X;
			a.y=Utilities.VIEW_TILE_Y;
			a.height=Utilities.VIEW_ROW_COUNT;
			a.width=Utilities.VIEW_COL_COUNT;
			// rectangle untuk object bersangkutan
			Rectangle b=new Rectangle();
			b.x=tile_posx;
			b.y=tile_posy;
			b.height=_sprite.getTileHeight();
			b.width=_sprite.getTileWidth();
			if (a.intersects(b)){
				//cek keliatan ato tidak
				_sprite.Draw(g, IO);
			}
		}
	}

	public static Point TiletoReal(Point p){
		Point p2=new Point();
		p2.x=(int)Utilities.VIEW_POS_X+p.x*Utilities.TILE_SIZE_X;
		p2.y=(int)Utilities.VIEW_POS_Y+p.y*Utilities.TILE_SIZE_Y;
		return p2;
	}
	public static Point RealToTile(Point p){
		Point p2=new Point();
		p2.x=p.x-(int)(Utilities.VIEW_POS_X);
		p2.y=p.y-(int)(Utilities.VIEW_POS_Y);
		p2.x=(int) Math.floor(p2.x/Utilities.TILE_SIZE_X);
		p2.y=(int) Math.floor(p2.y/Utilities.TILE_SIZE_Y);
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
		}
		return new Point(0,0);
	}
	public void UpdateSprite(long elapsedTime){
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
	}
	public boolean IsLoaded(){
		return _isLoaded;
	}
}