
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
	
	/** 
     * Constructor
    */
    public VisibleGameObject(){
		_isLoaded=false;
		_sprite=new Sprite();
	}
	/** 
     * Method abstract yang akan dijalankan otomatis
     * pada interval tertentu (60 fps)
     * Gunakan untuk mengubah state seiring waktu dengan mulus
     * @param elapsedTime waktu yang lewat sejak update terakhir. Dalam milisecond
    */
    public abstract void Update(long elapsedTime);
	
    /** 
     * Mendapatkan Sprite untuk objek
     * @return Sprite sprite
    */
    protected Sprite GetSprite(){
		return _sprite;
	}
	/** 
     * Mengecek apakah Objek sudah melakukan Load gambar ke Sprite-nya
     * @return boolean status objek sudah di-load atau belum
    */
	public boolean IsLoaded(){
		return _isLoaded;
	}
	/** 
     * Me-load gambar ke Sprite
     * @param filename path gambar yang akan di-load
    */
    public void Load(String filename){
		try{
			_sprite.Load(filename);
			_isLoaded = true;
		} catch (IOException e){
			_isLoaded=false;
		}
	}
    /** 
     * Meng-copy BufferedImage ke Sprite
     * @param i BufferedImage yang akan dipakai sebagai gambar
    */
    public void Load(BufferedImage i){
		try{
			_sprite.Load(i);
			_isLoaded = true;
		} catch(IOException e){
			_isLoaded=false;
		}
	}
    
    /** 
     * Menggambar Sprite ke Screen.
     * Dilakukan dulu pengecekan apakah di luar kamera atau tidak
     * @param g Grafik tempat tujuan digambar
     * @param IO ImageObserver yang akan di-notify
    */
    public void Draw(Graphics2D g, ImageObserver IO){
		if(_isLoaded) {
			// rectangle untuk peta yang kelihatan
			Rectangle a=new Rectangle();
			a.x=Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X;
			a.y=Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y;
			a.height=Utilities.VIEW_ROW_COUNT*Utilities.TILE_SIZE_X;
			a.width=Utilities.VIEW_COL_COUNT*Utilities.TILE_SIZE_Y;
			// rectangle untuk object bersangkutan
			Rectangle b=new Rectangle();
			b.x=tile_posx*Utilities.TILE_SIZE_X;
			b.y=tile_posy*Utilities.TILE_SIZE_Y;
			b.height=_sprite.getTileHeight()*Utilities.TILE_SIZE_X;
			b.width=_sprite.getTileWidth()*Utilities.TILE_SIZE_Y;
			if (a.intersects(b)){
				_sprite.Draw(g, IO);
			}
		}
	}

    /** 
     * Method khusus untuk animasi sprite.
     * Dijalankan otomatis setiap interval (60 fps)
     * @param elapsedTime waktu sejak method ini dijalankan terakhir
    */
    public void UpdateSprite(long elapsedTime){
		_sprite.UpdateDraw(elapsedTime);
	}
    /** 
     * Mengubah dari posisi berdasar tile menjadi 
     * posisi sesungguhnya relatif terhadap window
     * @param p posisi berdasar tile yang akan diubah
     * @return Point posisi relatif terhadap window
    */
    public static Point TiletoReal(Point p){
		Point p2=new Point();
		p2.x=(int)Utilities.VIEW_POS_X+(p.x-Utilities.VIEW_TILE_X)*Utilities.TILE_SIZE_X;
		p2.y=(int)Utilities.VIEW_POS_Y+(p.y-Utilities.VIEW_TILE_Y)*Utilities.TILE_SIZE_Y;
		return p2;
	}
    /** 
     * Mengubah dari posisi relatif terhadap window menjadi 
     * posisi berdasar tile
     * @param p posisi relatif terhadap window yang akan diubah
     * @return Point posisi berdasar tile
    */
    public static Point RealToTile(Point p){
		Point p2=new Point();
		p2.x=p.x-(int)(Utilities.VIEW_POS_X);
		p2.y=p.y-(int)(Utilities.VIEW_POS_Y);
		p2.x=(int) Math.floor((p2.x+Utilities.VIEW_TILE_X)/Utilities.TILE_SIZE_X);
		p2.y=(int) Math.floor((p2.y+Utilities.VIEW_TILE_Y)/Utilities.TILE_SIZE_Y);
		return p2;
	}
    /** 
     * Mengubah posisi Objek. Posisi input berdasar tile
     * @param tile_x posisi x tujuan berdasar tile
     * @param tile_y posisi y tujuan berdasar tile
    */
    public void SetPosition(int tile_x, int tile_y){
		if(_isLoaded){
			tile_posx=tile_x;
			tile_posy=tile_y;
			_sprite.SetPosition(TiletoReal(new Point(tile_x, tile_y)));
		}
	}
    /** 
     * Mendapatkan posisi Objek. Posisi yang dihasilkan berdasar tile
     * @return Point posisi letak objek berdasar tile
    */
    public Point GetPosition() {
		if(_isLoaded) {
			return new Point(tile_posx, tile_posy);
		}
		return new Point(0,0);
	}
    /** 
     * Mendapatkan ukuran lebar objek
     * Lebar yang didapat relatif terhadap window
     * @return double lebar objek
    */
    public double GetWidth(){
		return _sprite.getWidth();
	}
    /** 
     * Mendapatkan ukuran tinggi objek
     * Tinggi yang didapat relatif terhadap window
     * @return double tinggi objek
    */
    public double GetHeight(){
		return _sprite.getHeight();
	}
    /** 
     * Mendapatkan Rectangle yang melingkupi objek
     * Rectangle yang didapat relatif terhadap window
     * @return Rectangle Rectangle yang melingkupi objek
    */
	public Rectangle GetBoundingRect(){
		return _sprite.getBounds();
	}
}