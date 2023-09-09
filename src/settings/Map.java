package settings;

public class Map {
	private int mapSize;
	private char[][] map;

	public Map(int mapSize) {
		this.mapSize = mapSize;
		// mapa já construido
		/// a ideia é ele ser construido uma vez apenas e sofrer alterações.
		// está aqui dentro para criar o mapa quando já tiver o tamanho do mapa
		this.map = constructorMap(); // se estiver fora ele cria o mapa antes do tamanho
	}
	// Constroi o mapa com o tamnho certo
	public char[][] constructorMap() {
		char[][] map = new char[mapSize][mapSize];
		// i é a coluna
		for (int i = 0; i < mapSize; i++) {
			// j é a linha
			for (int j = 0; j < mapSize; j++) {
				// já que i é a coluna e J a linha, inverto para X e y estarem certo
				map[j][i] = '.';
			}
		}
		return map;
	}

	// mostra o mapa atualiazado no terminal
	public void viewMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				System.out.print(map[j][i] + " ");
			}
			System.out.println();
		}
	}
	// Adiciona o "objeto" no mapa em sua posição atual
	public void addObjectOnMap(int x, int y, char symbol) {
		map[x][y] = symbol;
	}
	// Remove os "objeto" do mapa
	public void removeObjectOnMap(int x, int y) {
		map[x][y] = '.';
	}
}
