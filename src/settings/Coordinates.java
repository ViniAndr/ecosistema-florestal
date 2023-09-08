package settings;

import java.util.Objects;

public class Coordinates {
	private int x;
    private int y;

    //Construtor para já iniciar tendo esses valores
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //para pegar esses valores no MAIN
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates ponto = (Coordinates) obj;
        return x == ponto.x && y == ponto.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
