package net.codejava;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

	   @Override
	   public boolean supports(Class<?> clazz) {
	      return User.class.isAssignableFrom(clazz);
	   }

	   @Override
	   public void validate(Object target, Errors errors) {		
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	         "name", "required.name","Username is required.");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
	 	         "password", "required.password","Password is required.");
	      
	      
	   }

}
