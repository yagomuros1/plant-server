package com.plant.server.util.form;

import com.plant.server.business.services.exceptions.ApiRuntimeException;
import com.plant.server.web.controller.api.ApiErrorCodes;
import com.plant.server.web.controller.api.form.GenericForm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public class FormValidator {

    public static void validate(GenericForm genericForm) throws ApiRuntimeException {
        Set<ConstraintViolation<GenericForm>> violations = getViolations(genericForm);
        if (!violations.isEmpty()) {
            ConstraintViolation<GenericForm> violation = violations.iterator().next();
            throw new ApiRuntimeException(ApiErrorCodes.INVALID_FORM, violation.getPropertyPath().toString());
        }
    }

    private static Set<ConstraintViolation<GenericForm>> getViolations(GenericForm genericForm) {
        return Validation.buildDefaultValidatorFactory().getValidator().validate(genericForm);
    }


}
