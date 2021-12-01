package com.plant.server.business.services.exceptions;

import java.util.Map;

public interface ICustomException {
	public String getCode();
	public Map<String, Object> getParams();
	public String getMessage();
}

