package test;

public class Main1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameObjectManager g=new GameObjectManager();
		TestObj abc=new TestObj();
		g.Add("bgh",abc);
		ObjTest ghj=new ObjTest(5);
		g.Add("ghj", ghj);
		g.UpdateAll();
		TestObj bcd;
		bcd=(TestObj)g.Get("bgh");
		bcd.Update(0.1);
		ghj=(ObjTest)g.Get("ghj");
		ghj.Update(0.1);
	}
}
