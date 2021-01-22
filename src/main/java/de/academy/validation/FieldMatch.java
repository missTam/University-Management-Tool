package de.academy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/* Cross field validation
* Validation annotation to validate that 2 fields have the same value.
* The password and matchingPassword form filed have to match */

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {

    String message() default "Passwords do not match.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String first();
    String second();

    // fill in the list via data binding as defined in UserDTO
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldMatch[] value();
    }
}