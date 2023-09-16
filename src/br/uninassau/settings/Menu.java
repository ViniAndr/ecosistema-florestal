package br.uninassau.settings;

import java.util.Scanner;

public class Menu {

	private int mapSize;
	private int amountTiger;
	private int amountRabbit;
	private int amountTree;
	private int control;
	private boolean controlWhile = false;

	// assim que criado j� chamar o menu
	public Menu(Scanner in) {
		homeMenu(in);
	}

	public int getMapSize() {
		return mapSize;
	}

	public int getAmountTiger() {
		return amountTiger;
	}

	public int getAmountRabbit() {
		return amountRabbit;
	}

	public int getAmountTree() {
		return amountTree;
	}

	public void homeMenu(Scanner in) {
		do {
			System.out.println("+-------------------------------------------+");
			System.out.println("|      Ecosistema Florestal de Console      |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Iniciar nas configuracoes pradores    |");
			System.out.println("| 2 - Faca a sua configuracao               |");
			System.out.println("| 99 - Sair                                 |");
			System.out.println("+-------------------------------------------+");
			System.out.print("| Digite aqui: ");
			control = in.nextInt();
			if (control == 1) {
				mapSize = 8;
				amountRabbit = 3;
				amountTiger = 3;
				amountTree = 8;
				controlWhile = false;
			} else if (control == 2) {
				yourSettings(in);
				controlWhile = false;
			} else if (control == 99) {
				System.exit(0);
				controlWhile = false;
			} else {
				controlWhile = true;
			}
		} while (controlWhile);

	}

	public void yourSettings(Scanner in) {
		int amountMaxTiger = 0;
		int amountMaxRabbit = 0;
		int amountMaxTree = 0;
		do {
			System.out.println("+-------------------------------------------+");
			System.out.println("|            Suas Configuracoes             |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| Qual o tamanho do mapa:                   |");
			System.out.println("| 1 - 6x6                                   |");
			System.out.println("| 2 - 8x8                                   |");
			System.out.println("| 3 - 12x12                                 |");
			System.out.println("| 4 - 16x16                                 |");
			System.out.println("| 5 - 21x21                                 |");
			System.out.print("| Digite aqui: ");
			control = in.nextInt();
			if (control == 1) {
				mapSize = 6;
				amountMaxTiger = 3;
				amountMaxRabbit = 7;
				amountMaxTree = 10;
			} else if (control == 2) {
				mapSize = 8;
				amountMaxTiger = 5;
				amountMaxRabbit = 14;
				amountMaxTree = 18;
			} else if (control == 3) {
				mapSize = 12;
				amountMaxTiger = 7;
				amountMaxRabbit = 20;
				amountMaxTree = 25;
			} else if (control == 4) {
				mapSize = 16;
				amountMaxTiger = 10;
				amountMaxRabbit = 30;
				amountMaxTree = 38;
			} else if (control == 5) {
				mapSize = 21;
				amountMaxTiger = 18;
				amountMaxRabbit = 50;
				amountMaxTree = 60;
			}
		} while (control != 1 && control != 2 && control != 3 && control != 4 && control != 5);
		do {
			System.out.printf("| Qual a quantidade de tige(s)(MAXIMO de %d): ", amountMaxTiger);
			amountTiger = in.nextInt();
			System.out.println(amountTiger);
		} while (amountTiger > amountMaxTiger);
		do {
			System.out.printf("| Qual a quantidade de coelho(s)(MAXIMO de %d): ", amountMaxRabbit);
			amountRabbit = in.nextInt();
		} while (amountRabbit > amountMaxRabbit);
		do {
			System.out.printf("| Qual a quantidade de arvore(s)(MAXIMO de %d): ", amountMaxTree);
			amountTree = in.nextInt();
		} while (amountTree > amountMaxTree);
		System.out.println("+-------------------------------------------+");
	}
}