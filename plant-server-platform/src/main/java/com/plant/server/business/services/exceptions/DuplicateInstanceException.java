package com.plant.server.business.services.exceptions;

import com.plant.server.web.controller.api.ApiErrorCodes;

public class DuplicateInstanceException extends ApiRuntimeException {

    private static final long serialVersionUID = -4689199922098201578L;

    private static final String CODE = ApiErrorCodes.ALREADY_SAVED;

    public DuplicateInstanceException(String message) {
        super(CODE, message);
    }

    public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance " + key + " for " + className);
    }

    public DuplicateInstanceException() {
        super();
    }
}

