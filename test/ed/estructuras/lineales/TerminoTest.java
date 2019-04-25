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

public class TerminoTest extends Calificador {

    public static final String nombre = "Termino";

    @Test
    public void initTest() {
        startTest(nombre, "init", "Prueba que el término sea creado exitosamente.", 1.0);
        Termino nuevo = new Termino('x',2);
        addUp(1.0);
        passed();
    }

    @Test
    public void toStringTest() {
        startTest(nombre, "Prueba que funcione método toString", 1.0);
        Termino nuevo = new Termino('x',2);
        assertTrue(nuevo.toString().equals("(x^2)"));
        addUp(1.0);
        passed();
    }

    @Test
    public void equalsTest() {
        startTest(nombre,"Prueba que funcione método equals", 1.0);
        Termino nuevo = new Termino('x',2);
        Termino igual = new Termino('x',2);
        assertTrue(nuevo.equals(igual));
        addUp(1.0);
        passed();
    }
}
