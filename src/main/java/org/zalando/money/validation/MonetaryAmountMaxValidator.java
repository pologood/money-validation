package org.zalando.money.validation;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

public final class MonetaryAmountMaxValidator implements ConstraintValidator<Max, MonetaryAmount> {

    private BigDecimal maxValue;

    @Override
    public void initialize(final Max annotation) {
        this.maxValue = BigDecimal.valueOf(annotation.value());
    }

    @Override
    public boolean isValid(final MonetaryAmount value, final ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        final BigDecimal amount = value.getNumber().numberValueExact(BigDecimal.class);
        return amount.compareTo(maxValue) <= 0;
    }

}
