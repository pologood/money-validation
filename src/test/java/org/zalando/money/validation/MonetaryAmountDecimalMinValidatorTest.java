package org.zalando.money.validation;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonetaryAmountDecimalMinValidatorTest {

    private final ConstraintValidator<DecimalMin, MonetaryAmount> unit = new MonetaryAmountDecimalMinValidator();

    @Test
    public void nullIsValid() {
        unit.initialize(decimalMin("0"));

        final boolean valid = unit.isValid(null, context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfLess() {
        unit.initialize(decimalMin("0", true));
        final boolean valid = unit.isValid(euro("-1"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfGreater() {
        unit.initialize(decimalMin("0", true));
        final boolean valid = unit.isValid(euro("1"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void validIfInclude() {
        unit.initialize(decimalMin("0", true));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfNotInclude() {
        unit.initialize(decimalMin("0", false));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfGreaterAndNotIncluded() {
        unit.initialize(decimalMin("0", false));
        final boolean valid = unit.isValid(euro("1"), context());

        assertThat(valid, is(true));
    }

    private MonetaryAmount euro(final String value) {
        return Money.of(new BigDecimal(value), "EUR");
    }

    private ConstraintValidatorContext context() {
        return mock(ConstraintValidatorContext.class);
    }

    private DecimalMin decimalMin(final String value) {
        return decimalMin(value, true);
    }

    private DecimalMin decimalMin(final String value, final boolean inclusive) {
        final DecimalMin annotation = mock(DecimalMin.class);
        when(annotation.value()).thenReturn(value);
        when(annotation.inclusive()).thenReturn(inclusive);
        return annotation;
    }

}
