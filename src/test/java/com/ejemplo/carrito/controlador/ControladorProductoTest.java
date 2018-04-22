package com.ejemplo.carrito.controlador;

import com.ejemplo.carrito.modelo.Producto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ControladorProductoTest {

    @Autowired
    private ControladorProducto controladorProducto;

    @Test
    public void dsctoEfectivo() {
        // Se genera un producto p arbitrario
        Producto p = new Producto();
        p.setNombre("Teclado");
        p.setPrecio(10000);
        p.setCategoria("Importado");
        p.setVencimiento(null);
        // Se llama a método dsctoEfectivo con flag efectivo = true
        int precioDscto = controladorProducto.dsctoEfectivo(p, true);
        // Se calcula de manera aparte el posible descuento
        int precioOriginal = p.getPrecio();
        Double precioFinal = precioOriginal - precioOriginal*0.19;
        int precioEsperado = precioFinal.intValue();
        System.out.println("Precio Original: "+ precioOriginal + "\nPrecio Descuento: " + precioDscto);
        // Se compara resultado esperado con el obtenido por el método llamado
        Assert.assertEquals(precioEsperado, precioDscto);
    }
}