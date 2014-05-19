
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
	//Container yang menyimpan objek yang di-manage
	private Map<String, VisibleGameObject> _gameObjects;
	
	public GameObjectManager(){
		_gameObjects=new HashMap<>();
	}

	/** 
     * Menambahkan VisibleGameObject untuk di-manage
     * @param name Nama yang diberikan ke Objek
     * @param gameObject Objek yang akan dimasukkan ke manager
    */
    public void Add(String name, VisibleGameObject gameObject){
		_gameObjects.put(name, gameObject);
	}
    /** 
     * Membuang VisibleGameObject tidak di-manage lagi
     * @param name Nama yang objek yang akan dibuang
    */
    public void Remove(String name){
		if (_gameObjects.containsKey(name)){
			_gameObjects.remove(name);
		}
	}
    /** 
     * Mendapatkan jumlah VisibleGameObject yang di-manage
     * @return int jumlah objek yang di-manage
    */
    public int GetObjectCount() {
		return _gameObjects.size();
	}
    /** 
     * Mengambil salah satu VisibleGameObject yang di-manage
     * @param name Nama objek yang akan diambil
    */
    public VisibleGameObject Get(String name) throws ObjectNameNotFoundException{
		if (_gameObjects.containsKey(name)){
			return _gameObjects.get(name);
		} else {
			throw new ObjectNameNotFoundException();
		}
	}
    /** 
     * Meng-update, dengan kata lain memanggil method update, 
     * dari semua VisibleGameObject yang di-manage
     * @param elapsedTime waktu sejak update terakhir dilakukan
    */
    public void UpdateAll(long elapsedTime){
		for (VisibleGameObject in : _gameObjects.values()){
			in.Update(elapsedTime);
			in.UpdateSprite(elapsedTime);
		}
	}
    /** 
     * Menggambar, dengan kata lain memanggil method draw, 
     * dari semua VisibleGameObject yang di-manage
     * @param g Screen yang akan digambar
     * @param IO ImageObserver yang akan di notifikasi
    */
    public void DrawAll(Graphics2D g, ImageObserver IO){
		Iterator<VisibleGameObject> i=_gameObjects.values().iterator();
		while (i.hasNext()){
			i.next().Draw(g, IO);
		}
	}
}