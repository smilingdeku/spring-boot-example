package org.example.config.exception;

import org.example.common.domain.ResultData;
import org.example.common.enums.Code;
import org.example.common.constant.MsgKeyConstant;
import org.example.common.exception.BusinessException;
import org.example.common.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("UnknownException", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultData<>(Code.UNKNOWN_ERROR));
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultData<>(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationExceptionException(AuthenticationException e) {
        String msg = e.getMessage();
        if (e instanceof BadCredentialsException) {
            msg = MessageUtil.message(MsgKeyConstant.SYSTEM_USERNAME_PASSWORD_NOT_MATCH);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResultData<>(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> handleValidException(BindException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultData<>(HttpStatus.BAD_REQUEST.value(), msg));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultData<>(HttpStatus.BAD_REQUEST.value(), msg));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleValidException(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResultData<>(HttpStatus.BAD_REQUEST.value(), msg));
    }

}
