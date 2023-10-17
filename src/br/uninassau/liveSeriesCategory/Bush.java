package br.uninassau.liveSeriesCategory;

import java.util.Random;
import java.util.Set;

import br.uninassau.settings.Coordinates;

public class Bush extends Tree {
	Random random = new Random();
	private int cycleToGrow = 0;

	public Bush(Set<Coordinates> posicoesUsadas, int mapSize) {
		super(posicoesUsadas, mapSize);
	}

	// método para criar novos arbustos
	public boolean newBush() {
		cycleToGrow++;
		if (cycleToGrow == 5) {
			// reset do ciclo de cresimento
			cycleToGrow = 0;
			return true;
		} else
			return false;
	}

	// uma quantidade de abusto aleatoria nascerá
	public int quantityOfNewBush() {
		return random.nextInt(8, 10);
	}
}
