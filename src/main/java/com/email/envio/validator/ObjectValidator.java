package com.email.envio.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ObjectValidator <T>{

  public Map<String, String> validate(T objectToValidate){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations =  validator.validate(objectToValidate);
        if(!violations.isEmpty()){
            return  violations
                    .stream()
                    .collect(Collectors.toMap(c -> c.getPropertyPath().toString(), ConstraintViolation::getMessage));
        }

        return Collections.emptyMap();

    }
}
