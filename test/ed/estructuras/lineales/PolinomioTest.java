package ed.estructuras.lineales;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.*;

/**
 * Clase para pruebas unitarias de la clase {@link Polinomio}.
 */
public class PolinomioTest extends Calificador {
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    public static final String nombre = "Polinomio";
    @Test
    public void initTest(){
        startTest(nombre, "init", "Prueba que el polinomio sea creado exitosamente.",1.0);
        double[] coeficientesC = {1,1};
        String[] variablesC = {"x","y"};
        String[] exponentesC = {"1","1"};
        //Ya se pueden crear polinomios
        Polinomio probando = new Polinomio(coeficientesC, variablesC, exponentesC);

        addUp(1.0);
        passed();
    }

    //Copia
    @Test
    public void copiaTest() {
        startTest(nombre, "Prueba que el polinomio sea copiado exitosamente",1.0);
        double[] coeficientesC = {1,1};
        String[] variablesC = {"x","y"};
        String[] exponentesC = {"1","1"};

        Polinomio nuevo = new Polinomio(coeficientesC, variablesC, exponentesC);

        double[] coeficientes = {1,1};
        String[] variables = {"x","y"};
        String[] exponentes = {"1","1"};

        Polinomio igual = new Polinomio(coeficientes, variables, exponentes);

        Polinomio aux = nuevo.copia();
        assertTrue(aux.equals(igual));

        addUp(1.0);
        passed();

    }

    //equals
    @Test
    public void equalsTest() {
        startTest(nombre, "Prueba método equals.",1.0);
        double[] coeficientesC = {1,1};
        String[] variablesC = {"x","y"};
        String[] exponentesC = {"1","1"};

        Polinomio nuevo = new Polinomio(coeficientesC, variablesC, exponentesC);

        double[] coeficientes = {1,1};
        String[] variables = {"x","y"};
        String[] exponentes = {"1","1"};

        Polinomio igual = new Polinomio(coeficientes, variables, exponentes);
        assertTrue(nuevo.equals(igual));
        addUp(1.0);
        passed();
    }

    //eliminaMonomiosCero
    @Test
    public void eliminaMonomiosCeroTest() {
        startTest(nombre, "Prueba que se eliminen los monomos con coeficiente cero de un polinomio.",1.0);
        double[] coeficientesC = {1,1,0};
        String[] variablesC = {"x","y","z"};
        String[] exponentesC = {"1","1","1"};

        Polinomio conCero = new Polinomio(coeficientesC, variablesC, exponentesC);

        double[] coeficientes = {1,1};
        String[] variables = {"x","y"};
        String[] exponentes = {"1","1"};

        Polinomio sinCero = new Polinomio(coeficientes, variables, exponentes);

        conCero.eliminaMonomiosCero();
        assertTrue(conCero.equals(sinCero));
        addUp(1.0);
        passed();
    }

    //Multiplicación de polimonomios
    //(x+y)*(x+y) = x² + 2xy + y²
    @Test
    public void multiplicacionPolinomiosTest() {
        startTest(nombre, "Prueba multiplicación de monomios. Binomio Cuadrado Perfecto",1.0);
        double[] a1 = {1,1};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio a = new Polinomio(a1, a2, a3);
        Polinomio resultado = a.multiplica(a);

        double[] b1 = {1,2,1};
        String[] b2 = {"x","xy","y"};
        String[] b3 = {"2","1,1","2"};
        Polinomio b = new Polinomio(b1, b2, b3);

        assertTrue(resultado.equals(b));
        addUp(1.0);
        passed();
    }

    //Multiplicación de polimonomios suma  de cubos.
    //( x + y ) * (x² - xy + y² ) = (x³ + y³)
    @Test
    public void multiplicacionPolinomios2Test() {
        startTest(nombre, "Prueba multiplicación de monomios. Suma de cubos",1.0);
        double[] a1 = {1,1};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio a = new Polinomio(a1, a2, a3);

        double[] b1 = {1,-1,1};
        String[] b2 = {"x","xy","y"};
        String[] b3 = {"2","1,1","2"};
        Polinomio b = new Polinomio(b1, b2, b3);

        double[] c1 = {1,1};
        String[] c2 = {"x","y"};
        String[] c3 = {"3","3"};
        Polinomio c = new Polinomio(c1, c2, c3);

        Polinomio resultado = a.multiplica(b);

        assertTrue(resultado.equals(c));
        addUp(1.0);
        passed();
    }

