package com.plant.server.business.services.exceptions;

import java.util.Map;

public class ApiLogicException extends Exception implements ICustomException {
	private static final long serialVersionUID = 7873457450512033733L;
	protected String code;
	protected Map<String, Object> params;
	protected String message;
    public ApiLogicException() {
        super();
    }
	public ApiLogicException(String code, Map<String, Object> params, String message) {
		super();
		this.code = code;
		this.params = params;
		this.message = message;
	}
	public ApiLogicException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ApiLogicException(String code) {
		super();
		this.code = code;
	}
	public ApiLogicException(Throwable t) {
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

