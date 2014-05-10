class GameObjectManager {
	
	private std::map<std::string, VisibleGameObject> _gameObjects;
	private sf::Clock clock;
	
	private class GameObjectDeallocator{
		void operator()(const std::pair<std::string,VisibleGameObject*> & p) const{
			delete p.second;
		}
	}
	
	public GameObjectManager(){
		
	}
	public ~GameObjectManager(){
		std::for_each(_gameObjects.begin(),_gameObjects.end(),GameObjectDeallocator());
	}

	public void Add(std::string name, VisibleGameObject gameObject){
		_gameObjects.insert(std::pair<std::string,VisibleGameObject>(name,gameObject));
	}
	public void Remove(std::string name){
		std::map<std::string, VisibleGameObject>::iterator results = _gameObjects.find(name);
		if(results != _gameObjects.end() )
		{
			delete results->second;
			_gameObjects.erase(results);
		}
	}
	public int GetObjectCount() const{
		return _gameObjects.size();
	}
	public VisibleGameObject Get(std::string name) const{
		std::map<std::string, VisibleGameObject>::const_iterator results = _gameObjects.find(name);
		if(results == _gameObjects.end() )
			return NULL;
		return results->second;
	}

	public void DrawAll(sf::RenderWindow& renderWindow){
		std::map<std::string,VisibleGameObject>::const_iterator itr = _gameObjects.begin();
		while(itr != _gameObjects.end())
		{
			itr->second.Draw(renderWindow);
			itr++;
		}
	}
	public void UpdateAll(){
		std::map<std::string,VisibleGameObject>::const_iterator itr = _gameObjects.begin();
		float timeDelta = clock.restart().asSeconds();

		while(itr != _gameObjects.end())
		{
			itr->second.Update(timeDelta);
			itr++;
		}
	}

}