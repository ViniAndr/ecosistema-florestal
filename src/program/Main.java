package program;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import settings.Collision;
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

		ArrayList<Tiger> tiger = new ArrayList<Tiger>();
		ArrayList<Rabbit> rabbit = new ArrayList<Rabbit>();

		for (int i = 0; i < qtd; i++) {
			rabbit.add(new Rabbit(positionsUsed, mapSize));
			tiger.add(new Tiger(positionsUsed, mapSize));
		}
		Collision collision = new Collision();

		while (true) {
			//Checa colisão entre os objetos ao tiger e rabbit.
			collision.collisionTigerAndRabbit(tiger, rabbit);

			//percorre todaa lista do objeto e caso exista ele pega a posição e spawna no mapa
			for (Tiger i : tiger) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'T');
			}
			for (Rabbit i : rabbit) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'R');;
			}

			// Mostra o Mapa
			map.viewMap();
			System.out.println(); // da um espaço

			// remove os animais da posição em que está 
			for (Tiger i : tiger) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
			}
			for (Rabbit i : rabbit) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
			}

			// Move os animais no mapa
			for (Tiger i : tiger) {
				i.move(mapSize);
			}
			for (Rabbit i : rabbit) {
				i.move(mapSize);
			}
			Thread.sleep(1000);
		}
	}
}