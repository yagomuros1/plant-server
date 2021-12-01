package com.plant.server.business.services.exceptions;

import java.util.Map;

public class ApiRuntimeException extends RuntimeException implements ICustomException {
	private static final long serialVersionUID = 7118984385790055445L;
	protected String code;
	protected Map<String, Object> params;
	protected String message;
    public ApiRuntimeException() {
        super();
    }
	public ApiRuntimeException(String code, Map<String, Object> params, String message) {
		super();
		this.code = code;
		this.params = params;
		this.message = message;
	}
	public ApiRuntimeException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ApiRuntimeException(String code) {
		super();
		this.code = code;
	}
	public ApiRuntimeException(Throwable t) {
		super(t);
	}
	public String getCode() {
		return this.code;
	}
	public Map<String, Object> getParams() {
		return this.params;
	}
	public String getMessage() {
		return this.message;
	}
}

