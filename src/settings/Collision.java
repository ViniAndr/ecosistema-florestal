package settings;

import java.util.ArrayList;
import java.util.Iterator;

import animal.Rabbit;
import animal.Tiger;

public class Collision {
	public void collisionTigerAndRabbit(ArrayList<Tiger> tigers, ArrayList<Rabbit> rabbits) {
	    // permite que voc� itere sobre uma cole��o de forma segura
		Iterator<Rabbit> rabbitIterator = rabbits.iterator(); // usei para n�o gerar um erro
	    //Se retorna true significa que h� pelo menos mais um elemento na cole��o a ser iterado.
	    while (rabbitIterator.hasNext()) {
	        Rabbit rabbit = rabbitIterator.next(); // obt�m o pr�ximo elemento da lista.
	        for (Tiger tiger : tigers) {
	            if (tiger.getPoint().equals(rabbit.getPoint())) {
	                System.out.println("Colidiu");
	                rabbitIterator.remove(); // Remove o Rabbit da lista de forma segura
	                break; // Importante para sair do loop de Tigres
	            }
	        }
	    }
	}
}
