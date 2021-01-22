package de.academy.validation;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Helper class that contains validation logic for annotation @EmailPattern */
// ConstraintValidator takes in 1) our annotation 2) type of data to validate against
public class EmailPatternValidator implements ConstraintValidator<EmailPattern, String> {

    private Pattern pattern;
    private Matcher matcher;
    // no space, no special chars, no double dot, [sth][allow .][sth]@[sth].[sth]
    private static final String REGEX_EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // initialize custom annotation (and can fetch values from it if necessary)
    @Override
    public void initialize(EmailPattern constraintAnnotation) {
    }

    // validation logic
    // enteredEmail - HTML Form data entered by the user
    // ConstraintValidatorContext - helper class for additional error messages
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        if (email == null) {
            return true;
        }

        /*pattern = Pattern.compile(REGEX_EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();*/

        return EmailValidator.getInstance().isValid(email);

    }
}
