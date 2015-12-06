
public class Counter {
	
	static int m_MineCount;
	static int m_DiffusedCount;
	static int m_RevealedTileCount;
	static int m_TilesRemainingCount;
	
	public Counter(int numOfMines, int numOfTiles){
		m_DiffusedCount = 0;
		m_RevealedTileCount = 0;
		m_MineCount = numOfMines;
		m_TilesRemainingCount = numOfTiles*numOfTiles;
	}
	
	public static void setMineCount(int numOfMines){
		m_MineCount = numOfMines;
	}
	
	/*public static void setTileRemainingCount(int numOfTiles){

		int sizeOfBoard = numOfTiles * numOfTiles;
		m_TilesRemainingCount = sizeOfBoard - getReveleadTileCount();
	} */
	
	public static void decrementTilesRemainingCount(){
		m_TilesRemainingCount --;
	}

	public static void decrementDiffusedCount(){
		m_DiffusedCount --;
	}
	
	public static void incrementDiffusedCount(){
		m_DiffusedCount ++;
	}
	
	public static void incrementRevealedTileCount(){
		m_RevealedTileCount ++;
	}
	
	public static int getMineCount(){
		return m_MineCount;
	}

	public static int getTilesRemainingCount(){
		return m_TilesRemainingCount;
	}
	
	public static int getRevealedTileCount(){
		return m_RevealedTileCount;
	}
	
	public static int getDiffusedCount(){
		return m_DiffusedCount;
	}

	
	
}
