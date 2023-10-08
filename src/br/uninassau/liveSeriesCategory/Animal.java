package br.uninassau.liveSeriesCategory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import br.uninassau.settings.Coordinates;

public class Animal {
	Random random = new Random();
	private Coordinates point;
	private int lastMeal = 1; // ultima refeição
	
	public int getLastMeal() {
		return lastMeal;
	}

	// Todo animal deve ser criado já tendo posição
	public Animal(Set<Coordinates> posicoesUsadas, int mapSize) {
		this.point = setSpawn(posicoesUsadas, mapSize); // recebe o metodo o valor
	}

	// gera os spawn sem repetir a posição de outro objeto
	public Coordinates setSpawn(Set<Coordinates> posicoesUsadas, int mapSize) {
		// vai gerar as posições aleatoriamente
		Random random = new Random();
		// uma var do tipo do conjunto
		Coordinates ponto;
		do {
			int x = random.nextInt(mapSize);
			int y = random.nextInt(mapSize);
			ponto = new Coordinates(x, y); // instacia da class Coordinates com valores já definidos
		} while (posicoesUsadas.contains(ponto));// Verifica se já existe esses valores no conjunto
		posicoesUsadas.add(ponto); // caso não exista ele já adiciona a conjunto para não repetir
		return ponto; // retorna o valor
	}

	public Coordinates getPoint() {
		return point;
	}

	public boolean isNextPositionValid(int newX, int newY, ArrayList<Tree> trees) {
		// Verifique se a próxima posição é uma árvore
		for (Tree tree : trees) {
			if (tree.getPoint().getX() == newX && tree.getPoint().getY() == newY) {
				return false; // A próxima posição é uma árvore, não é válida
			}
		}
		return true; // A próxima posição não é uma árvore, é válida
	}

	// Mover do animais padronizados
	public void move(int mapSize) {
		int direction = random.nextInt(4);
		int newX = point.getX();
		int newY = point.getY();

		switch (direction) {
		case 0: {
			// norte
			// operador ternario para verificar se não vai sair do mapa
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

	// morrer de fome
	public boolean starve() {
		// incrementa os ciclos sem comer
		lastMeal++;
		// se ficar mais de 10 dias sem comer morre
		return (lastMeal == 15) ? true : false;
	}

	// animal comeu zera os ciclos sem comida
	public void food() {
		lastMeal = 0;
	}
}
