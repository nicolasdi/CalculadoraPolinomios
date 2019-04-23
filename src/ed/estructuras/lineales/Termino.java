package ed.estructuras.lineales;

/**
 * Un término está formado por una variable y su grado.
 */
public class Termino {
    /** Variable del término. */
    char variable;
    /** Grado del término. */
    int grado;

    /** Construye a un término
     * @param variable la variable de este término.
     * @param grado el grado de la variable.
     */
    public Termino(char variable, int grado) {
        this.variable = variable;
        this.grado = grado;
    }

    /**
     * Entrega un termino igual e independiente del término que manda
     * a llamar al método.
     * @return termino una copia de este término.
     */
    public Termino copia() {
        return new Termino(this.getVariable(), this.getGrado());
    }

    /**
     * Decimos que dos términos son iguales si tienen la misma
     * variable y tienen el mismo grado.
     * @param o objeto con el cual se quiere verificar la igualdad.
     */
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

    /**
     * Devuelve el grado de este término.
     * @return grado el grado de este término.
     */
    public int getGrado() {
        return this.grado;
    }

    /**
     * Devuelve la variable de este término.
     * @return la variable de este término.
     */
    public char getVariable() {
        return this.variable;
    }

    /**
     * Revisa si los dos términos tienen la misma variable.
     * @param t termino del cual se va a verificar que this tenga la
     * misma variable.
     * @return true si tienen la misma variable, false en otro caso.
     */
    public boolean mismaVariable(Termino t) {
        return Character.toString(this.getVariable()).equals(Character.toString(t.getVariable()));
    }

    /**
     * Devuelve un término el cual resulta de la multiplicación de dos
     * términos. Esta multiplicación corresponde con la definición
     * algebraica de multiplicación de términos.
     * @param t el termino con el cual se va a multiplicar.
     * @return termino resultante de la multiplicación del término que
     * manda a llamar al método con el parámetro.
     */
    public Termino multiplica(Termino t) {
        return new Termino(this.getVariable(), this.sumaGrado(t));
    }

    /**
     * Asigna un nuevo grado a este término.
     * @param grado a ser asignado a este término.
     */
    public void setGrado(int grado) {
        this.grado = grado;
    }

    /**
     * Suma los grados de las variables.
     * @param t variable con la que se sumará el grado.
     * @return grado la suma de los grados de los términos.
     */
    //Método auxiliar para cuando se multiplican términos.
    public int sumaGrado(Termino t) {
        return this.getGrado() + t.getGrado();
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
