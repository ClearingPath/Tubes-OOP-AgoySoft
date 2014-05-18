
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
	
	public void Load(String filename) throws IOException{
		try {
			_texture = ImageIO.read(new File(filename));
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void Load (BufferedImage i) throws IOException{
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
	
	public void ChangeAnimType(int Code) throws AnimTypeNotFoundException{
		if (_anim_array.containsKey(Code)){
			CurAnim=_anim_array.get(Code);
			CurTime=0;
			CurRow=CurAnim.StartRow;
			CurCol=CurAnim.StartCol;
		} else {
			throw new AnimTypeNotFoundException();
		}
	}
	
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

	public Point GetPosition(){
		Point ret=new Point();
		ret.x=(int) Pos_X;
		ret.y=(int) Pos_Y;
		return ret;
	}
	public void SetPosition(double x, double y){
		Pos_X=x;
		Pos_Y=y;
	}
	public void SetPosition(Point p){
		Pos_X=p.x;
		Pos_Y=p.y;
	}
	public void SetImageSize(int SizeX, int SizeY){
		Size_X=SizeX;
		Size_Y=SizeY;
	}
	public double GetAngle(){
		return _angle;
	}
	public void SetRotation(double angle){
		_angle=angle;
		if (_angle>=360)_angle=_angle%360;
	}
	public void SetScale(double ScaleX, double ScaleY){
		transscl.setToScale(ScaleX, ScaleY);
	}
	public void SetOffset(int OffX, int OffY){
		Offset_X=OffX;
		Offset_Y=OffY;
	}
	public double getWidth(){
		return Size_X;
	}
	public double getHeight(){
		return Size_Y;
	}
	public int getTileWidth(){
		return Size_X/Utilities.TILE_SIZE_X;
	}
	public int getTileHeight(){
		return Size_Y/Utilities.TILE_SIZE_Y;
	}
	public Rectangle getBounds(){
		Rectangle r=new Rectangle();
		r.height=Size_X;
		r.width=Size_Y;
		r.x=(int) (Pos_X);
		r.y=(int) (Pos_Y);
		return r;
	}
}