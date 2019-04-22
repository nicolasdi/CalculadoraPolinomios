package ed.estructuras.lineales;

import java.util.Arrays;

public class Proyecto2 {

    public static void main(String[] args) {
	    //////////////////////////////////////////TERMINOS
	    //Ya se pueden crear términos
	    Termino nuevo = new Termino('x',2);
	    Termino otroNuevo = new Termino('x',2);
	    //Ya Funciona su método toString
	    //System.out.println(nuevo);

	    //Ya funciona su método equals
	    //System.out.println(nuevo.equals(otroNuevo));

	    ///////////////////////////////////////////MONOMIOS
	    //Ya se pueden crear monomios
	    Monomio prueba = new Monomio(5,"xyz", "2,2,3");
	    Monomio prueba2 = new Monomio(5,"zyw", "3,2,3");
	    Monomio prueba3 = new Monomio(0,"zyx", "3,2,2");
	    Termino pruebi = new Termino('z',3);
	    //Ya sirve su método to String
	    //System.out.println(prueba.toString());

	    //Ya sirve su método equals
	    //System.out.println(prueba.equals(prueba2));

	    //Ya sirve su método sumable
	    //System.out.println(prueba.sumable(prueba2));

	    //Ya sirve su método suma monomios
	    //System.out.println(prueba.suma(prueba2));

	    //Ya sirve su método soyMonomioCero
	    //System.out.println(prueba3.soyMonomioCero());

	    ListaDoblementeLigada<Termino> x = prueba.getMisTerminos();
	    ListaDoblementeLigada<Termino> y = prueba2.getMisTerminos();
	    //System.out.println("aqui");


	    //Lo siguiente funciona: es verdad que el  método contains funciona para los monomios ie. se puede detectar si un monomio contiene un término
	    //System.out.println(x.contains(pruebi));

	    //Lo siguiente también funciona, es verdad que el retainAll solo guarda las variables que tienen en común
	    //x.retainAll(y);
	    //System.out.println(x);

	    //prueba.multiplica(prueba2);
	    //System.out.println(prueba.multiplica(prueba2));
	    /////////////////////////////////////////////////POLINOMIOS

	    double[] coeficientesC = {1,2,3};
	    String[] variablesC = {"xy", "y", "z"};
	    String[] exponentesC = {"2,2","3","3"};
	    //Ya se pueden crear polinomios
	    Polinomio probando = new Polinomio(coeficientesC, variablesC, exponentesC);

	    double[] coeficientesD = {-1,0};
	    String[] variablesD = {"xy","y"};
	    String[] exponentesD = {"2,2","3"};
	    //Ya se pueden crear polinomios
	    Polinomio probando2 = new Polinomio(coeficientesD, variablesD, exponentesD);

	    //Ya sirve su método soyPolinomioCero
	    //System.out.println(probando2.soyPolinomioCero());

	    //Ya sirve su método  toString
	    //System.out.println(probando.toString());

	    //System.out.println(probando.suma(probando2));
	    //probando.suma(probando2);

	    //Tributo a Sensei
	    System.out.println("¡Si! ¡Ya se pudo! ¡Te amo, Sensei!");
    }
}
