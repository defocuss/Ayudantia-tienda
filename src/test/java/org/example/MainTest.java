package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {

    Object[][] productos = new Object[10][3];

    @BeforeEach
    void setUp() {
        Main.agregarProducto(productos, "papas", 1, 12);
        Main.agregarProducto(productos, "ramitas", 2, 15);
        Main.agregarProducto(productos, "mani", 3, 0);
        Main.agregarProducto(productos, "gomitas", 4, 10);
        Main.agregarProducto(productos, "carne", 5, 1);
        Main.agregarProducto(productos, "fanta", 6, 99);
        Main.agregarProducto(productos, "sprite", 7, 13);
        Main.agregarProducto(productos, "pepsi", 8, 16);
        // aqui solamente se colocan valores enteros positivos en la cantidad, ya que
        // la validacion para esto se hace en las funciones para ingresar cantidad, no en las de
        // agregar producto
    }

    @Test
    void agregarCantidadProducto() {
        assertEquals(16, Main.agregarCantidadProducto(productos, "papas", 1, 4, 0));
        assertEquals(18, Main.agregarCantidadProducto(productos, "ramitas", 2, 3, 1));
        assertEquals(-3, Main.agregarCantidadProducto(productos, "mani", 3, -3, 2));
        assertEquals(17, Main.agregarCantidadProducto(productos, "gomitas", 4, 7, 3));
    }

    @Test
    void agregarProductoNuevo() {
        Main.agregarProductoNuevo(productos, "helado", 9, 2, 8);
        Main.agregarProductoNuevo(productos, "queso", 10, 23, 9);
    }

    @Test
    void restarCantidadProducto() {

    }

    @Test
    void validarCantidadRestar() {
        assertTrue(Main.validarCantidadRestar(productos, "papas", 1, 3, 0));
        assertTrue(Main.validarCantidadRestar(productos, "ramitas", 2, 15, 1));
        assertFalse(Main.validarCantidadRestar(productos, "gomitas", 4, 18, 3));
        assertFalse(Main.validarCantidadRestar(productos, "carne", 5, 2, 4));
    }

    @Test
    void calcularDisponibilidad() {
        assertEquals(12,Main.calcularDisponibilidad(productos,1));
        assertEquals(15,Main.calcularDisponibilidad(productos,2));
        assertEquals(0,Main.calcularDisponibilidad(productos,3));
        assertEquals(10,Main.calcularDisponibilidad(productos,4));
        assertEquals(16,Main.calcularDisponibilidad(productos,8));
    }
}