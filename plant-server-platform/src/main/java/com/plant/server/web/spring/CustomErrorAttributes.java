package com.plant.server.web.spring;

import java.util.HashMap;
import java.util.Map;

import com.plant.server.business.services.exceptions.ICustomException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable throwable = getError(webRequest);
        ICustomException customApiException = getCustomApiException(throwable);
        
        // custom API exception
        if (customApiException != null) {
            Map<String, Object> causeErrorAttributes = new HashMap<String, Object>();
            causeErrorAttributes.put("code", customApiException.getCode());

            Map<String, Object> paramsErrorAttributes = new HashMap<String, Object>();
            Map<String, Object> paramsMap = customApiException.getParams();
            if (paramsMap!=null) {
                for (String paramKey : paramsMap.keySet()) {
                	paramsErrorAttributes.put(paramKey, paramsMap.get(paramKey));
                }
            }
            causeErrorAttributes.put("params", paramsErrorAttributes);
            
            causeErrorAttributes.put("message", customApiException.getMessage());
            
            errorAttributes.put("cause", causeErrorAttributes);
        }
		return errorAttributes;
    }
	
	private static final ICustomException getCustomApiException(Throwable throwable) {
		if (throwable == null) {
			return null;
		}
		if (throwable instanceof ICustomException) {
			return (ICustomException) throwable;
		}
		return getCustomApiException(throwable.getCause());
	}
} 