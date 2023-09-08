package settings;

public class Map {
	private int mapSize = 8;
	
	public Map(int mapSize) {
		this.mapSize = mapSize;
	}
	
	public int getMapSize() {
		return mapSize;
	}



	public char[][] constructorMap() {
		char[][] map = new char[mapSize][mapSize];
		// i é a coluna
		for (int i = 0; i < mapSize; i++) {
			// j é a linha
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = '.';
			}
		}
		return map;
	}
	
	//mapa já construido
	private char[][] map = constructorMap();
	
	public void setMap(char[][] map) {
		this.map = map;
	}
	
	

	public char[][] getMap() {
		return map;
	}



	// mostra o mapa atualiazado no terminal
	public void viewMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
