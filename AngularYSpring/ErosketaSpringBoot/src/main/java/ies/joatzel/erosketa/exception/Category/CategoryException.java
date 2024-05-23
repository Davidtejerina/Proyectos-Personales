package ies.joatzel.erosketa.exception.Category;


public abstract class CategoryException extends RuntimeException {
    public CategoryException(String mensaje) {
        super(mensaje);
    }
}