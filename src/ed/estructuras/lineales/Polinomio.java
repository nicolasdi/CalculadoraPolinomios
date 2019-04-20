package ed.estructuras.lineales;

/**
 * Clase para representar polinomios.*/

/*
 * Detalles de la implementación; un polinomio es una lista doblemente
 * ligada de monomios.
 */
public class Polinomio {

	private ListaDoblementeLigada<Monomio> misMonomios;

	//Constructor ya sirve
	public Polinomio(double[] coeficientes, String[] variables, String[] exponentes) {
		//Verificamos tener las variables necesarias para poder crear un polinomio
		if(!((coeficientes.length == variables.length)
		     && ( variables.length == exponentes.length))) {
			throw new IllegalArgumentException();
		}
		this.misMonomios = new ListaDoblementeLigada<Monomio>();

		//Se construyen los monomios del polinomio
		for(int i = 0; i < coeficientes.length; i++) {
			//está recibiendo int coeficiente, string variales, string
			//exponentes(separados por ,)
			Monomio nuevo = new Monomio(coeficientes[i], variables[i], exponentes[i]);
			this.misMonomios.add(nuevo);
			}
	}

	public void suma(Polinomio sumando) {
		return;
	}


	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder();
		for(Monomio mono: this.misMonomios) {
			representacion.append(mono.toString());
			representacion.append(" + ");
				}

		return representacion.toString();
	}

}
