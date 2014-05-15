package test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
//import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Sprite{
	private Image _texture;
	//private Rectangle _rect;
	private double Center_X;
	private double Center_Y;
	private double _angle;
	
	private int Size_X;
	private int Size_Y;
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
	
	private static AnimType DefAnim(){
		AnimType a=new AnimType();
		a.StartRow=0;
		a.StartCol=0;
		a.rate=-1;
		return a;
	}
	
	private AffineTransform trans;
	
	public Sprite(){
		_angle=0;
		Size_X=-1;
		Size_Y=-1;
		CurTime=0;
		CurAnim=DefAnim;
		_anim_array=new HashMap<>();
		trans=new AffineTransform();
	}
	
	public void Load(String filename) throws IOException{
		try {
			_texture = ImageIO.read(getClass().getResource(filename));
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void Load (Image i) throws IOException{
		// TODO cari cara cek image udah di load/belum
		if (i.getHeight(null)<=0){
			//kalo image belom di load
			throw new IOException();
		}
		_texture=i;
	}
	
	public void AddAnimType(int Code, int StartRow, int StartCol, int EndRow, int EndCol, double rate){
		AnimType a=new AnimType();
		a.StartRow=StartRow;
		a.StartCol=StartCol;
		a.EndRow=EndRow;
		a.EndCol=EndCol;
		a.rate=rate;
		_anim_array.put(Code,a);
	}
	
	public void ChangeAnimType(int Code) throws IOException{
		if (_anim_array.containsKey(Code)){
			CurAnim=_anim_array.get(Code);
			CurTime=0;
			CurRow=0;
			CurCol=0;
		} else {
			throw new IOException();
		}
	}
	
	public void UpdateDraw(double ElapsedTime){
		if (CurAnim.rate!=-1){
			CurTime+=ElapsedTime;
			if (CurTime>CurAnim.rate){
				CurRow++;
				if (CurRow>CurAnim.EndRow)CurRow=CurAnim.StartRow;
				CurCol++;
				if (CurCol>CurAnim.EndCol)CurCol=CurAnim.StartCol;
			}
		}
	}
	public void Draw(Graphics2D g, ImageObserver IO){
		Rectangle rect=new Rectangle();
		int s_x=Size_X;
		int s_y=Size_Y;
		if (s_x==-1)s_x=_texture.getWidth(null);
		if (s_y==-1)s_y=_texture.getHeight(null);
		rect.x=CurCol*s_x;
		rect.y=CurRow*s_y;
		rect.width=s_x;
		rect.height=s_y;
		//rect=bagian gambar yang ingin digambar
		// TODO draw only necessary rectangle
		g.drawImage(_texture, trans, IO);
		//g.drawImage(trans.filter(_texture, null), Center_X, Center_Y, IO);
	}

	public Point GetPosition(){
		Point ret=new Point();
		ret.x=(int) Center_X;
		ret.y=(int) Center_Y;
		return ret;
	}
	public void SetPosition(double x, double y){
		Center_X=x;
		Center_Y=y;
	}
	public void SetPosition(Point p){
		Center_X=p.x;
		Center_Y=p.y;
	}
	public void SetImageSize(int SizeX, int SizeY){
		Size_X=SizeX;
		Size_Y=SizeY;
	}
	public double GetAngle(){
		return _angle;
	}
	public void SetRotation(float angle){
		_angle=angle;
		if (_angle>=360)_angle=_angle%360;
		// TODO Fix Rotation
		// Rotation information
		double rotationRequired = Math.toRadians(angle);
		double locationX = _texture.getWidth(null) / 2;
		double locationY = _texture.getHeight(null) / 2;
		trans = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		//trans = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}
	public double getWidth(){
		return _texture.getWidth(null);
	}
	public double getHeight(){
		return _texture.getHeight(null);
	}
	public Rectangle getBounds(){
		Rectangle r=new Rectangle();
		r.height=(int) getWidth();
		r.width=(int) getHeight();
		r.x=(int) (Center_X-r.height/2);
		r.y=(int) (Center_Y-r.width/2);
		return r;
	}
}