package ed.estructuras.lineales;

/**
 * Un término está formado por una variable y su grado.
 */
public class Termino {
	char variable;
	int grado;

	public Termino(char variable, int grado) {
		this.variable = variable;
		this.grado = grado;
	}

	public char getVariable() {
		return this.variable;
	}

	public int getGrado() {
		return this.grado;
	}

	private ListaDoblementeLigada<Termino> mantieneVariablesEnComun(Monomio m) {

		return null;
	}




	/**
	 * Revisa si los dos términos tienen la misma variable.
	 */
	public boolean mismaVariable(Termino t) {
		return Character.toString(this.getVariable()).equals(Character.toString(t.getVariable()));
	}

	/**
	 * Multiplicación de monomios con la misma variable
	 */
	public Termino multiplica(Termino t) {
		return new Termino(this.getVariable(), this.sumaCoeficientes(t));
	}

	/**
	 * Método auxiliar para cuando se multiplican términos.
	 * Suma el grado de las variables.
	 */
	public int sumaCoeficientes(Termino t) {
		return this.getGrado() + t.getGrado();
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}

		if(o == null || o.getClass() != this.getClass()) {
			return false;
		}

		//DUda
		//char cannot be dereferenced
		Termino rep = (Termino)o;
		return Character.toString(this.getVariable()).equals(Character.toString(rep.getVariable()))
			&& this.getGrado() == rep.getGrado();

	}

	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder();
		representacion.append("(");
		representacion.append(Character.toString(this.variable));
		representacion.append("^");
		//Revisar por qué funciona el integer.toString
		representacion.append(Integer.toString(this.grado));
		representacion.append(")");
		return representacion.toString();
	}


}
