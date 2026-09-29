import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias para la clase Producto")
public class ProductoTest {

    private Producto productoService;

    @BeforeEach
    void setUp() {
        productoService = new Producto();
    }

    @Test
    @DisplayName("CP01: Validar precio correcto mayor a cero")
    void testValidarPrecioCorrecto() {
        boolean esValido = productoService.validarPrecio(25.50);
        assertTrue(esValido, "El precio debería ser válido");
    }

    @Test
    @DisplayName("CP02: Validar stock correcto mayor o igual a cero")
    void testValidarStockCorrecto() {
        boolean esValido = productoService.validarStock(10);
        assertTrue(esValido, "El stock positivo debería ser válido");
    }

    @Test
    @DisplayName("CP03: Calcular precio final con descuento aplicado")
    void testCalcularPrecioFinalConDescuento() {
        double resultado = productoService.calcularPrecioFinal(100.0, 10.0);
        assertEquals(90.0, resultado, 0.01, "El precio final con 10% de descuento debe ser 90.0");
    }

    @Test
    @DisplayName("CP04: Calcular precio con IGV del 18%")
    void testCalcularPrecioConIgv() {
        double totalConIgv = productoService.calcularPrecioConIgv(100.0);
        assertEquals(118.0, totalConIgv, 0.01, "El precio con 18% de IGV debe ser 118.0");
    }

    @Test
    @DisplayName("CP05: Calificar a envío gratis si el monto es >= 100")
    void testEnvioGratisPorMonto() {
        boolean aplica = productoService.esEnvioGratis(150.0, 2);
        assertTrue(aplica, "Debe calificar a envío gratis por superar los 100 soles");
    }
}
