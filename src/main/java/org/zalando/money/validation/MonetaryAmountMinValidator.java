package org.zalando.money.validation;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public final class MonetaryAmountMinValidator implements ConstraintValidator<Min, MonetaryAmount> {

    private BigDecimal minValue;

    @Override
    public void initialize(final Min annotation) {
        this.minValue = BigDecimal.valueOf(annotation.value());
    }

    @Override
    public boolean isValid(final MonetaryAmount value, final ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        final BigDecimal amount = value.getNumber().numberValueExact(BigDecimal.class);
        return amount.compareTo(minValue) >= 0;
    }

}