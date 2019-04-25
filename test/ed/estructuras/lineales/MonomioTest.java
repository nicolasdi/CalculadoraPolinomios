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
 *
 * @author mindahrelfen
 */
public class MonomioTest extends Calificador{ // Calificador hace casi todo el trabajo.

    public static final String nombre = "Monomio";

    @Test //Tag estandar para denotar que el siguiente metodo es una prueba
    public void initTest(){ //Prueba basica que revisa si se crea el monomio
        Monomio m;
        startTest(nombre, "init", "Prueba que el monomio sea creado exitosamente.",1.0); // start es parte de Calificador recibe quien lo invoca, el nombre de la prueba, una descripcion y el peso en la ponderacion de la prueba.
        m = new Monomio(1.0,"x","2"); // Se crea un monomio x^2
        addUp(1.0); // Se aumenta la calificacion, el valor pasado debe encajar con el valor pasado en starTest pero el programa no lo revisa.
        passed(); // ademas se debe avisar que se paso correctamente la prueba
    }


    //Nota: Mis monomios sí pueden tener coeficiente, cero, esto es porque
    //al final de cada operación, siempre son eliminados del polinomio.

    //toString
    @Test
    public void toStringTest() {
        startTest(nombre, "Prueba que funcione método toString", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        String cadena = nuevo.toString();
        assertTrue(cadena.equals("5.0(x^2)(y^2)"));
        addUp(1.0);
        passed();
    }

    //equals
    @Test
    public void equalsTest() {
        startTest(nombre,"Prueba que funcione método equals", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        Monomio igual = new Monomio(5,"xy", "2,2");
        assertTrue(nuevo.equals(igual));
        addUp(1.0);
        passed();
    }

    //sumable
    @Test
    public void sumableTest() {
        startTest(nombre,"Prueba que puedan sumarse los ecoeficientes de dos monomios", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        Monomio igual = new Monomio(10,"xy", "2,2");
        assertTrue(nuevo.sumable(igual));
        addUp(1.0);
        passed();
    }

    // NO sumable
    @Test
    public void noSumableTest() {
        startTest(nombre,"Prueba que NO puedan sumarse los ecoeficientes de dos monomios", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        Monomio igual = new Monomio(10,"xy", "3,2");
        assertFalse(nuevo.sumable(igual));
        addUp(1.0);
        passed();
    }

    //suma monomios
    @Test
    public void sumaMonomiosTest() {
        startTest(nombre,"Prueba la suma de dos monomios", 1.0);
        Monomio sumando1 = new Monomio(5,"xy", "2,2");
        Monomio sumando2 = new Monomio(10,"xy", "2,2");
        Monomio resultado = new Monomio(15,"xy", "2,2");
        sumando1.suma(sumando2);

        assertTrue(sumando1.equals(resultado));
        addUp(1.0);
        passed();
    }

    //soy monomio cero
    @Test
    public void soyMonomioCeroTest() {

        startTest(nombre,"Prueba que un monomio sea el monomio cero", 1.0);

        Monomio nuevo = new Monomio(0,"xy", "2,2");
        assertTrue(nuevo.soyMonomioCero());
        addUp(1.0);
        passed();

    }

    //multiplica monomio
    @Test
    public void multiplicaMonomioTest() {
        startTest(nombre,"Prueba la multiplicacion entre dos monomios", 1.0);

        Monomio factor1 = new Monomio(5,"xy", "2,2");
        Monomio factor2 = new Monomio(20,"xz", "2,3");
        Monomio resultado = new Monomio(100,"xyz", "4,2,3");

        assertTrue(factor1.multiplica(factor2).equals(resultado));
        addUp(1.0);
        passed();
    }

    //Contiene Variable
    @Test
    public void contieneVariableTest() {
        startTest(nombre,"Prueba que un monomio contenga un termino", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        Termino contenido = new Termino('x',2);
        assertTrue(nuevo.contieneVariable(contenido));
        addUp(1.0);
        passed();
    }

    //Copia
    @Test
    public void copiaTest() {
        startTest(nombre,"Prueba que se copie correctamente un monomio", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        Monomio copia = new Monomio(5,"xy", "2,2");
        assertTrue(nuevo.copia().equals(copia));
        addUp(1.0);
        passed();
    }

    //getCoeficiente
    @Test
    public void getCoeficienteTest() {
        startTest(nombre,"Prueba que se devuelva el coeficiente correcto del monomio", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        assertTrue(nuevo.getCoeficiente() == 5);
        addUp(1.0);
        passed();
    }

    //setCoeficiente
    @Test
    public void setCoeficiente() {
        startTest(nombre,"Prueba que se cambie correctamente el  coeficiente del monomio", 1.0);
        Monomio nuevo = new Monomio(5,"xy", "2,2");
        nuevo.setCoeficiente(8);
        assertTrue(nuevo.getCoeficiente() == 8);
        addUp(1.0);
        passed();

    }
}
