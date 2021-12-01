package com.plant.server.business.services.userservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserConstants {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final Integer STATE_INACTIVE = 0;
    public static final Integer STATE_ACTIVE = 1;
    public static final Set<Integer> STATES = new HashSet<>(Arrays.asList(STATE_INACTIVE, STATE_ACTIVE));

}
