package ies.joatzel.erosketa.exception.Product;

public abstract class ProductException extends RuntimeException{
    public ProductException(String mensaje) {
        super(mensaje);
    }
}
