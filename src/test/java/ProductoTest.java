import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -50.0})
    @DisplayName("CP02: Lanzar excepción si el precio es menor o igual a cero")
    void testValidarPrecioInvalido(double precioInvalido) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.validarPrecio(precioInvalido);
        });
        assertEquals("El precio debe ser mayor a cero", exception.getMessage());
    }

    @Test
    @DisplayName("CP03: Validar stock correcto mayor o igual a cero")
    void testValidarStockCorrecto() {
        boolean esValido = productoService.validarStock(10);
        assertTrue(esValido, "El stock positivo debería ser válido");
    }

    @Test
    @DisplayName("CP04: Lanzar excepción si el stock es negativo")
    void testValidarStockNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.validarStock(-5);
        });
        assertEquals("El stock no puede ser negativo", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "100.0, 10.0, 90.0",
            "50.0, 20.0, 40.0",
            "80.0, 0.0, 80.0",
            "200.0, 50.0, 100.0"
    })
    @DisplayName("CP05: Calcular precio final con descuento aplicado")
    void testCalcularPrecioFinalConDescuento(double precioBase, double descuento, double esperado) {
        double resultado = productoService.calcularPrecioFinal(precioBase, descuento);
        assertEquals(esperado, resultado, 0.01);
    }

    @Test
    @DisplayName("CP06: Lanzar excepción si el descuento es mayor a 100%")
    void testDescuentoMayorACien() {
        assertThrows(IllegalArgumentException.class, () -> {
            productoService.calcularPrecioFinal(100.0, 120.0);
        });
    }

    @Test
    @DisplayName("CP07: Calcular precio con IGV del 18%")
    void testCalcularPrecioConIgv() {
        double totalConIgv = productoService.calcularPrecioConIgv(100.0);
        assertEquals(118.0, totalConIgv, 0.01);
    }

    @Test
    @DisplayName("CP08: Calificar a envío gratis si el monto es >= 100")
    void testEnvioGratisPorMonto() {
        boolean aplica = productoService.esEnvioGratis(150.0, 2);
        assertTrue(aplica, "Debe calificar a envío gratis por superar los 100 soles");
    }

    @Test
    @DisplayName("CP09: Calificar a envío gratis si la cantidad de productos es >= 5")
    void testEnvioGratisPorCantidad() {
        boolean aplica = productoService.esEnvioGratis(40.0, 5);
        assertTrue(aplica, "Debe calificar a envío gratis por llevar 5 o más unidades");
    }

    @Test
    @DisplayName("CP10: No calificar a envío gratis si no cumple monto ni cantidad")
    void testNoAplicaEnvioGratis() {
        boolean aplica = productoService.esEnvioGratis(40.0, 2);
        assertFalse(aplica, "No debe calificar a envío gratis");
    }
}
