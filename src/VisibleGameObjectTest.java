class VisibleGameObject
{
	public VisibleGameObject(){
		_isLoaded=false;
	}
	public virtual ~VisibleGameObject(){
		
	}
	
	public virtual void Load(std::string filename){
		if(_image.loadFromFile(filename) == false){
			_filename = "";
			_isLoaded = false;
		} else {
			_filename = filename;
			_sprite.setTexture(_image);
			_isLoaded = true;
		}
	}
	public virtual void Draw(sf::RenderWindow & window){
		if(_isLoaded) {
			renderWindow.draw(_sprite);
		}
	}
	public virtual void Update(float elapsedTime){
		
	}

	public virtual void SetPosition(float x, float y){
		if(_isLoaded){
			_sprite.setPosition(x,y);
		}
	}
	public virtual sf::Vector2f GetPosition() const{
		if(_isLoaded) {
			return _sprite.getPosition();
		}
		return sf::Vector2f();
	}
	public virtual float GetWidth() const{
		return _sprite.getLocalBounds().width;
	}
	public virtual float GetHeight() const{
		return _sprite.getLocalBounds().height;
	}

	public virtual sf::Rect<float> GetBoundingRect() const{
		return _sprite.getGlobalBounds();
	}
	public virtual bool IsLoaded() const{
		return _isLoaded;
	}

	protected sf::Sprite& GetSprite();

	private sf::Sprite  _sprite;
	private sf::Texture _image;
	private std::string _filename;
	private bool _isLoaded;
};
