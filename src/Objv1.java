
public class Objv1 extends VisibleGameObject {
	private double timepass;
	private int lewat;
	
	public Objv1(){
		super();
		Load("abc.png");
		GetSprite().SetRotation(10);
		SetPosition(5,5);
		GetSprite().SetImageSize(32, 32);;
		GetSprite().AddAnimType(0, 0,0,8,0,500);
		try {
			GetSprite().ChangeAnimType(0);
		} catch (AnimTypeNotFoundException e) {
			// AnimType not found
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timepass=0;
		lewat=0;
	}
	
	@Override
	public void Update(long elapsedTime) {
		timepass+=(elapsedTime);
		if (timepass>=1000){
			lewat++;
			timepass=0;
			if (lewat>36)lewat=0;
			GetSprite().SetRotation(lewat*10);
			SetPosition(lewat, lewat);
		}
	}

}
