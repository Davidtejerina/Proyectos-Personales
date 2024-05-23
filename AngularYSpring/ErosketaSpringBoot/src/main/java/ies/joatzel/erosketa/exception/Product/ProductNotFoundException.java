package ies.joatzel.erosketa.exception.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String mensaje) {
        super(mensaje);
    }
}
