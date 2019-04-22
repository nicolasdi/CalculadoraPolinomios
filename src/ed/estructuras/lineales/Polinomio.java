package ed.estructuras.lineales;

import java.util.ListIterator;
import java.util.Iterator;

/**
 * Clase para representar polinomios.*/

/*
 * Detalles de la implementación; un polinomio es una lista doblemente
 * ligada que almacena monomios.
 */
public class Polinomio {

	private ListaDoblementeLigada<Monomio> misMonomios;

	/**
	 * Construye a un Polinomio.
	 * @param coeficientes
	 * @param variables
	 * @param exponentes
	 */
	public Polinomio(double[] coeficientes, String[] variables, String[] exponentes) {
		//Verificar que el tamaño de los arreglos sean mayor que cero
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

	/*
	 * Constructor que crea un polinomio vacío, utilizado para la
	 * suma y multiplicación.
	 */
	private Polinomio() {
		this.misMonomios = new ListaDoblementeLigada<Monomio>();
	}

	/*
	 * Constructor que recibe como parámetro una lista de Monomios,
	 * utilizado para copiar polinomios.
	 */
	private Polinomio(ListaDoblementeLigada<Monomio> misMonomios) {
		this.misMonomios = misMonomios;
	}

	/*
	 * Se agrega el monomio al polinomio.  Este método no es
	 * totalmente necesario, pero se deja para que el método suma sea
	 * más legible.
	 */
	private void agregaMonomio(Monomio nuevo) {
		this.misMonomios.add(nuevo);
	}

	/**
	 * Devuelve una copia del polinomio que manda a llamar al método
	 */
	public Polinomio copia() {
		return new Polinomio(this.copiaMonomios());
	}

	/*
	 * Devuelve una lista con una copia de los monomios del polinomio
	 * que manda a llamar al método.
	 */
	//Duda: aqui se copia sólo la lista, los monomios en referencia
	//siguen siendo los mismos ¿verdad?
	private ListaDoblementeLigada<Monomio> copiaMonomios() {
		ListaDoblementeLigada<Monomio> copia = new ListaDoblementeLigada<Monomio>();

		Iterator<Monomio> copiador = this.getMisMonomios().iterator();
		while(copiador.hasNext()) {
			copia.add(copiador.next());
		}
		return copia;
	}

	/**
	 * Elimina los Monomios cero de este polinomio.
	 */
	public void eliminaMonomiosCero() {
		Iterator<Monomio> checador = this.getMisMonomios().iterator();
		while(checador.hasNext()) {
			if(checador.next().soyMonomioCero()) {
				checador.remove();
			}
		}
	}

	/**
	 * Devulve la lista de monomios que conforman al polinomio.
	 */
	public ListaDoblementeLigada<Monomio> getMisMonomios() {
		return this.misMonomios;
	}

	/**
	 * La multiplicación de dos polinomios corresponde con la
	 * multiplicación algebraica de polinomios.
	 */
	public Polinomio multiplica(Polinomio factor) {
		Polinomio resultado = new Polinomio();
		/*
		for(Monomio f1 : factor.getMisMonomios()) {
			for(Monomio f2 : this.getMisMonomios()) {
				resultado.suma(f1.multiplica(f2));
			}
			}*/

		return resultado;
	}

	/**
	 * Simplifica este polinomi; agrupando términos semejantes y
	 * eliminando monomios cero.
	 */
	public void simplifica() {
		this.eliminaMonomiosCero();
	}

	/**
	 * Nos dice si el polinomio es el polinomio cero ie. su
	 * coeficiente es cero para todos los monomios que lo componen.
	 */
	public boolean soyPolinomioCero() {
		//si no tiene monomios
		if(this.getMisMonomios().isEmpty() ) return true;

		//Verificamos si todos sus monomios son el monomio cero
		for(Monomio elemento : this.getMisMonomios() ) {
			//Basta con que uno de sus monomios sea distinto del
			//monomio cero para poder afirmar que no es el polinomio
			//cero
			if(!elemento.soyMonomioCero()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Se suma este polinomio con el polinomio recibido. La suma
	 * concuerda con la definición algebraica de suma.
	 * @param sumando polinomio con quien se va a sumar este
	 * polinomio.
	 */
	/* Se parte de la suposición de que el polinomio está
	 * simplificado. ie. no existe en el polinomio el monomio xy y el
	 * yx. */
	public Polinomio suma(Polinomio sumando) {
		//Si this es un polinomio vacío
		if(this.getMisMonomios().isEmpty()) {
			return sumando.copia();
		}

		//Si sumando es un polinomio vacío
		if(sumando.getMisMonomios().isEmpty()) {
			return this.copia();
		}

		Polinomio resultado = new Polinomio();

		//Se copia la lista para poder manipular a los monomios del
		//polinomio this sin afectarlo.
		ListaDoblementeLigada<Monomio> copia = this.copiaMonomios();

		for(Monomio elemento : sumando.getMisMonomios()) {
			Monomio aux = elemento;

			//Dado que no es seguro utilizar for-each si
			//se quiere utilizar .remove() utilizamos un
			//iterador con sintaxis usual.

			//Se busca si hay algún monomio con quien pueda sumar su
			//coeficiente.
			Iterator<Monomio> elementos2 = copia.iterator();

			//Se inicializa en null para que no lance el error "aux2
			//might not have been initilized, es seguro hacer esto
			//pues sabemos que el polinomio this tiene al menos un
			//monomio, por la verificación al inicio del método.
			Monomio aux2 = null;
			while(elementos2.hasNext()) {
				aux2 = elementos2.next();

				//Si se encontró a alguien con quien sumar, luego de
				//sumarlo y agregarlo al nuevo polinomio, se va al
				//siguiente monomio del polinomio sumando.
				if(aux.sumable(aux2)) {
					elementos2.remove();
					resultado.agregaMonomio(aux.suma(aux2));
					break;
				}
			}

			//Si no se pudo sumar con nadie.

			//Esta verificación es
			//necesaria para que se agrege al polinomio sólo si no
			//encontró a nadie con quién sumarse.
			if(!aux.sumable(aux2)) {
				resultado.agregaMonomio(aux);
			}
		}

		//Se agregan los monomios del polinomio this que no tuvieron
		//monomios para sumarse.
		for(Monomio elemento : copia) resultado.agregaMonomio(elemento);
		//AGREGAR MÉTODO SIMPLIFICA
		resultado.eliminaMonomiosCero();
		return resultado;
	}

	@Override
	public String toString() {
		if(this.misMonomios.isEmpty()) {
			return "Polinomio Cero";
		}

		StringBuilder representacion = new StringBuilder();
		ListIterator<Monomio> asistente = this.misMonomios.listIterator();

		for(int i = 0; i < this.misMonomios.size() - 1; i++) {
			representacion.append(asistente.next());
			representacion.append(" + ");
		}
		representacion.append(asistente.next());
		return representacion.toString();
	}
}
