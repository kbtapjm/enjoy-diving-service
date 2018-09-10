package kr.co.pjm.diving.service.common.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pjm.diving.common.exception.ResourceNotFoundException;
import kr.co.pjm.diving.service.common.domain.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
    List<String> errors = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.toList());
    
    if (log.isDebugEnabled()) log.debug("errors : {}", errors.toString()); 

    ErrorDto errorDto = new ErrorDto(errors.toString(), HttpStatus.BAD_REQUEST.value(), req.getRequestURI(), new Date());
    
    return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorDto> handlerResourceNotFoundException(HttpServletRequest req, ResourceNotFoundException e) {
    ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.value(), req.getRequestURI(), new Date());

    return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorDto> handlerHttpRequestMethodNotSupportedException(HttpServletRequest req,
      HttpRequestMethodNotSupportedException e) {

    ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value(), req.getRequestURI(), new Date());

    return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorDto> handlerHttpMediaTypeNotSupportedException(HttpServletRequest req,
      HttpMediaTypeNotSupportedException e) {

    ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value(), req.getRequestURI(), new Date());

    return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDto> handlerException(HttpServletRequest req,Exception e) {

    ErrorDto errorDto = new ErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), req.getRequestURI(), new Date());

    return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
