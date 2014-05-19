
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
*
* @author M. Ilmi
*/

public class Sprite{
	private BufferedImage _texture;
	private double Pos_X;
	private double Pos_Y;
	private double _angle;
	private int Size_X;
	private int Size_Y;
	private AffineTransform transscl;
	private int Offset_X;
	private int Offset_Y;
	
	private static class AnimType{
		public int StartRow;
		public int StartCol;
		public int EndRow;
		public int EndCol;
		public double rate;
	}
	private HashMap<Integer, AnimType> _anim_array;
	private AnimType CurAnim;
	private int CurRow;
	private int CurCol;
	private double CurTime;
	private static AnimType DefAnim=DefAnim();
	/** 
     * Inisialisasi untuk null object dari AnimType
    */
	private static AnimType DefAnim(){
		AnimType a=new AnimType();
		a.StartRow=0;
		a.StartCol=0;
		a.rate=-1;
		return a;
	}
	
	public Sprite(){
		_angle=0;
		Size_X=-1;
		Size_Y=-1;
		Offset_X=0;
		Offset_Y=0;
		CurTime=0;
		CurAnim=DefAnim;
		_anim_array=new HashMap<>();
		transscl=new AffineTransform();
		transscl.setToIdentity();
	}
	
	/** 
     * Load source image untuk sprite dari file external
     * @param filename path tempat source image yang akan di load
    */
	public void Load(String filename) throws IOException{
		try {
			_texture = ImageIO.read(new File(filename));
			System.out.println("tes");
			System.out.println(_texture.getHeight()+","+_texture.getWidth());
		} catch (Exception e) {
			throw e;
		}
	}
	/** 
     * Load source image untuk sprite dari BufferedImage yang di-input
     * @param i BufferedImage yang akan dijadikan source
    */
	public void Load (BufferedImage i) throws IOException{
		// TODO cari cara cek image udah di load/belum
		if (i.getHeight(null)<=0){
			//kalo image belom di load
			throw new IOException();
		}
		_texture=i;
	}
	
	/** 
     * Menambahkan animasi pada Sprite.
     * Baris dan kolom dihitung berdasarkan ukuran sprite yang ditentukan
     * Animasi akan di loop otomatis dari gambar awal ke gambar akhir,
     * lalu ganti ke gambar awal dan mengulangi lagi
     * @param Code Kode animasi yang ditambahkan
     * @param StartRow Nomor baris pada gambar yang dijadikan frame awal animasi
     * @param StartCol Nomor kolom pada gambar yang dijadikan frame awal animasi
     * @param EndRow Nomor kolom pada gambar yang dijadikan frame akhir animasi
     * @param EndCol Nomor baris pada gambar yang dijadikan frame akhir animasi
     * @param rate kecepatan animasi berpindah antar frame. -1 untuk tidak berubah dari frame awal
    */
    public void AddAnimType(int Code, int StartRow, int StartCol, int EndRow, int EndCol, double rate){
		AnimType a=new AnimType();
		a.StartRow=StartRow;
		a.StartCol=StartCol;
		a.EndRow=EndRow;
		a.EndCol=EndCol;
		a.rate=rate;
		_anim_array.put(Code,a);
	}
	
    /** 
     * Mengubah animasi yang ditampilkan
     * Kode animasi sesuai yang diisi pada saat AddAnimType
     * @param Code Kode animasi yang ingin ditampilkan
    */
    public void ChangeAnimType(int Code) throws AnimTypeNotFoundException{
		if (_anim_array.containsKey(Code)){
			CurAnim=_anim_array.get(Code);
			CurTime=0;
			CurRow=CurAnim.StartRow;
			CurCol=CurAnim.StartCol;
		} else {
			CurAnim=DefAnim;
			CurTime=0;
			CurRow=CurAnim.StartRow;
			CurCol=CurAnim.StartCol;
			throw new AnimTypeNotFoundException();
		}
	}
	
    /** 
     * Method untuk menjalankan animasi. 
     * Dijalankan setiap interval bersama-sama update milik VisibleGameObject (60 fps)
     * @param elapsedTime waktu sejak method ini terakhir dijalankan
    */
    public void UpdateDraw(long elapsedTime){
		if (CurAnim.rate!=-1){
			CurTime+=elapsedTime;
			if (CurTime>CurAnim.rate){
				CurRow++;
				if (CurRow>CurAnim.EndRow)CurRow=CurAnim.StartRow;
				CurCol++;
				if (CurCol>CurAnim.EndCol)CurCol=CurAnim.StartCol;
				CurTime=0;
			}
		}
	}
    /** 
     * Menggambar Sprite ke Screen
     * @param g Grafik tempat tujuan digambar
     * @param IO ImageObserver yang akan di-notify
    */
    public void Draw(Graphics2D g, ImageObserver IO){
		int s_x=Size_X;
		int s_y=Size_Y;
		if (s_x==-1)s_x=_texture.getWidth(null);
		if (s_y==-1)s_y=_texture.getHeight(null);
		AffineTransform trans=new AffineTransform();
		trans.setToIdentity();
		trans.concatenate(AffineTransform.getRotateInstance(Math.toRadians(_angle),Pos_X+s_x/2,Pos_Y+s_y/2));
		trans.concatenate(AffineTransform.getTranslateInstance(Pos_X, Pos_Y));
		trans.concatenate(transscl);
		BufferedImage tp=_texture.getSubimage(CurCol*s_x+Offset_X,CurRow*s_y+Offset_Y,s_x,s_y);
		g.drawImage(tp, trans, IO);
	}

