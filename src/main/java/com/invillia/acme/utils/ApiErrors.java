package com.invillia.acme.utils;

import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApiErrors {
    Errors errors;

    public ApiErrors(Errors errors) {
        this.errors = errors;
    }

    public Map<String, String> errors() {
        Map<String, String> listErrors = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            listErrors.put(error.getField(), error.getDefaultMessage());
        }
        return listErrors;
    }

}
