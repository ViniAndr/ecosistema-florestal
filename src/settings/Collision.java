package settings;

import java.util.ArrayList;
import java.util.Iterator;

import animal.Rabbit;
import animal.Tiger;
import liveSeriesCategory.Animal;
import liveSeriesCategory.Tree;

public class Collision {
	public void collisionTigerAndRabbit(ArrayList<Tiger> tigers, ArrayList<Rabbit> rabbits) {
	    // permite que você itere sobre uma coleção de forma segura
		Iterator<Rabbit> rabbitIterator = rabbits.iterator(); // usei para não gerar um erro
	    //Se retorna true significa que há pelo menos mais um elemento na coleção a ser iterado.
	    while (rabbitIterator.hasNext()) {
	        Rabbit rabbit = rabbitIterator.next(); // obtém o próximo elemento da lista.
	        for (Tiger tiger : tigers) {
	            if (tiger.getPoint().equals(rabbit.getPoint())) {
	                System.out.println("Colidiu");
	                rabbitIterator.remove(); // Remove o Rabbit da lista de forma segura
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

	
	public boolean collisionAnimalAndAnimal(Animal animal, ArrayList<? extends Animal> animals) {
	    for (Animal otherAnimal : animals) {
	        if (!animal.equals(otherAnimal) && animal.getPoint().equals(otherAnimal.getPoint())) {
	            return true; // Colisão entre animais da mesma classe
	        }
	    }
	    return false; // Nenhuma colisão
	}
}
