

public class Tile extends VisibleGameObject{
    /** atribut **/
    private Utilities.TileType Jenis;
    public item ItemOnTop;

    /** konstruktor **/
    public Tile(){
            ItemOnTop = item.ItemKosong;
    }

    /** method **/
    // getter
    public Utilities.TileType getJenis(){
            return Jenis;
    }
    public item getItemOnTop(){
            return ItemOnTop;
    }
    public boolean IsWalkable(){
            return (Jenis!=Utilities.TileType.UnWalkable);
    }
    // setter
    public void setJenis(Utilities.TileType jenis){
            Jenis = jenis;
    }
    public void putItem(item _newItem){
            ItemOnTop = _newItem;
    }
    public void Update(long elapsedTime) {
        
    }
}