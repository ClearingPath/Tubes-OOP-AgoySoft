
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
*
* @author M. Ilmi
*/

public class GameObjectManager {
	
	private Map<String, VisibleGameObject> _gameObjects;
	
	public GameObjectManager(){
		_gameObjects=new HashMap<>();
	}

	public void Add(String name, VisibleGameObject gameObject){
		_gameObjects.put(name, gameObject);
	}
	public void Remove(String name){
		if (_gameObjects.containsKey(name)){
			_gameObjects.remove(name);
		}
	}
	public int GetObjectCount() {
		return _gameObjects.size();
	}
	public VisibleGameObject Get(String name) throws ObjectNameNotFoundException{
		if (_gameObjects.containsKey(name)){
			return _gameObjects.get(name);
		} else {
			throw new ObjectNameNotFoundException();
		}
	}

	public void DrawAll(Graphics2D g, ImageObserver IO){
		Iterator<VisibleGameObject> i=_gameObjects.values().iterator();
		while (i.hasNext()){
			i.next().Draw(g, IO);
		}
	}
	public void UpdateAll(long elapsedTime){
		for (VisibleGameObject in : _gameObjects.values()){
			in.Update(elapsedTime);
			in.UpdateSprite(elapsedTime);
		}
	}

}