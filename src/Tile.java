

public class Tile {
		/** atribut **/
		private Utilities.TileType Jenis;
		public item ItemOnTop;

		/** konstruktor **/
		public Tile(){
			ItemOnTop = item.ItemKosong;
		}
		
		/** method **/
		// getter
		Utilities.TileType getJenis(){
			return Jenis;
		}
		item getItemOnTop(){
			return ItemOnTop;
		}
		boolean IsWalkable(){
			return (Jenis!=Utilities.TileType.UnWalkable);
		}
		// setter
		void setJenis(Utilities.TileType jenis){
			Jenis = jenis;
		}
		void putItem(item _newItem){
			ItemOnTop = _newItem;
		}
}