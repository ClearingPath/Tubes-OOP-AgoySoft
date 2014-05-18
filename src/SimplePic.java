import java.awt.Graphics2D;
import java.awt.image.ImageObserver;


public class SimplePic extends VisibleGameObject {
	@Override
	public void Draw(Graphics2D g, ImageObserver IO){
		GetSprite().Draw(g, IO);
	}
	@Override
	public void Update(long elapsedTime) {
		// TODO Auto-generated method stub

	}

}
