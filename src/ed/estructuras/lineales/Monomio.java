package ed.estructuras.lineales;

import java.util.Arrays;
import java.util.Iterator;

/**
 *  Clase que nos permite representar monomios. Un monomio está
 *  conformado por un coeficiente y una lista de términos.
 */
public class Monomio {
    /** Coeficiente que acompaña al monomio. */
    private double coeficiente;
    /** Términos que conforman a un monomio. */
    ListaDoblementeLigada<Termino> misTerminos;

    /**
     * Crea un monomio
     * @param coeficiente del monomio.
     * @param variables que acompañan al monomio.
     * @param exponentes al que se eleva cada una de las variables.
     */
    public Monomio(double coeficiente,
                   String variables,
                   String exponentes) {
        //Verificar que en cada cadena vengan sólo letras o números
        //Verificar exponentes sean enteros y mayores que cero
        //si algo falla en este constructor, revisar el  parse porque estoy guardando integers en un arreglo de int's
        //Hacer que los monomios se simplifiquen conforme se vayan agregando, ie. si entra xx simplificar  por x^2

        this.coeficiente = coeficiente;
        char[] cadaVariable = variables.toCharArray();
        String[] cadaExponente = exponentes.split(",");
        this.misTerminos = new ListaDoblementeLigada<Termino>();

        //Se verifica que exista la misma cantidad de exponentes que
        //de variables
        if(cadaVariable.length != cadaExponente.length) {
            throw new IllegalArgumentException("Cantidad de variables  y exponentes incorrecta");
        }

        //Se construye a cada término que van a formar a este
        //monomio, luego se agrega a su lista de términos.
        for(int i = 0; i < cadaVariable.length; i++) {
            Termino nuevo = new Termino(cadaVariable[i],
                                        Integer.parseInt(cadaExponente[i]));
            this.misTerminos.add(nuevo);
        }
    }

    /* Constructor que recibe un coeficiente y una lista de términos
     * que lo van a conformar. */
    private Monomio(Double coeficiente, ListaDoblementeLigada<Termino> misTerminos) {
        this.coeficiente = coeficiente;
        this.misTerminos = misTerminos;
    }

    /* Crea a un monomio vacío. */
    private Monomio() {
        this.misTerminos = new ListaDoblementeLigada<Termino>();
    }

