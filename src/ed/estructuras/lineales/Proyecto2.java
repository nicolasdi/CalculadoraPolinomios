package ed.estructuras.lineales;

import java.util.Arrays;

public class Proyecto2 {

    public static void main(String[] args) {
	    //Ya se pueden crear monomios
	    Monomio prueba = new Monomio(5,"xyz", "2,2,3");
	    //Ya sirve su método to String
	    System.out.println(prueba.toString());


	    double[] coeficientesC = {2,3,4};
	    String[] variablesC = {"x", "y", "z"};
	    String[] exponentesC = {"2","2","3"};
	    //Ya se pueden crear polinomios
	    Polinomio probando = new Polinomio(coeficientesC, variablesC, exponentesC);
	    //Ya sirve su método  toString
	    System.out.println(probando.toString());

	    //Tributo a Sensei
	    System.out.println("¡Si! ¡Ya se pudo! ¡Te amo, Sensei!");
    }
}
