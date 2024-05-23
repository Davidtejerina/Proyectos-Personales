package ies.joatzel.erosketa.exception.Category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends CategoryException {
    public CategoryNotFoundException(String mensaje) {
        super(mensaje);
    }
}