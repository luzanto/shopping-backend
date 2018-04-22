package com.ejemplo.carrito.controlador;

import com.ejemplo.carrito.excepcion.ResourceNotFoundException;
import com.ejemplo.carrito.modelo.Producto;
import com.ejemplo.carrito.repositorio.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ControladorProducto {

    @Autowired
    RepositorioProducto repositorioProducto;

    // Obtener todos los productos
    @GetMapping("/productos")
    public List<Producto> getAllProducts() {
        return repositorioProducto.findAll();
    }

    // Obtener producto por id
    @GetMapping("/productos/{id}")
    public Producto getProductById(@PathVariable(value = "id") Long productId) {
        return repositorioProducto.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productId));
    }

    // Crear producto
    @PostMapping("/productos")
    public Producto createProduct(@Valid @RequestBody Producto producto) {
        return repositorioProducto.save(producto);
    }

    // Actualizar producto
    @PutMapping("/productos/{id}")
    public Producto updateProduct(@PathVariable(value = "id") Long productId,
                           @Valid @RequestBody Producto productDetails) {

        Producto producto = repositorioProducto.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productId));

        producto.setNombre(productDetails.getNombre());
        producto.setVencimiento(productDetails.getVencimiento());
        producto.setCategoria(productDetails.getCategoria());
        producto.setPrecio(productDetails.getPrecio());

        return repositorioProducto.save(producto);
    }

    // Borrar producto
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
        Producto producto = repositorioProducto.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", productId));

        repositorioProducto.delete(producto);

        return ResponseEntity.ok().build();
    }

    public static int dsctoEfectivo (Producto producto, Boolean efectivo) {
        // Se obtiene precio original del producto deseado
        double dsct = 0.19;
        int precioOriginal = producto.getPrecio();
        Double precioFinal = precioOriginal-precioOriginal*dsct;
        if (efectivo) {
            return precioFinal.intValue();
        }
        else {
            return precioOriginal;
        }
    }

}
