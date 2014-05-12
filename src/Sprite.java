package test;

import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
	private Image _texture;
	//private Rectangle _rect;
	private double Center_X;
	private double Center_Y;
	private double _angle;
	private AffineTransform trans;
	
	public Sprite(){
		_angle=0;
	}
	
	public void Load(String filename) throws IOException{
		try {
			_texture = ImageIO.read(getClass().getResource(filename));
		} catch (IOException e) {
			System.out.println("file not found");
			throw e;
		}
	}
	
	public void Load (Image i){
		_texture=i;
	}
	
	public void SetPosition(double x, double y){
		Center_X=x;
		Center_Y=y;
	}
	
	public double[] GetPosition(){
		double ret[]=new double[2];
		ret[0]=Center_X;
		ret[1]=Center_Y;
		return ret;
	}
	
	public void SetAngle(float angle){
		_angle=angle;
		// Rotation information
		double rotationRequired = Math.toRadians(angle);
		double locationX = _texture.getWidth(null) / 2;
		double locationY = _texture.getHeight(null) / 2;
		trans = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		//trans = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}
	
	public double GetAngle(){
		return _angle;
	}
	
	public void Draw(Graphics2D g, ImageObserver IO){
		g.drawImage(_texture, trans, IO);
		//g.drawImage(trans.filter(_texture, null), Center_X, Center_Y, IO);
	}
}