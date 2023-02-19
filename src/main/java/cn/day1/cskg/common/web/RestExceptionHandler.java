package cn.day1.cskg.common.web;


import cn.day1.cskg.common.lang.Result;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import java.util.stream.Collectors;

/**
 * Description:
 * 参数校验拦截器
 *
 * @author maserhe
 * @date 2023/2/19 12:23
 **/
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
        @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
        public ResponseEntity<Object> handleValidatedException(Exception e) {
            Result result = null;
            if (e instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException ex =(MethodArgumentNotValidException) e;
                result = Result.fail(
                        ex.getBindingResult().getAllErrors().stream()
                                .map(ObjectError::getDefaultMessage)
                                .collect(Collectors.joining(";"))
                );
            } else if (e instanceof ConstraintViolationException){
                ConstraintViolationException ex = (ConstraintViolationException) e;
                result = Result.fail(
                        ex.getConstraintViolations().stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(";"))
                );
            }else if (e instanceof BindException) {
                BindException ex = (BindException ) e;
                result = Result.fail(
                        ex.getAllErrors().stream()
                                .map(ObjectError::getDefaultMessage)
                                .collect(Collectors.joining(";"))
                );
            }
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

}