    /** 
     * Mendapatkan posisi Sprite.
     * Posisi dalam bentuk integer
    */
    public Point GetPosition(){
		Point ret=new Point();
		ret.x=(int) Pos_X;
		ret.y=(int) Pos_Y;
		return ret;
	}
    /** 
     * Mengubah posisi Sprite.
     * Posisi relatif terhadap Screen
     * Posisi yang dimaksd adalah posisi kiri atas Sprite
     * @param x Posisi x relatif terhadap screen
     * @param y Posisi y relatif terhadap screen
    */
    public void SetPosition(double x, double y){
		Pos_X=x;
		Pos_Y=y;
	}
    /** 
     * Mengubah posisi Sprite.
     * Posisi relatif terhadap Screen
     * Posisi yang dimaksd adalah posisi kiri atas Sprite
     * @param P Posisi relatif terhadap screen
    */
    public void SetPosition(Point p){
		Pos_X=p.x;
		Pos_Y=p.y;
	}
    /** 
     * Mennentukan ukuran gambar yang diambil dari source image.
     * input -1 untuk mengambil seukuran gambar source
     * @param SizeX Ukuran x yang akan diambil.
     * @param SizeY Ukuran y yang akan diambil.
    */
    public void SetImageSize(int SizeX, int SizeY){
		Size_X=SizeX;
		Size_Y=SizeY;
	}
    /** 
     * Mengambil besar rotasi yang diaplikasikan ke Sprite.
     * input -1 untuk mengambil seukuran gambar source
     * @return double besar rotasi perputaran sprite dalam derajat
    */
    public double GetAngle(){
		return _angle;
	}
    /** 
     * Memutar sprite sebesar input.
     * Rotasi tidak ditambah dari rotasi sebelumnya,
     * tetapi di-reset lalu dirotasi sebesar input
     * @param angle Sudut perputaran gambar dalam derajat.
    */
    public void SetRotation(double angle){
		_angle=angle;
		if (_angle>=360)_angle=_angle%360;
	}
    /** 
     * Mengubah skala perbesaran sprite sebesar input.
     * Skala tidak diubah berdasarkan skala sebelumnya,
     * tetapi di-reset lalu diskalakan sebesar input
     * @param ScaleX Besar skala x perbesaran gambar.
     * @param ScaleY Besar skala y perbesaran gambar.
    */
    public void SetScale(double ScaleX, double ScaleY){
		transscl.setToScale(ScaleX, ScaleY);
	}
    /** 
     * Mengubah offset pergesaran ujung kiri atas source image
     * dimana gambar akan diambil.
     * Offset tidak diubah berdasarkan offset sebelumnya,
     * tetapi di-reset lalu digeser ulang sebesar input
     * @param OffX Besar pergeseran x dari source image.
     * @param OffY Besar pergeseran y dari source image.
    */
    public void SetOffset(int OffX, int OffY){
		Offset_X=OffX;
		Offset_Y=OffY;
	}
    /** 
     * Mendapatkan ukuran lebar dari gambar yang diambil dari source image
     * @return double lebar gambar yang diambil dari source image.
    */
    public double getWidth(){
		int s_x=Size_X;
		if (s_x==-1)s_x=_texture.getWidth(null);
		return s_x;
	}
    /** 
     * Mendapatkan ukuran tinggi dari gambar yang diambil dari source image
     * @return double tinggi gambar yang diambil dari source image.
    */
    public double getHeight(){
		int s_y=Size_Y;
		if (s_y==-1)s_y=_texture.getHeight(null);
		return s_y;
	}
    /** 
     * Mendapatkan ukuran lebar dari gambar yang diambil dari source image
     * lebar dalam ukuran tile
     * @return double lebar gambar yang diambil dari source image bedasar tile.
    */
    public int getTileWidth(){
		return (int) (getWidth()/Utilities.TILE_SIZE_X);
	}
    /** 
     * Mendapatkan ukuran tinggi dari gambar yang diambil dari source image
     * tinggi dalam ukuran tile
     * @return double tinggi gambar yang diambil dari source image bedasar tile.
    */
    public int getTileHeight(){
		return (int) (getHeight()/Utilities.TILE_SIZE_Y);
	}
    /** 
     * Mendapatkan Rectangle yang meliputi seluruh gambar
     * @return Rectangle Rectangle yang meliputi seluruh gambar
    */
    public Rectangle getBounds(){
		Rectangle r=new Rectangle();
		r.height=(int) getHeight();
		r.width=(int) getWidth();
		r.x=(int) (Pos_X);
		r.y=(int) (Pos_Y);
		return r;
	}
}