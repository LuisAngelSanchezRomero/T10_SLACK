/**
 * Clase principal que contiene la lógica de negocio para la gestión y validación de productos.
 */
public class Producto {

    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(String nombre, double precio, int stock) {
        validarPrecio(precio);
        validarStock(stock);
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Valida que el precio sea mayor a cero.
     */
    public boolean validarPrecio(double precio) {
        if (precio <= 0.0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        return true;
    }

    /**
     * Valida que el stock no sea un número negativo.
     */
    public boolean validarStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return true;
    }

    /**
     * Calcula el precio final con un descuento porcentual aplicado (0% - 100%).
     */
    public double calcularPrecioFinal(double precioBase, double descuento) {
        validarPrecio(precioBase);
        if (descuento < 0.0 || descuento > 100.0) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100");
        }
        double montoDescuento = precioBase * (descuento / 100.0);
        double total = precioBase - montoDescuento;
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Calcula el precio con IGV (18% en Perú).
     */
    public double calcularPrecioConIgv(double precioBase) {
        validarPrecio(precioBase);
        double totalConIgv = precioBase * 1.18;
        return Math.round(totalConIgv * 100.0) / 100.0;
    }

    /**
     * Determina si el pedido califica para envío gratuito (monto >= 100 o cantidad >= 5).
     */
    public boolean esEnvioGratis(double montoTotal, int cantidad) {
        if (montoTotal < 0.0 || cantidad <= 0) {
            throw new IllegalArgumentException("Monto y cantidad deben ser valores positivos");
        }
        return montoTotal >= 100.0 || cantidad >= 5;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        validarStock(stock);
        this.stock = stock;
    }
}
