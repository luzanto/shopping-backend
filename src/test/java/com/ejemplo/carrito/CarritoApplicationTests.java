package com.ejemplo.carrito;

import com.ejemplo.carrito.controlador.ControladorProducto;
import com.ejemplo.carrito.modelo.Producto;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(JUnit4.class)
public class CarritoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void defaultTest() {
		// Se genera un producto p arbitrario
		Producto p = new Producto();
		p.setNombre("Teclado");
		p.setPrecio(10000);
		p.setCategoria("Importado");
		p.setVencimiento(null);
		// Se llama a método dsctoEfectivo con flag efectivo = true
		int precioDscto = ControladorProducto.dsctoEfectivo(p, true);
		// Se calcula de manera aparte el posible descuento
		int precioOriginal = p.getPrecio();
		Double precioFinal = precioOriginal - precioOriginal*0.19;
		int precioEsperado = precioFinal.intValue();
		System.out.println("Precio Original: "+ precioOriginal + "\nPrecio Descuento: " + precioDscto);
		// Se compara resultado esperado con el obtenido por el método llamado
		Assert.assertEquals(precioEsperado, precioDscto);
	}
}