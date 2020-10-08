package com.sp.app.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
@ControllerAdvice  
@RestController
public class ExceptionConfiguration {
	
	private class JsonResponse {
        String message;

        public JsonResponse(String message) {
            super();
            this.message = message;
        }

        @SuppressWarnings("unused")
		public String getMessage() {
            return message;
        }

        @SuppressWarnings("unused")
		public void setMessage(String message) {
            this.message = message;
        }       
    }
	
	@ExceptionHandler(value = Exception.class)  
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
