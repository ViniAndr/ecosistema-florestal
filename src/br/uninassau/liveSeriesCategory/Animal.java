package br.uninassau.liveSeriesCategory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import br.uninassau.settings.Coordinates;

public class Animal {
	Random random = new Random();
	private Coordinates point;

	// Todo animal deve ser criado j� tendo posi��o
	public Animal(Set<Coordinates> posicoesUsadas, int mapSize) {
		this.point = setSpawn(posicoesUsadas, mapSize); // recebe o metodo o valor
	}

	// gera os spawn sem repetir a posi��o de outro objeto
	public Coordinates setSpawn(Set<Coordinates> posicoesUsadas, int mapSize) {
		// vai gerar as posi��es aleatoriamente
		Random random = new Random();
		// uma var do tipo do conjunto
		Coordinates ponto;
		do {
			int x = random.nextInt(mapSize);
			int y = random.nextInt(mapSize);
			ponto = new Coordinates(x, y); // instacia da class Coordinates com valores j� definidos
		} while (posicoesUsadas.contains(ponto));// Verifica se j� existe esses valores no conjunto
		posicoesUsadas.add(ponto); // caso n�o exista ele j� adiciona a conjunto para n�o repetir
		return ponto; // retorna o valor
	}

	public Coordinates getPoint() {
		return point;
	}
	
	public boolean isNextPositionValid(int newX, int newY, ArrayList<Tree> trees) {
	    // Verifique se a pr�xima posi��o � uma �rvore
	    for (Tree tree : trees) {
	        if (tree.getPoint().getX() == newX && tree.getPoint().getY() == newY) {
	            return false; // A pr�xima posi��o � uma �rvore, n�o � v�lida
	        }
	    }
	    return true; // A pr�xima posi��o n�o � uma �rvore, � v�lida
	}

	// Mover do animais padronizados
	public void move(int mapSize) {
		int direction = random.nextInt(4);
		int newX = point.getX();
		int newY = point.getY();

		switch (direction) {
		case 0: {
			// norte
			// operador ternario para verificar se n�o vai sair do mapa
			point.setY((newY - 1) != -1 ? (newY - 1) : newY);
			break;
		}
		case 1: {
			// leste
			point.setX((newX + 1) != mapSize ? (newX + 1) : newX);
			break;
		}
		case 2: {
			// sul
			point.setY((newY + 1) != mapSize ? (newY + 1) : newY);
			break;
		}
		case 3: {
			// oeste
			point.setX((newX - 1) != -1 ? (newX - 1) : newX);
			break;
		}
		}
	}
}
