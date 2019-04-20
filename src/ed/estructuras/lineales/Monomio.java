package ed.estructuras.lineales;

import java.util.Arrays;

/**
 *  Clase que nos permite representar monomios. Un monomio está
 *  conformado por un coeficiente y variables donde cada una está
 *  elevada a algún exponente.
 */
public class Monomio{
	/** Coeficiente que acompaña al monomio. */
	double coeficiente;

	/** Almacén del los términos que acompañan al coeficiente. */
	char[] variables;

	/** Almacén de los exponentes de los coeficientes. */
	int[] exponentes;

	/**
	 * Construye un monomio.
	 * @param coeficiente el coeficiente que acompaña al monomio.
	 * @param variables las variables que conforman al monomio.
	 * @param exponente el exponente al que está elevado cada variable.
	 */
	public Monomio(double coeficiente,
	               String variables,
	               String exponentes) {
		if(coeficiente == 0) throw new IllegalArgumentException("El coeficiente debe ser distinto de cero");;
		//Verificar que en cada cadena vengan sólo letras o números
		//Verificar si  las longitudes de variables y exponentes sonla misma
		//Verificar exponentes sean enteros y mayores que cero
		//si algo falla en este constructor, revisar el  parse porque estoy guardando integers en un arreglo de int's

		//Hacer que los monomios se simplifiquen conforme se vayan agregando, ie. si entra xx simplificar  por x^2
		this.coeficiente = coeficiente;
		this.variables = variables.toCharArray();

		String[] aux = exponentes.split(",");
		this.exponentes = new int[aux.length];
		///*
		for(int i = 0; i < aux.length; i++) {
			this.exponentes[i] = Integer.parseInt(aux[i]);

			}
		//*/
	}

	public double getCoeficiente() {
		return this.coeficiente;
	}

	public char[] getVariables() {
		return this.variables;
	}

	public int[] getExponentes() {
		return this.exponentes;
	}

	//este código quedaría más bonito si me hubieras dejado usar
	//tablasHash, Vilchis :c

	/*
	//Suponemos que cada monomio ya está minimizado
	public boolean sumables(Monomio propuesto) {
		//Tienen diferente número de variables
		if(this.variables.length != propuesto.variables.length) {
			return false;
		}

		char[] miVerificador = this.getVariables().sort();
		char[] suVerificador = propuesto.getVariables().sort();

		//Se verifica que tengan las mismas variables
		for(int i = 0; i < miVerificador.length; i++) {
			if(miVerificador[i] != suVerificador[i]) {
				return false;
			}
		}

		//Se verifica que tengan los mismos exponentes
		for(int i = 0; i < variables.length(); i++) {

		}
		} */

	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder();
		representacion.append(Double.toString(this.coeficiente));
		for(int i = 0; i < this.variables.length ; i++) {
			representacion.append("("
			                      + Character.toString(this.variables[i])
			                      + "^"
			                      + Integer.toString(this.exponentes[i])
			                      + ")");
		}
		return representacion.toString();
	}

}
