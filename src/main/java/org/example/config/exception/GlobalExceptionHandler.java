package org.example.config.exception;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.domain.response.Result;
import org.example.common.exception.BusinessException;
import org.example.common.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.failure());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class, IllegalStateException.class})
    public ResponseEntity<?> handlePreconditionsException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.failure(e.getMessage()));
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Result(e.getCode(), e.getMessage(), (Object) null));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationExceptionException(AuthenticationException e) {
        String msg = e.getMessage();
        if (e instanceof BadCredentialsException) {
            msg = MessageUtil.get(MsgKeyConstant.SYSTEM_USERNAME_PASSWORD_NOT_MATCH);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.failure(msg));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        String msg = MessageUtil.get(MsgKeyConstant.PERMISSION_DENIED);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.failure(msg));
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> handleValidException(BindException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.failure(msg));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.failure(msg));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleValidException(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.failure(msg));
    }

}
