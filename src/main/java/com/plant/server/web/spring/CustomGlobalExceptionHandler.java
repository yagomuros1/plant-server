package com.plant.server.web.spring;

import com.plant.server.business.services.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InstanceNotFoundException.class)
    public static void springHandleNotFound(HttpServletResponse response, InstanceNotFoundException exception) throws IOException {
        log.error(exception.getMessage(), exception);
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({BadRequestException.class, DuplicateInstanceException.class, ApiLogicException.class, ApiRuntimeException.class})
    public static void springHandleBadRequestError(HttpServletResponse response, BadRequestException badRequestException, ApiRuntimeException runtimeException) throws IOException {
        log.error(badRequestException.getMessage(), badRequestException);
        log.error(runtimeException.getMessage(), runtimeException);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(IOException.class)
    public static void springHandleIOError(HttpServletResponse response, IOException iOException) throws IOException {
        log.error(iOException.getMessage(), iOException);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(InternalErrorException.class)
    public static void springHandleInternalError(HttpServletResponse response, InternalErrorException internalErrorException) throws IOException {
        log.error(internalErrorException.getMessage(), internalErrorException);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(NoPermissionException.class)
    public static void springHandleNoPermission(HttpServletResponse response, NoPermissionException noPermissionException) throws IOException {
        log.error(noPermissionException.getMessage(), noPermissionException);
        response.sendError(HttpStatus.FORBIDDEN.value());
    }

}