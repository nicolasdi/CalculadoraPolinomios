package ed.estructuras.lineales;

import java.util.Arrays;

public class Proyecto2 {

    public static void main(String[] args) {
	    //////////////////////////////////////////TERMINOS

	    //Ya se pueden crear términos
	    System.out.println("Se están creando  Terminos...");
	    Termino nuevo = new Termino('x',2);
	    Termino otroNuevo = new Termino('y',3);
	    //Ya Funciona su método toString
	    System.out.println("Primer término creado: ");
	    System.out.println(nuevo);
	    System.out.println("Segundo término crreado: ");
	    System.out.println(otroNuevo);

	    //Ya funciona su método equals
	    //System.out.println(nuevo.equals(otroNuevo));

	    ///////////////////////////////////////////MONOMIOS
	    //Ya se pueden crear monomios
	    Monomio prueba = new Monomio(5,"xyz", "2,2,3");
	    Monomio prueba2 = new Monomio(1,"x", "1");
	    Monomio prueba3 = new Monomio(1,"x", "1");
	    Termino pruebi = new Termino('x',3);

	    System.out.println("Se están creando monomios...");
	    System.out.println("Primer monomio creado: ");
	    //Ya sirve su método to String
	    System.out.println(prueba);
	    System.out.println("Segundo monomio creado: ");
	    System.out.println(prueba2);
	    //Ya sirve su método equals
	    //System.out.println(prueba.equals(prueba2));

	    //Ya sirve su método sumable
	    //System.out.println(prueba2.sumable(prueba3));

	    //Ya sirve su método suma monomios
	    //System.out.println(prueba.suma(prueba2));

	    //Ya sirve su método soyMonomioCero
	    //System.out.println(prueba3.soyMonomioCero());

	    //Ya sirve su método multiplica
	    //System.out.println(prueba2.multiplica(prueba3));


	    /////////////////////////////////////////////POLINOMIOS

	    double[] coeficientesC = {1,1};
	    String[] variablesC = {"x","y"};
	    String[] exponentesC = {"1","1"};
	    //Ya se pueden crear polinomios
	    Polinomio probando = new Polinomio(coeficientesC, variablesC, exponentesC);

	    double[] coeficientesD = {1,-1,1};
	    String[] variablesD = {"x","xy","y"};
	    String[] exponentesD = {"2","1,1","2"};
	    //Ya se pueden crear polinomios
	    Polinomio probando2 = new Polinomio(coeficientesD, variablesD, exponentesD);

	    System.out.println("Se están creando polinomios...");
	    System.out.println("Se creó al polinomio:");
	    System.out.println(probando);
	    //Ya sirve su método soyPolinomioCero
	    //System.out.println(probando2.soyPolinomioCero());

	    //Ya sirve su método  toString
	    //System.out.println(probando.toString());

	    //Ya sirve su método suma
	    //System.out.println(probando.suma(probando2));

	    //Ya sirve su método suma
	    // System.out.println(probando.suma(probando2));
	    System.out.println();
	    System.out.println();

	    System.out.println("----- Calculadora de Polinomios -----");
	    ///////////////////////////////////////////
	    //(x+y)*(x+y) = x² + 2xy + y²
	    double[] a1 = {1,1};
	    String[] a2 = {"x","y"};
	    String[] a3 = {"1","1"};
	    Polinomio a = new Polinomio(a1, a2, a3);

	    System.out.println("(" + a +")"
	                       +" * "
	                       +"("+ a +")"
	                       + " = " + a.multiplica(a));
	    System.out.println();
	    System.out.println();
	    //////////////////////////////////////////
	    //( x + y ) * (x² - xy + y² ) = (x³ + y³)
	    double[] c1 = {1,1};
	    String[] c2 = {"x","y"};
	    String[] c3 = {"1","1"};
	    Polinomio c = new Polinomio(c1, c2, c3);

	    double[] d1 = {1,-1,1};
	    String[] d2 = {"x","xy","y"};
	    String[] d3 = {"2","1,1","2"};
	    Polinomio d = new Polinomio(d1, d2, d3);
	    System.out.println();

	    System.out.println("(" + c +")"
	                       +" * "
	                       +"("+ d +")"
	                       + " = " + c.multiplica(d));
	    System.out.println();

	    //////////////////////////////////////////
	    //( x + y ) * (x - y ) = (x² - y²)
	    double[] e1 = {1,1};
	    String[] e2 = {"x","y"};
	    String[] e3 = {"1","1"};
	    Polinomio e = new Polinomio(e1, e2, e3);

	    double[] f1 = {1,-1};
	    String[] f2 = {"x","y"};
	    String[] f3 = {"1","1"};
	    Polinomio f = new Polinomio(f1, f2, f3);
	    System.out.println();

	    System.out.println("(" + e +")"
	                       +" * "
	                       +"("+ f +")"
	                       + " = " + e.multiplica(f));
	    System.out.println();
	    System.out.println();

	    //////////////////////////////////////////
	    //( x + y ) * (x + y) * (x + y) = (x + y)³ = x³ + 3x²y + 3xy² + y³
	    double[] g1 = {1,1};
	    String[] g2 = {"x","y"};
	    String[] g3 = {"1","1"};
	    Polinomio g = new Polinomio(g1, g2, g3);
	    System.out.println();

	    System.out.println("(" + g +")"
	                       +" * "
	                       +"("+ g +")"
	                       +" * "
	                       +"("+ g + ")"
	                       + " = " + g.multiplica(g).multiplica(g));
	    System.out.println();
	    System.out.println();

	    //////////////////////////////////////////
	    //( x + y ) * (x + y)  + (x + y) * (x - y) = (x² + 2xy + y²) + (x² - y²) = 2x² + 2xy

	    System.out.println("(" + a +")"
	                       +" * "
	                       +"("+ a +")"
	                       +" + "
	                       +"(" + e +")"
	                       +" * "
	                       +"("+ f +")"
	                       + " = "+ a.multiplica(a).suma(e.multiplica(f)));

	    System.out.println();
	    System.out.println();

	    //Tributo a Sensei
	    System.out.println("¡Si! ¡Ya se pudo!");
    }
}
