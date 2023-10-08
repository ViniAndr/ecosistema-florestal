package br.uninassau.settings;

import java.util.ArrayList;
import java.util.Iterator;

import br.uninassau.animal.Deer;
import br.uninassau.animal.Rabbit;
import br.uninassau.animal.Tiger;
import br.uninassau.liveSeriesCategory.Animal;
import br.uninassau.liveSeriesCategory.Bush;
import br.uninassau.liveSeriesCategory.Tree;

public class Collision {
	protected boolean animalIsDead = false;

	// Essas variaveis abaixo junto com os get e set, são para o X no map
	private int assistancePositionX = 1000;
	private int assistancePositionY = 1000;

	public int getAssistancePositionX() {
		return assistancePositionX;
	}

	public int getAssistancePositionY() {
		return assistancePositionY;
	}

	public void setAssistancePositionX(int assistancePositionX) {
		this.assistancePositionX = assistancePositionX;
	}

	public void setAssistancePositionY(int assistancePositionY) {
		this.assistancePositionY = assistancePositionY;
	}

	public void collisionTigerAndRabbit(ArrayList<Tiger> tigers, ArrayList<Rabbit> rabbits) {
		// permite que você itere sobre uma coleção de forma segura
		Iterator<Rabbit> rabbitIterator = rabbits.iterator(); // usei para não gerar um erro
		// Se retorna true significa que há pelo menos mais um elemento na coleção a ser
		// iterado.
		while (rabbitIterator.hasNext()) {
			Rabbit rabbit = rabbitIterator.next(); // obtém o próximo elemento da lista.
			for (Tiger tiger : tigers) {
				if (tiger.getPoint().equals(rabbit.getPoint())) {
					animalIsDead = true;
					assistancePositionX = tiger.getPoint().getX(); // serve apenas para mostar o local
					assistancePositionY = tiger.getPoint().getY(); // serve apenas para mostar o local
					rabbitIterator.remove(); // Remove o Rabbit da lista de forma segura
					tiger.food(); // falo qual foi o ciclo que ele comeu
					break; // Importante para sair do loop de Tigres
				}
			}
		}
	}

	public void collisionTigerAndDeer(ArrayList<Tiger> tigers, ArrayList<Deer> deers) {
		// permite que você itere sobre uma coleção de forma segura
		Iterator<Deer> deerIterator = deers.iterator(); // usei para não gerar um erro
		// Se retorna true significa que há pelo menos mais um elemento na coleção a ser
		// iterado.
		while (deerIterator.hasNext()) {
			Deer deer = deerIterator.next(); // obtém o próximo elemento da lista.
			for (Tiger tiger : tigers) {
				if (tiger.getPoint().equals(deer.getPoint())) {
					animalIsDead = true;
					assistancePositionX = tiger.getPoint().getX(); // serve apenas para mostar o local
					assistancePositionY = tiger.getPoint().getY(); // serve apenas para mostar o local
					deerIterator.remove(); // Remove o veado da lista de forma segura
					tiger.food();
					break; // Importante para sair do loop de Tigres
				}
			}
		}
	}

	public boolean collisionAnimalAndTree(Animal animal, ArrayList<Tree> trees) {
		for (Tree tree : trees) {
			if (animal.getPoint().equals(tree.getPoint())) {
				return true; // Colisão entre o animal e a árvore
			}
		}
		return false; // Nenhuma colisão
	}

	// ? extends Animal => caractere coringa. qaulquer classe que seja extends de
	// Animal
	public boolean collisionAnimalAndAnimal(Animal animal, ArrayList<? extends Animal> animals) {
		for (Animal otherAnimal : animals) {
			if (!animal.equals(otherAnimal) && animal.getPoint().equals(otherAnimal.getPoint())) {
				return true; // Colisão entre animais da mesma classe
			}
		}
		return false; // Nenhuma colisão
	}

	// coelho comer um arbusto
	public void collisionRabbitAndBush(ArrayList<Rabbit> rabbits, ArrayList<Bush> bushs) {
		// permite que você itere sobre uma coleção de forma segura
		Iterator<Bush> bushIterator = bushs.iterator(); // usei para não gerar um erro
		// Se retorna true significa que há pelo menos mais um elemento na coleção a ser
		// iterado.
		while (bushIterator.hasNext()) { // retorna true se tem um proximo elemento
			Bush bush = bushIterator.next(); // obtém o próximo elemento da lista.
			for (Rabbit rabbit : rabbits) {
				if (rabbit.getPoint().equals(bush.getPoint())) {
					bushIterator.remove(); // Remove o arbusto da lista de forma segura
					rabbit.food();
					break; // Importante para sair do loop de Tigres
				}
			}
		}
	}

	// veado comer um arbusto
	public void collisionDeerAndBush(ArrayList<Deer> deers, ArrayList<Bush> bushs) {
		Iterator<Bush> bushIterator = bushs.iterator();
		while (bushIterator.hasNext()) {
			Bush bush = bushIterator.next();
			for (Deer deer : deers) {
				if (deer.getPoint().equals(bush.getPoint())) {
					bushIterator.remove();
					deer.food();
					break;
				}
			}
		}
	}
}
