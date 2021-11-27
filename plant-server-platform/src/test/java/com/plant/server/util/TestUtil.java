package com.plant.server.util;

import java.util.Collections;

import com.plant.server.web.controller.api.ApiUtils;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.google.common.net.HttpHeaders;

public class TestUtil {
	
	public static final String ADMIN_USERNAME = "admin";
	public static final String ADMIN_PASSWORD = "a";

	public static final String ADMIN_WRONGUSERNAME = "wrongusername";
	public static final String ADMIN_WRONGPASSWORD = "wrongpassword";

	public static final Long VALID_USER_ID = 3L;
	public static final String VALID_USER_USERNAME = "dfernandez";
	public static final String VALID_USER_EMAIL = "dfernandez@cleventy.com";
	public static final String VALID_USER_PASSWORD = "a";

	public static final Long INACTIVE_USER_ID = 46511L;
	public static final String INACTIVE_USER_USERNAME = "inactive";

	public static final String INVALID_USER_USERNAME = "wrong";

	public static void addAcceptVersionInterceptor(TestRestTemplate template) {
		template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.ACCEPT, ApiUtils.TYPE_CMG_JSON_V_1_0_0);
			return execution.execute(request, body);
		}));
	}

}
