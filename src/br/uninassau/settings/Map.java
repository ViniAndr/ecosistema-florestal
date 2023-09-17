package br.uninassau.settings;

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

	// preciso dessa class para ter acesso se algum coleho foi morto e mostrar no
	// console
	// mostra o mapa atualiazado no terminal
	public void viewMap(int liveQuantityOfTiger, int liveQuantityOfRabbit, int liveQuantityOfDeer, Collision colision) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				System.out.print(map[j][i] + " ");
			}
			if (i == 0) {
				System.out.printf("| Tamanho do Mapa %dx%d%n", mapSize, mapSize);
			} else if (i == 1) {
				System.out.printf("| Triges(T) vivos = %d%n", liveQuantityOfTiger);
			} else if (i == 2) {
				System.out.printf("| Coelhos(C) vivos = %d%n", liveQuantityOfRabbit);
			} else if (i == 3) {
				System.out.printf("| Veados(V) vivos = %d%n", liveQuantityOfDeer);
			} else if (i == 4) {
				if (colision.getAnimalIsDead()) {
					System.out.println("| Tigre acaba de mata algum animal");
					colision.setAnimalIsDead(false);
				}
				else
					System.out.println("| ");
			} else {
				System.out.println("| ");
			}
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
