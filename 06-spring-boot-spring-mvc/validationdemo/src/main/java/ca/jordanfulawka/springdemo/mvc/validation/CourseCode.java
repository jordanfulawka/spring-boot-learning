package ca.jordanfulawka.springdemo.mvc.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define the default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups, groups can group related constraints
    public Class<?>[] groups() default {};

    // define default payloads, payloads provide custom
    // defaults about validation failure (severity level, error code etc)
    public Class<? extends Payload>[] payload() default {};
}
