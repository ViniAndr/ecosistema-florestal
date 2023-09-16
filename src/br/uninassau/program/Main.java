package br.uninassau.program;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.uninassau.animal.Rabbit;
import br.uninassau.animal.Tiger;
import br.uninassau.liveSeriesCategory.Tree;
import br.uninassau.settings.Collision;
import br.uninassau.settings.Coordinates;
import br.uninassau.settings.Map;
import br.uninassau.settings.Menu;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// Conjunto de elementos que não se repetem. As posições
		Set<Coordinates> positionsUsed = new HashSet<Coordinates>();

		// Config do menu
		Scanner in = new Scanner(System.in);
		Menu menu = new Menu(in);
		in.close();

		// config do mapa
		Map map = new Map(menu.getMapSize());

		// configuando para ter varios ou 1 do mesmo objeto
		ArrayList<Tiger> tiger = new ArrayList<Tiger>();
		ArrayList<Rabbit> rabbit = new ArrayList<Rabbit>();
		ArrayList<Tree> tree = new ArrayList<Tree>();

		// criação dos objetos
		for (int i = 0; i < menu.getAmountTree(); i++) {
			tree.add(new Tree(positionsUsed, menu.getMapSize()));
		}

		for (int i = 0; i < menu.getAmountTiger(); i++) {
			tiger.add(new Tiger(positionsUsed, menu.getMapSize()));
		}

		for (int i = 0; i < menu.getAmountRabbit(); i++) {
			rabbit.add(new Rabbit(positionsUsed, menu.getMapSize()));
		}

		Collision collision = new Collision();

		while (true) {
			// Checa colisão entre os objetos tiger e rabbit e faz uma ação.
			collision.collisionTigerAndRabbit(tiger, rabbit);

			// percorre todaa lista do objeto e caso exista ele passa a posição de spawn
			// para o mapa
			for (Tiger i : tiger) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'T');
			}
			for (Rabbit i : rabbit) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'R');
				;
			}
			for (Tree t : tree) {
				map.addObjectOnMap(t.getPoint().getX(), t.getPoint().getY(), '*');
			}

			// Mostra o Mapa
			map.viewMap();
			System.out.println(); // da um espaço Temp

			// remove os animais da posição em que está no mapa
			for (Tiger i : tiger) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
			}
			for (Rabbit i : rabbit) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
			}

			// Move os animais no mapa
			for (Tiger tg : tiger) {
				// Gere uma nova posição até encontrar uma posição válida (não colidir com
				// árvores)
				do {
					tg.move(menu.getMapSize());
				} while (collision.collisionAnimalAndAnimal(tg, tiger) || collision.collisionAnimalAndTree(tg, tree));
			}
			for (Rabbit rb : rabbit) {
				// Gere uma nova posição até encontrar uma posição válida (não colidir com
				// árvores)
				do {
					rb.move(menu.getMapSize());
				} while (collision.collisionAnimalAndAnimal(rb, rabbit) || collision.collisionAnimalAndTree(rb, tree));
			}
			//um timer para gerar uma atualização no mapa
			Thread.sleep(1000);
		}
	}
}