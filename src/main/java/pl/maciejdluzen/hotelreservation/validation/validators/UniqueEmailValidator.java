package pl.maciejdluzen.hotelreservation.validation.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.maciejdluzen.hotelreservation.validation.constraints.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Scope("prototype")
@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private ValidationService validationService;

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.debug("Validating unique email: {}", value);
        boolean unique = validationService.isUniqueEmail(value);
        log.debug("Is email '{}' unique? {}", value, unique);
        return unique;
    }

    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }
}
