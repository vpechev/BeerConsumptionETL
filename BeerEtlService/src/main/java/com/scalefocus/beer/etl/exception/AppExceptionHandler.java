package com.scalefocus.beer.etl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception handler for StoreToSqlException (custom exception thrown by our service).
     * When this exception occurred a Response code 500 is expected
     * @param ex - the exception which is going to be handled
     * @return - corresponding response entity.
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity handleGenericSqlException(StoreToSqlException ex) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionHandlerResponseMessages.UNEXPECTED_EXCEPTION_OCCURED_MESSAGE);
    }

    /**
     * Exception handler for DuplicateEntityException (custom exception thrown by our service).
     * When this exception occurred a Response code 422 is expected
     * @param ex - the exception which is going to be handled
     * @return - corresponding response entity.
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity handleEntityExistInSqlException(DuplicateEntityException ex) {
        return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionHandlerResponseMessages.EXISTING_ENTITY_IN_SQL_DB_MESSAGE);
    }

    private class ResponseEntity {
        private int code;
        private String message;

        public ResponseEntity(HttpStatus code, String message){
            this.code = code.value();
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