    //Suma
    @Test
    //(x + y) + (x + y) = 2x + 2y
    public void sumaPolinomioTest() {
        startTest(nombre, "Prueba suma de polinomios",1.0);

        double[] a1 = {1,1};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio a = new Polinomio(a1, a2, a3);
        Polinomio suma = a.suma(a);

        double[] c1 = {2,2};
        String[] c2 = {"x","y"};
        String[] c3 = {"1","1"};
        Polinomio resultado = new Polinomio(c1, c2, c3);

        assertTrue(suma.equals(resultado));
        addUp(1.0);
        passed();
    }

    //usa suma y multiplicación.
    //(( x + y ) * (x + y))  + (x + y) * (x - y) = (x² + 2xy + y²) + (x² - y²) = 2x² + 2xy
    @Test
    public void sumaMultiplicaTest() {
        startTest(nombre, "Prueba que suma y multiplicación funcionen en conjunto",1.0);

        double[] a1 = {1,1};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio operando1 = new Polinomio(a1, a2, a3);

        double[] c1 = {1,-1};
        String[] c2 = {"x","y"};
        String[] c3 = {"1","1"};
        Polinomio operando2 = new Polinomio(c1, c2, c3);

        Polinomio c = operando1.multiplica(operando1);
        Polinomio d = operando1.multiplica(operando2);
        Polinomio resultado = c.suma(d);

        double[] e1 = {2,2};
        String[] e2 = {"x","xy"};
        String[] e3 = {"2","1,1"};
        Polinomio resulta = new Polinomio(e1, e2, e3);
        assertTrue(resultado.equals(resulta));

        addUp(1.0);
        passed();
    }

    //simplifica
    @Test
    public void simplificaTest() {
        startTest(nombre, "Prueba que método simplifica",1.0);
        double[] a1 = {1,0};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio a = new Polinomio(a1, a2, a3);

        double[] b1 = {1};
        String[] b2 = {"x"};
        String[] b3 = {"1"};
        Polinomio b = new Polinomio(b1, b2, b3);
        a.simplifica();
        assertTrue(a.equals(b));

        addUp(1.0);
        passed();
    }

    //Soy polinomioCero
    @Test
    public void polinoimoCeroTest() {
        startTest(nombre, "Prueba que método simplifica",1.0);
        double[] a1 = {0,0};
        String[] a2 = {"x","y"};
        String[] a3 = {"1","1"};
        Polinomio a = new Polinomio(a1, a2, a3);
        assertTrue(a.soyPolinomioCero());

        addUp(1.0);
        passed();
    }

    //Suma polinomios
    @Test
    public void sumaPolinomio2Test() {
        startTest(nombre, "Prueba que método simplifica",1.0);
        double[] a1 = {2,3};
        String[] a2 = {"xz","y"};
        String[] a3 = {"1,3","1"};
        Polinomio a = new Polinomio(a1, a2, a3);

        double[] b1 = {1};
        String[] b2 = {"y"};
        String[] b3 = {"1"};
        Polinomio b = new Polinomio(b1, b2, b3);

        double[] e1 = {2,4};
        String[] e2 = {"xz","y"};
        String[] e3 = {"1,3","1"};
        Polinomio e = new Polinomio(e1, e2, e3);

        Polinomio resultado = a.suma(b);
        assertTrue(resultado.equals(e));
        addUp(1.0);
        passed();

    }

    //toString
    @Test
    public void toStringTest() {
        startTest(nombre, "Prueba método toString",1.0);
        double[] a1 = {2,3};
        String[] a2 = {"xz","y"};
        String[] a3 = {"1,3","1"};
        Polinomio a = new Polinomio(a1, a2, a3);

        assertTrue(a.toString().equals("2.0(x^1)(z^3) + 3.0(y^1)"));
        addUp(1.0);
        passed();
    }
}
