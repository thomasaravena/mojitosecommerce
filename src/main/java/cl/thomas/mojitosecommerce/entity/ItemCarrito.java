package cl.thomas.mojitosecommerce.entity;



/**
 * Clase de apoyo que representa un elemento individual dentro del carrito de compras.
 * No es una entidad persistente en la base de datos; su propósito es encapsular 
 * la relación entre un {@link Plan} y la cantidad seleccionada durante la sesión del usuario.
 * * @author Sergio Carocca
 * @version 1.0
 */
public class ItemCarrito {

    /** El plan de servicio seleccionado por el usuario. */
    private Producto producto;

    /** La cantidad de veces que se ha agregado este plan al carrito. */
    private int cantidad;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public ItemCarrito() {
        super();
    }

    /**
     * Constructor con parámetros para inicializar un ítem del carrito.
     * * @param plan El plan de servicio asociado.
     * @param cantidad Cantidad inicial del servicio.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        super();
        this.producto = producto;
        this.cantidad = cantidad;
    }

   

    public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getSubtotal() {
        return this.producto.getPrecio() * this.cantidad;
    }
    
}