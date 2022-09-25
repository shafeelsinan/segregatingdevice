package net.codejava;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.codejava.vo.BuyVo;

@Component
public class BuyValidator implements Validator {

	   @Override
	   public boolean supports(Class<?> clazz) {
	      return BuyVo.class.isAssignableFrom(clazz);
	   }

	   @Override
	   public void validate(Object target, Errors errors) {		
//	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
//	         "name", "required.name","Username is required.");
//	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
//	 	         "email", "required.email","Email is required.");
//	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
//	 	         "password", "required.password","Password is required.");
//	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
//		 	         "usertype", "required.usertype","Select user type.");
	      
	      
	   }

}
