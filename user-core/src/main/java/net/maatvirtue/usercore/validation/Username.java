package net.maatvirtue.usercore.validation;

import org.hibernate.validator.constraints.Email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Email
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Username
{
	String message() default "invalid username";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
