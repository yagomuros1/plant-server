package com.plant.server.business.entities.user;

import com.plant.server.util.string.StringUtils;

public class UserConstants {
    public static final String USER_TYPE_ADMIN = "ADMIN";
    public static final String USER_TYPE_MEDIATOR = "MEDIATOR";
    public static final int EMAIL_MAX_LENGTH = StringUtils.FIELD_DEFAULT_MAX_LENGTH;
    public static final int PASSWORD_MAX_LENGTH = StringUtils.FIELD_DEFAULT_MAX_LENGTH;
}