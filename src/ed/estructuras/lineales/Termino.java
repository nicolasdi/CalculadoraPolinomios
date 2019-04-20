package ed.estructuras.lineales;

/**
 * Un término está formado por una variable y su exponente.
 */
public class Termino {
	char variable;
	int exponente;

	public Termino(char variable, int exponente) {
		this.variable = variable;
		this.exponente = exponente;
	}

	public char getVariable() {
		return this.variable;
	}

	public int getExponente() {
		return this.exponente;
	}


}
