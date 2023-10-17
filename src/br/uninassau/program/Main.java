package br.uninassau.program;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import br.uninassau.animal.Deer;
import br.uninassau.animal.Rabbit;
import br.uninassau.animal.Tiger;
import br.uninassau.liveSeriesCategory.Bush;
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

		// receber o valor do ciclo
		int cycle = map.getCycle();

		// configuando para ter varios ou 1 do mesmo objeto
		ArrayList<Tiger> tigers = new ArrayList<Tiger>();
		ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>();
		ArrayList<Deer> deers = new ArrayList<Deer>();
		ArrayList<Tree> trees = new ArrayList<Tree>();
		ArrayList<Bush> bushs = new ArrayList<Bush>();

		// criação dos objetos
		for (int i = 0; i < menu.getAmountTree(); i++) {
			trees.add(new Tree(positionsUsed, menu.getMapSize()));
		}
		for (int i = 0; i < menu.getAmountTiger(); i++) {
			tigers.add(new Tiger(positionsUsed, menu.getMapSize()));
		}
		for (int i = 0; i < menu.getAmountRabbit(); i++) {
			rabbits.add(new Rabbit(positionsUsed, menu.getMapSize()));
		}
		for (int i = 0; i < menu.getAmountDeer(); i++) {
			deers.add(new Deer(positionsUsed, menu.getMapSize()));
		}
		for (int i = 0; i < menu.getAmountBush(); i++) {
			bushs.add(new Bush(positionsUsed, menu.getMapSize()));
		}

		Collision collision = new Collision();

		while (true) {

			// Checa colisão entre os objetos tiger e rabbit e faz uma ação.
			collision.collisionTigerAndRabbit(tigers, rabbits);
			collision.collisionTigerAndDeer(tigers, deers);
			// herbivoros colidirem com o alimento
			collision.collisionRabbitAndBush(positionsUsed ,rabbits, bushs);
			collision.collisionDeerAndBush(positionsUsed, deers, bushs);

			// percorre todaa lista do objeto e caso exista ele passa a posição de spawn
			// para o mapa
			// o abusto esta acima de todos, para no mapa o caractere não sobrescrever
			for (Bush i : bushs) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), '*');
			}
			if(!tigers.isEmpty()) {
			for (Tiger i : tigers) {
				if (i.getLastMeal() == 0)
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'X');
				else
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'T');
			}
			}
			for (Rabbit i : rabbits) {
				// caso o último ciclo que ele se alimetou é 0, lógica abaixo para deixar verde
				if (i.getLastMeal() == 0)
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'c');
				else
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'C');
			}
			for (Deer i : deers) {
				if (i.getLastMeal() == 0)
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'v');
				else
					map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), 'V');
			}
			for (Tree i : trees) {
				map.addObjectOnMap(i.getPoint().getX(), i.getPoint().getY(), '#');
			}

			// Add 1 ao valor do ciclo
			map.setCycle(cycle++);

			// Mostra o Mapa
			map.viewMap(tigers.size(), rabbits.size(), deers.size(), bushs.size(), collision);
			System.out.println(); // da um espaço Temp

			// após o mapa atualizar que verifica.
			if(tigers.isEmpty()) {
				System.out.println("Todos Carnivoros forma extintos");
				System.out.println("Os herbivoros viveram em Paz e sofreram uma evolucao genetia");
				break;
			}
			// caso não tenha mais coelho e nem veado ele finaliza.
			if (rabbits.isEmpty() && deers.isEmpty()) {
				System.out.println("Todos Herbivoros forma extintos");
				System.out.println("Devido a falta de comida os tigre comeram sua propria especie e fim.");
				break;
			}
			// remove os animais da posição em que está no mapa
			for (Tiger i : tigers) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
				// Gere uma nova posição até encontrar uma posição válida (não colidir com
				// árvores e nem com um animal do mesmo tipo)
				do {
					i.move(menu.getMapSize());
				} while (collision.collisionAnimalAndAnimal(i, tigers) || collision.collisionAnimalAndTree(i, trees));
			}
			for (Rabbit i : rabbits) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
				do {
					i.move(menu.getMapSize());
				} while (collision.collisionAnimalAndAnimal(i, rabbits) || collision.collisionAnimalAndTree(i, trees));
			}
			for (Deer i : deers) {
				map.removeObjectOnMap(i.getPoint().getX(), i.getPoint().getY());
				do {
					i.move(menu.getMapSize());
				} while (collision.collisionAnimalAndAnimal(i, rabbits) || collision.collisionAnimalAndTree(i, trees));

			}

			// Percorre a lista de tigres e remove os tigres mortos de fome
			// hasNext() retorna true se ainda houver elementos na coleção para percorrer.
			for (Iterator<Tiger> iterator = tigers.iterator(); iterator.hasNext();) {
				// retorna o próximo elemento na coleção
				Tiger tiger = iterator.next();
				if (tiger.starve()) {
					iterator.remove(); // Remove o tigre da lista se estiver morto
				}
			}
			for (Iterator<Rabbit> iterator = rabbits.iterator(); iterator.hasNext();) {
				Rabbit rabbit = iterator.next();
				if (rabbit.starve()) {
					iterator.remove();
				}
			}
			for (Iterator<Deer> iterator = deers.iterator(); iterator.hasNext();) {
				Deer deer = iterator.next();
				if (deer.starve()) {
					iterator.remove();
				}
			}
			// se o array tiver vazil ou com elemento
			if (bushs.isEmpty() || !bushs.isEmpty()) {
				if (bushs.isEmpty()) // se tiver vazil, plante um para eu usar para nascer os outros
					bushs.add(new Bush(positionsUsed, menu.getMapSize()));
				if (bushs.get(0).newBush()) {
					for (int i = 0; i < bushs.get(0).quantityOfNewBush(); i++) {
						bushs.add(new Bush(positionsUsed, menu.getMapSize()));
					}
				}
			}
			// um timer para gerar uma atualização no mapa
			Thread.sleep(3000);
		}
	}
}