package br.uninassau.settings;

public class Map {
	private int mapSize;
	private char[][] map;
	private int cycle = 1;

	public final String ANSI_RESET = "\u001B[0m";
	public final String ANSI_VERMELHO = "\u001B[31m";
	public final String ANSI_VERDE = "\u001B[32m";

	public Map(int mapSize) {
		this.mapSize = mapSize;
		// mapa já construido
		/// a ideia é ele ser construido uma vez apenas e sofrer alterações.
		// está aqui dentro para criar o mapa quando já tiver o tamanho do mapa
		this.map = constructorMap(); // se estiver fora ele cria o mapa antes do tamanho
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
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
	public void viewMap(int liveQuantityOfTiger, int liveQuantityOfRabbit, int liveQuantityOfDeer,
			int liveQuantityOfBush, Collision colision) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[j][i] == 'X')
					System.out.print(ANSI_VERMELHO + map[j][i] + " " + ANSI_RESET);
				else if (map[j][i] == 'c' || map[j][i] == 'v')
					System.out.print(ANSI_VERDE + map[j][i] + " " + ANSI_RESET);
				else
					System.out.print(map[j][i] + " ");
			}
			// estrutura de condição resposavel por mostrar o status do mapa em cada ciclo
			if (i == 0) {
				System.out.printf("| Ciclo do mapa: %d | Tamanho: %dx%d%n", cycle, mapSize, mapSize);
			} else if (i == 1) {
				System.out.printf("| Triges(T) vivos = %d%n", liveQuantityOfTiger);
			} else if (i == 2) {
				System.out.printf("| Coelhos(C) vivos = %d%n", liveQuantityOfRabbit);
			} else if (i == 3) {
				System.out.printf("| Veados(V) vivos = %d%n", liveQuantityOfDeer);
			} else if (i == 4) {
				System.out.printf("| Arbustos(*) quantidades = %d%n", liveQuantityOfBush);
			} else if (i == 5) {
				if (colision.animalIsDead && !colision.herbivoreFed) {
					System.out.println("| " + ANSI_VERMELHO + "Tigre acaba de matar um ANIMAL" + ANSI_RESET);
					colision.animalIsDead = false;
				} else if (!colision.animalIsDead && colision.herbivoreFed) {
					System.out.println("| " + ANSI_VERDE + "Herbivoro abacou de alimentar-se" + ANSI_RESET);
					colision.herbivoreFed = false;
				} else if (colision.animalIsDead && colision.herbivoreFed) {
					System.out.println("| " + ANSI_VERMELHO + "Tigre acaba de matar um ANIMAL" + ANSI_RESET + " | "
							+ ANSI_VERDE + "Herbivoro abacou de alimentar-se" + ANSI_RESET);
					colision.animalIsDead = false;
					colision.herbivoreFed = false;
				} else
					System.out.println("| ");
			}else {
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
