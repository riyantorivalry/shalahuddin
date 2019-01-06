package com.shalahuddin.web.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.stereotype.Component;

import com.shalahuddin.web.repository.AccountRepository;

@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = com.shalahuddin.web.validator.EmailExistsValidator.class)
@Documented
public @interface EmailExists {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

@Component
class EmailExistsValidator implements ConstraintValidator<com.shalahuddin.web.validator.EmailExists, String> {

	private final AccountRepository accountRepository;

	public EmailExistsValidator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	@Override
	public void initialize(com.shalahuddin.web.validator.EmailExists constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !accountRepository.exists(value);
	}
}