    /**
     * Nos dice si dentro del monomio existe algún término con la
     * misma variable que el parámetro.
     * @param t el termino del que se va a buscar la variable.
     * @return true si este monomio contiene la misma variable que el
     * parámetro.
     */
    public boolean contieneVariable(Termino t) {
        Iterator<Termino> it = this.misTerminos.iterator();

        while(it.hasNext()) {
            if(it.next().mismaVariable(t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Devuelve una copia de este monomio, modificar a su copia no
     * afecta al monomio original.
     * @return monomio una  copia del monomio que manda a llamar al método.
     */
    public Monomio copia() {
        double  c = this.getCoeficiente();
        ListaDoblementeLigada<Termino> copia = new ListaDoblementeLigada<Termino>();

        for(Termino t : this.getMisTerminos()) {
            copia.add(t.copia());
        }

        return new Monomio(c, copia);
    }

    //Partimos de la suposición de que un monomio siempre está
    //simplificado ie, no existen xx, sino x^2
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

    /**
     * Devuelve el coeficiente de este monomio.
     * @return el coeficiente de este monomio.
     */
    public double getCoeficiente() {
        return this.coeficiente;
    }

    /**
     * Devuelve los términos que conforman a este monomio.
     * @return términos que conforman a este monomio.
     */
    public ListaDoblementeLigada<Termino> getMisTerminos() {
        return this.misTerminos;
    }

    /**
     * Asigna un nuevo coeficiente a este monomio.
     * @param coeficiente el nuevo coeficiente para este monomio.
     */
    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }

    /**
     * Decimos que un monomio es un Monomio Cero si su coeficiente es
     * cero.
     * @return true si su coeficiente es cero, false en otro caso.
     */
    public boolean soyMonomioCero() {
        return this.coeficiente == 0;
    }

    /**
     * Método que nos dice si es posible sumar los coeficientes de dos monomios.
     * Dos monomios son sumables si están conformados por los mismos términos.
     * @param o monomio con quien se desea saber si this es sumable.
     * @return true si los monomios contienen los mismos términos.
     */
    public boolean sumable(Monomio o) {

        //Verificación misma cantidad de variables
        if(this.getMisTerminos().size() != o.getMisTerminos().size()) {
            return false;
        }

        //Verificamos que cada uno de los términos que conforman a
        //este monomio estén contenidos en los términos del monomio o.
        Iterator<Termino> checador = this.getMisTerminos().iterator();
        while(checador.hasNext()) {
            if(!o.getMisTerminos().contains(checador.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * La suma de los coeficientes de los monomios es asignado al
     * monomio que manda a llamar al método. Este método sólo debe
     * ser usado si el monomio this y el parámetro son sumables.
     * @param sumando con el cual se desea sumar su coeficiente.
     */
    /* Además este método sólo debe ser usado después de verificar que
     * sumable devuelva true. Dado que es algo tardado verificar que
     * dos monomios sean sumables, dentro de este método no se manda a
     * llamar a _sumable_ */
    public void suma(Monomio sumando) {
        if(sumando == null) {
            throw new IllegalArgumentException();
        }

        this.setCoeficiente(this.getCoeficiente() + sumando.getCoeficiente());
    }

    /* Se copian los términos de este monomio en una nueva lista, para
     * poder ser usados al crear un nuevo monomio. */
    private ListaDoblementeLigada<Termino> copiaTerminos() {
        ListaDoblementeLigada<Termino> copia = new ListaDoblementeLigada<Termino>();

        Iterator<Termino> copiador = this.getMisTerminos().iterator();
        while(copiador.hasNext()) {
            copia.add(copiador.next().copia());
        }

        return copia;
    }

    /**
     * Devuelve un monomio nuevo el cual es el resultado la
     * multiplicación del monomio this y el parámetro. La
     * multiplicación de monomios corresponde con la multiplicación
     * algebraica.
     * @param factor con quien se  va a multiplicar.
     * @return un nuevo monomio que resulta de la multiplicación de this con
     * el parámetro.
     */
    public Monomio multiplica(Monomio factor) {
        Monomio resultado = new Monomio();
        resultado.setCoeficiente(this.getCoeficiente() * factor.getCoeficiente());

        //Se agregan los terminos de this, para  después modificarlos
        resultado.getMisTerminos().addAll(this.copiaTerminos());

        //Calculando los términos que tendrá _resultado_
        for(Termino t : factor.getMisTerminos()) {
            Termino aux = t;
            //Si en el monomio ya existe un termino con la misma
            //variable
            if(resultado.contieneVariable(aux)) {
                //Buscamos en resultado al termino que contiene la misma variable
                //que aux
                Termino  e = resultado.variableContenidaEn(aux);
                e.setGrado(e.sumaGrado(aux));
                continue;
            }

            resultado.getMisTerminos().add(t);
        }
        return resultado;
    }


    /**
     * Devuelve el termino del monomio que manda a llamar al método
     * contiene la misma variable que el termino pasado como
     * parámetro.
     * @param t el término del que se va a buscar la varible.
     * @return el término del monomio que contiene la misma variable
     * que el parámetro.
     */
    /* Este método debe de usarse sólo después de usar contiene
     * variable para evitar NullPointerException. */
    public Termino variableContenidaEn(Termino t) {

        for(Termino e : this.getMisTerminos()) {
            Termino aux = e;
            if(aux.mismaVariable(t)) return aux;
        }
        return null;
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
