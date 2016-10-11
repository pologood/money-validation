package org.zalando.money.validation;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * @deprecated contributed to Hibernate Validator
 */
@Deprecated
public final class MonetaryAmountDecimalMinValidator implements ConstraintValidator<DecimalMin, MonetaryAmount> {

    private BigDecimal minValue;
    private boolean inclusive;

    @Override
    public void initialize(final DecimalMin annotation) {
        this.minValue = new BigDecimal(annotation.value());
        this.inclusive = annotation.inclusive();
    }

    @Override
    public boolean isValid(final MonetaryAmount value, final ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        final BigDecimal amount = value.getNumber().numberValueExact(BigDecimal.class);
        final int result = amount.compareTo(minValue);
        return inclusive ? result >= 0 : result > 0;
    }

}