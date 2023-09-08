package liveSeriesCategory;

import java.util.Random;
import java.util.Set;

import settings.Coordinates;

public class Animal {
	Random random = new Random();
	protected Coordinates point;

	// Todo animal deve ser criado já tendo posição
	public Animal(Set<Coordinates> posicoesUsadas) {
		// vai gerar as posições aleatoriamente
		Random random = new Random();
		// uma var do tipo do conjunto
		Coordinates ponto;
		do {
			int x = random.nextInt(8);
			int y = random.nextInt(8);
			ponto = new Coordinates(x, y); // instacia da class Coordinates com valores já definidos
		} while (posicoesUsadas.contains(ponto));// Verifica se já existe esses valores no conjunto
		posicoesUsadas.add(ponto); // caso não exista ele já adiciona a conjunto para não repetir
		this.point = ponto; // atribui a coordenada não repetida ao valor dessa var do tipo Coordinates
	}

	public Coordinates getPoint() {
		return point;
	}

	// Mover do animais padronizados
	public void move(int mapSize) {
		int direction = random.nextInt(4); // 0 a 3

		// deixar OBS => NO MAPA X E Y NÃO IGUAL AO PLANO CARTESIANO.
		// X É VERTICAL E Y É HORIZONTAL
		int x = point.getX();
		int y = point.getY();

		switch (direction) {
		case 0: {
			// norte
			// operador ternario para verificar se não vai sair do mapa
			point.setX((x - 1) != -1 ? x - 1 : x);
			break;
		}
		case 1: {
			// leste
			point.setY((y + 1) != 8 ? y + 1 : y);
			break;
		}
		case 2: {
			// sul
			point.setX((x + 1) != 8 ? x + 1 : x);
			break;
		}
		case 3: {
			// oeste
			point.setY((y - 1) != -1 ? y - 1 : y);
			break;
		}
		}
	}
}
