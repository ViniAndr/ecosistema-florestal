package liveSeriesCategory;

import java.util.Random;
import java.util.Set;

import settings.Coordinates;

public class Tree {
	Random random = new Random();
	protected Coordinates point;

	// Todo animal deve ser criado j� tendo posi��o
	public Tree(Set<Coordinates> posicoesUsadas, int mapSize) {
		this.point = setSpawn(posicoesUsadas, mapSize); // recebe o metodo o valor
	}

	// gera os spawn sem repetir a posi��o de outro mob
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
}
