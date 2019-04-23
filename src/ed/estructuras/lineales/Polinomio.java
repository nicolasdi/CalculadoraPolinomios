package ed.estructuras.lineales;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Clase para representar polinomios.
 */
/*
 * Detalles de la implementación; un polinomio es una lista doblemente
 * ligada que almacena monomios.
 */
public class Polinomio {
    /* Lista donde se almacenan los monomios que conforman a este polinomio */
    private ListaDoblementeLigada<Monomio> misMonomios;

    /**
     * Construye a un Polinomio.
     * @param coeficientes de los monomios del polinomio.
     * @param variables que conforman a cada monomio del polinomio.
     * @param grados de las variables de cada monomio.
     */
    public Polinomio(double[] coeficientes, String[] variables, String[] grados) {

        //Verificamos tener la cantidad correcta de coeficientes, variables y grados
        if(!((coeficientes.length == variables.length)
             && ( variables.length == grados.length))) {
            throw new IllegalArgumentException();
        }
        this.misMonomios = new ListaDoblementeLigada<Monomio>();

        //Se construyen los monomios del polinomio
        for(int i = 0; i < coeficientes.length; i++) {
            //está recibiendo int coeficiente, string variales, string
            //grados(separados por ,)
            Monomio nuevo = new Monomio(coeficientes[i], variables[i], grados[i]);
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
     * @return polinomio una copia de este polinomio.
     */
    public Polinomio copia() {
        return new Polinomio(this.copiaMonomios());
    }

    /*
     * Devuelve una lista con una copia de los monomios del polinomio
     * que manda a llamar al método.
     */
    private ListaDoblementeLigada<Monomio> copiaMonomios() {
        ListaDoblementeLigada<Monomio> copia = new ListaDoblementeLigada<Monomio>();

        Iterator<Monomio> copiador = this.getMisMonomios().iterator();
        //Copiamos monomio por monomio y se agrega a la lista de
        //monomios
        while(copiador.hasNext()) {
            //se agrega a la lista la copia del Monomio
            copia.add(copiador.next().copia());
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
     * @return lista de los monomios que conforman al polinomio.
     */
    public ListaDoblementeLigada<Monomio> getMisMonomios() {
        return this.misMonomios;
    }

    /**
     * Devuelve un polinomio que es resultado de la multiplicación de
     * dos polinomios, this y el parámetro, corresponde con la
     * multiplicación algebraica de polinomios.
     * @param factor el polinomio con quien se va a multiplicar.
     * @return polinomio resultado de la multiplicación de los
     * polinomios.
     */
    public Polinomio multiplica(Polinomio factor) {
        Polinomio resultado = new Polinomio();
        //Polinomio auxiliar utilizado para guardar la multiplicación
        //de cada monomio y poder sumarselo al resultado.
        Polinomio aux = new Polinomio();

        for(Monomio f1 : factor.getMisMonomios()) {
            for(Monomio f2 : this.getMisMonomios()) {
                aux.misMonomios.add(f1.multiplica(f2));
                resultado = resultado.suma(aux);
                aux.misMonomios.clear();
            }
        }

        return resultado;
    }

    /**
     * Simplifica este polinomi; agrupando términos semejantes y
     * eliminando monomios cero.
     */
    //Terminar
    public void simplifica() {
        this.eliminaMonomiosCero();
    }

    /**
     * Nos dice si el polinomio es el polinomio cero ie. su
     * coeficiente es cero para todos los monomios que lo componen.
     * @return true si todos los monomios de este polinomio son el
     * polinomio cero o éste polinomio no tiene monomios, false en
     * otro caso.
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
     * @return polinomio el polinomio resultado de sumar a this y al polinomio
     * que fue pasado como parámetro.
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

        Polinomio resultado = this.copia();

        for(Monomio m : sumando.getMisMonomios()) {
            Monomio aux = m;

            //Se inicializa en null para que no lance el error aux2
            //might have not been initialized.

            //Es necesario que esta no sea una variable local del for,
            //pues se necesita saber su valor al salir de éste.
            Monomio aux2 = null;
            for(Monomio e : resultado.getMisMonomios()) {
                aux2 = e;

                //Si encontró un monomio con quien sumarse, se suma.
                if(aux2.sumable(aux)) {
                    aux2.suma(aux);
                    break;

                }
            }
            //Cuando se ha llegado a este punto, habrá que preguntar
            //por qué se salió del for anterior, si se salió porque el
            //monomio encontró con quien sumarse, el monomio no se
            //agrega al polinomio. Si se salió el for anterior porque
            //no encontró a nadie con quien sumarse entonces se agrega
            //al polinomio.


            //Si no se encontró a alguien con quien sumarse, se agrega
            //a la lista.
            if(!aux2.sumable(aux)) {
                resultado.agregaMonomio(aux);
            }
        }

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
