package de.academy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* java annotation for custom validation of email
* @Constraint - validation business rule
* @Target - what the annotation can be applied to
* @Retention - retain annotation in java class file; process at runtime
* */
@Constraint(validatedBy = EmailPatternValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailPattern {

    // define default error message
    String message() default "Invalid email";

    // define default group related validation constraints together
    Class<?>[] groups() default {};

    // define default payloads - additional information about validation error
    Class<? extends Payload>[] payload() default {};

}
