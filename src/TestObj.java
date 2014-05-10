package test;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class TestObj extends VisibleGameObject {
	
	private int x;
	private static int num=0;
	
	public TestObj(){
		num++;
		x=num;
	}
	
	public void Update(double elapsedTime){
		System.out.println(x);
	}
	public void Draw(Graphics2D g, ImageObserver IO){
		if(IsLoaded()) {
			g.drawImage(_texture, _sprite.x, _sprite.y, _sprite.x + _sprite.width, _sprite.y+_sprite.height,
					0, 0, _texture.getWidth(null), _texture.getHeight(null), IO);
		}
	}
}
