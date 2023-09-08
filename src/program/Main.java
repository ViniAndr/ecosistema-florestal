package program;

import java.util.HashSet;
import java.util.Set;

import settings.Coordinates;
import settings.Map;
import animal.Rabbit;
import animal.Tiger;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// Conjunto de elementos que não se repetem. As posições
		Set<Coordinates> positionsUsed = new HashSet<Coordinates>();

		// config do mapa
		int qtd = 2;
		int mapSize = 8;
		Map map = new Map(mapSize);

		Tiger[] tiger = new Tiger[qtd];
		Rabbit[] rabbits = new Rabbit[qtd];

		for (int i = 0; i < qtd; i++) {
			rabbits[i] = new Rabbit(positionsUsed);
			tiger[i] = new Tiger(positionsUsed);
		}

		while (true) {

			// spawna no mapa
			for (int i = 0; i < qtd; i++) {
				map.getMap()[rabbits[i].getPoint().getX()][rabbits[i].getPoint().getY()] = 'R';
				map.getMap()[tiger[i].getPoint().getX()][tiger[i].getPoint().getY()] = 'T';
			}
			// Limpa do mapa
			map.viewMap();
			System.out.println();
			for (int i = 0; i < qtd; i++) {
				map.getMap()[rabbits[i].getPoint().getX()][rabbits[i].getPoint().getY()] = '.';
				map.getMap()[tiger[i].getPoint().getX()][tiger[i].getPoint().getY()] = '.';
			}

			// Move no mapa
			for (int i = 0; i < qtd; i++) {
				rabbits[i].move(mapSize);
				tiger[i].move(mapSize);
			}
			Thread.sleep(1500);
		}
	}

}