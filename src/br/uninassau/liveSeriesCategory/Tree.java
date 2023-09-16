package br.uninassau.liveSeriesCategory;

import java.util.Random;
import java.util.Set;

import br.uninassau.settings.Coordinates;

public class Tree {
	Random random = new Random();
	protected Coordinates point;

	// Todo animal deve ser criado já tendo posição
	public Tree(Set<Coordinates> posicoesUsadas, int mapSize) {
		this.point = setSpawn(posicoesUsadas, mapSize); // recebe o metodo o valor
	}

	// gera os spawn sem repetir a posição de outro mob
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
}
