package com.ejemplo.carrito.controlador;

import com.ejemplo.carrito.modelo.Producto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControladorProductoIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/productos");
    }

    @Test
    public void testCreateProducto() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = fmt.parse("2018-05-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Producto p = new Producto();

        p.setNombre("Yogurt de Mora");
        p.setPrecio(350);
        p.setCategoria("Nacional");
        p.setVencimiento(fecha);

        HttpEntity<Producto> request = new HttpEntity<>(p);
        Producto foo = template.postForObject(base.toString(), request, Producto.class);
        Assert.assertNotNull(foo);
        Assert.assertEquals(p.getNombre(), foo.getNombre());
    }
}