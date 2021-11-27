package com.plant.server.business.services.exceptions;

import com.plant.server.web.controller.api.ApiErrorCodes;

public class InstanceNotFoundException extends ApiRuntimeException {

    private static final long serialVersionUID = 3202777853762428562L;

    private static final String CODE = ApiErrorCodes.INSTANCE_NOT_FOUND;

    public InstanceNotFoundException() {
        super();
    }

    public InstanceNotFoundException(String message) {
        super(CODE, message);
    }

    public InstanceNotFoundException(Object key, String className) {
        super(CODE, "Instance not found " + key + " for " + className);
    }
}

