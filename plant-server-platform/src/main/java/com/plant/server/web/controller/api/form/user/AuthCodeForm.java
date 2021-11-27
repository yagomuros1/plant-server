package com.plant.server.web.controller.api.form.user;

public interface AuthCodeForm {

    Object getKey();
    Long getMilliseconds();
    String getCode();

}
