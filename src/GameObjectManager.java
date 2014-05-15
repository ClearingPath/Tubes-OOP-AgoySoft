package test;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class GameObjectManager {
	
	private Map<String, VisibleGameObject> _gameObjects;
	private long LastTime;
	
	public GameObjectManager(){
		LastTime=System.currentTimeMillis();
		_gameObjects=new HashMap<>();
	}

	public void Add(String name, VisibleGameObject gameObject){
		_gameObjects.put(name, gameObject);
		//_gameObjects.insert(std::pair<std::string,VisibleGameObject>(name,gameObject));
	}
	public void Remove(String name){
		if (_gameObjects.containsKey(name)){
			_gameObjects.remove(name);
		}
		/*std::map<std::string, VisibleGameObject>::iterator results = _gameObjects.find(name);
		if(results != _gameObjects.end() )
		{
			delete results->second;
			_gameObjects.erase(results);
		}*/
	}
	public int GetObjectCount() {
		return _gameObjects.size();
	}
	public VisibleGameObject Get(String name){
		if (_gameObjects.containsKey(name)){
			return _gameObjects.get(name);
		}
		return null;
		/*std::map<std::string, VisibleGameObject>::const_iterator results = _gameObjects.find(name);
		if(results == _gameObjects.end() )
			return NULL;
		return results->second;*/
	}

	public void DrawAll(Graphics2D g, ImageObserver IO){
		Iterator<VisibleGameObject> i=_gameObjects.values().iterator();
		while (i.hasNext()){
			i.next().Draw(g, IO);
		}
		/*std::map<std::string,VisibleGameObject>::const_iterator itr = _gameObjects.begin();
		while(itr != _gameObjects.end())
		{
			itr->second.Draw(renderWindow);
			itr++;
		}*/
	}
	public void UpdateAll(){
		long elapsedTime=System.currentTimeMillis()-LastTime;
		LastTime=elapsedTime;
		double elaps_ms=elapsedTime/1000;
		
		for (VisibleGameObject in : _gameObjects.values()){
			in.Update(elaps_ms);
			in.UpdateSprite(elaps_ms);
		}
		
		/*Iterator<VisibleGameObject> i=_gameObjects.values().iterator();
		while (i.hasNext()){
			i.next().Update((double)elapsedTime/1000);
		}
		std::map<std::string,VisibleGameObject>::const_iterator itr = _gameObjects.begin();
		float timeDelta = clock.restart().asSeconds();
		
		while(itr != _gameObjects.end())
		{
			itr->second.Update(timeDelta);
			itr++;
		}*/
	}

}