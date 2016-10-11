package org.zalando.money.validation;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

/**
 * @deprecated contributed to Hibernate Validator
 */
@Deprecated
public final class MonetaryAmountDecimalMaxValidator implements ConstraintValidator<DecimalMax, MonetaryAmount> {

    private BigDecimal maxValue;
    private boolean inclusive;

    @Override
    public void initialize(final DecimalMax annotation) {
        this.maxValue = new BigDecimal(annotation.value());
        this.inclusive = annotation.inclusive();
    }

    @Override
    public boolean isValid(final MonetaryAmount value, final ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        final BigDecimal amount = value.getNumber().numberValueExact(BigDecimal.class);
        final int result = amount.compareTo(maxValue);
        return inclusive ? result <= 0 : result < 0;
    }

}
