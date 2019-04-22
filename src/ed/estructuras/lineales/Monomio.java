package ed.estructuras.lineales;

import java.util.Arrays;
import java.util.Iterator;

/**
 *  Clase que nos permite representar monomios. Un monomio está
 *  conformado por un coeficiente y variables donde cada una de ellas
 *  está elevada a algún grado.
 */
public class Monomio{
	/** Coeficiente que acompaña al monomio. */
	private double coeficiente;
	/** Términos que conforman a un monomio. */
	ListaDoblementeLigada<Termino> misTerminos;

	///////////Agregar constructor cuando se tiene sólo un número ex. 4 como monomio
	/**
	 * Crea un monomio
	 * @param coeficiente
	 */
	public Monomio(double coeficiente,
	               String variables,
	               String exponentes) {
		//Verificar que en cada cadena vengan sólo letras o números
		//Verificar exponentes sean enteros y mayores que cero
		//si algo falla en este constructor, revisar el  parse porque estoy guardando integers en un arreglo de int's
		//Hacer que los monomios se simplifiquen conforme se vayan agregando, ie. si entra xx simplificar  por x^2

		//
		this.coeficiente = coeficiente;
		char[] cadaVariable = variables.toCharArray();
		String[] cadaExponente = exponentes.split(",");
		this.misTerminos = new ListaDoblementeLigada<Termino>();

		//se verifica que exista la misma cantidad de exponentes que
		//de variables
		if(cadaVariable.length != cadaExponente.length) {
			throw new IllegalArgumentException("Cantidad de variables  y exponentes incorrecta");
		}

		//Se almacena cada término en la lista
		for(int i = 0; i < cadaVariable.length; i++) {
			Termino nuevo = new Termino(cadaVariable[i],
			                            Integer.parseInt(cadaExponente[i]));
			this.misTerminos.add(nuevo);
			}
	}

	private Monomio(Double coeficiente, ListaDoblementeLigada<Termino> misTerminos) {
		this.coeficiente = coeficiente;
		this.misTerminos = misTerminos;
	}

	//Crea a un monomio vacío
	private Monomio() {
		this.misTerminos = new ListaDoblementeLigada<Termino>();
	}

	public double getCoeficiente() {
		return this.coeficiente;
	}

	public ListaDoblementeLigada<Termino> getMisTerminos() {
		return this.misTerminos;
	}

	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}

	//Método que nos dice si es posible sumar los coeficientes dos dos
	//monomios
	public boolean sumable(Monomio o) {

		//Verificación misma cantidad de variables
		if(this.getMisTerminos().size() != o.getMisTerminos().size()) {
			return false;
		}

		//Verificación de término por término que acompaña al monomio
		Iterator<Termino> checador = this.getMisTerminos().iterator();
		while(checador.hasNext()) {
			if(!o.getMisTerminos().contains(checador.next())) {
				return false;
			}
		}

		return true;
	}

	/* Dado que el único que usará este método es la clase Polinomio y
	 * se pide que se devuelva un monomio nuevo, en esta
	 * implementación de suma de monomios se crea y devuelve un
	 * Monomio nuevo. Además este método sólo debe ser usado después de verificar que sumable devuelva true. Dado que es algo tardado verificar que dos monomios sean sumables, no se manda a llamar dentro de este método a _sumable_  */
	public Monomio suma(Monomio sumando) {
		if(sumando == null) {
			throw new IllegalArgumentException();
		}

		return new Monomio(this.getCoeficiente()
		                   + sumando.getCoeficiente(),
		                   this.copiaTerminos());
	}

	/*Se copian los términos de este monomio en una nueva lista, para poder ser usados al crear un  nuevo  monomio. */
	private ListaDoblementeLigada<Termino> copiaTerminos() {
		ListaDoblementeLigada<Termino> copia = new ListaDoblementeLigada<Termino>();
		Iterator<Termino> copiador = this.getMisTerminos().iterator();
		while(copiador.hasNext()) {
			copia.add(copiador.next());
		}

		return copia;
	}

	public boolean soyMonomioCero() {
		return this.coeficiente == 0;
	}


	//Partimos de la suposición de que un monomio siempre está simplificado ie, no existen xx, existen  x^2
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}

		if(o == null || o.getClass()!= this.getClass()) {
			return false;
		}

		Monomio representacion = (Monomio)o;

		//Verificación mismo coeficiente
		if(this.getCoeficiente() != representacion.getCoeficiente()) {
			return false;
		}

		//Verificación misma cantidad de variables
		if(this.getMisTerminos().size() != representacion.getMisTerminos().size()) {
			return false;
		}

		//Verificación de término por término que acompaña al monomio
		//Basta verificar una contención porque ya se verificó que tengan la misma cantidad, no podría pasar que tengan la misma cardinalidad de variables, unas variables estuvieran contenidas en un monomio y en el otro monomio tuviera variables extras.
		Iterator<Termino> checador = this.getMisTerminos().iterator();
		while(checador.hasNext()) {
			if(!representacion.getMisTerminos().contains(checador.next())) {
				return false;
			}
		}

		return true;
	}

	public Monomio multiplica(Monomio factor) {
		Monomio resultado = new Monomio();
		resultado.setCoeficiente(this.getCoeficiente() * factor.getCoeficiente());

		//Los términos que sólo están en this
		ListaDoblementeLigada<Termino> thisMenosFactor = this.copiaTerminos();

		thisMenosFactor.removeAll(factor.getMisTerminos());

		//Terminos que sólo están en factor
		ListaDoblementeLigada<Termino> factorMenosThis = factor.getMisTerminos();

		factorMenosThis.removeAll(this.misTerminos);

		//Los términos que los monomios no tengan en común se agregan directamente al monomio sin modificar su grado
		resultado.misTerminos.addAll(thisMenosFactor);
		resultado.misTerminos.addAll(factorMenosThis);

		//Hasta aquí todo bien

		//Las variables que tengan en común, modifican su grado antes de ser agregadas
		ListaDoblementeLigada<Termino> enComun = this.copiaTerminos();
		//variables que tienen en común
		enComun.retainAll(factor.getMisTerminos());
		System.out.println("aqui");
		System.out.println(enComun);

		//Se busca el termino con quien se va a sumar su exponente
		for(Termino t : enComun) {
			Termino aux = t;
			for(Termino e : factor.getMisTerminos()) {
				Termino aux2 = e;
				//Cuando se encuentra, termina el ciclo y seguimos con
				//el siguiente término.
				if(aux.mismaVariable(aux2)) {
					Termino nuevo = aux.multiplica(aux2);
					resultado.getMisTerminos().add(nuevo);
					break;
				}
			}
		}

		return resultado;
	}


	@Override
	public String toString() {
		StringBuilder representacion = new StringBuilder();
		//Coeficiente
		representacion.append(Double.toString(this.coeficiente));
		for(Termino elemento: misTerminos) {
			//¡Manda a llamar solito al método toString!
			representacion.append(elemento);
		}
		return representacion.toString();
	}

}
