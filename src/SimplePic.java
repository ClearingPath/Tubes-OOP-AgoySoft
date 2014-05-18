import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.ImageObserver;


public class SimplePic extends VisibleGameObject {
	@Override
	public void Draw(Graphics2D g, ImageObserver IO){
		GetSprite().Draw(g, IO);
	}
	public void SetPosition(double posx, double posy){
		GetSprite().SetPosition(posx, posy);
	}
	@Override
	public void SetPosition(int posx, int posy){
		GetSprite().SetPosition(posx, posy);
	}
	@Override
	public Point GetPosition(){
		return GetSprite().GetPosition();
	}
	@Override
	public void Update(long elapsedTime) {
		// TODO Auto-generated method stub

	}

}
