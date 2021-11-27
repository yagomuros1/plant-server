package com.plant.server.business.services.exceptions;

import com.plant.server.web.controller.api.ApiErrorCodes;

public class BadRequestException extends ApiRuntimeException {

	private static final long serialVersionUID = -9144958997537219376L;
	
	private static final String CODE = ApiErrorCodes.BAD_REQUEST;


    public BadRequestException(String message) {
        super(CODE, message);
    }

    public BadRequestException(String code, String message) {
        super(code, message);
    }

	public BadRequestException(Object key, String message) {
        super(CODE, "Bad request for " + key + ": " + message);
    }
}

