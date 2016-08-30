package org.zalando.money.validation;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.DecimalMax;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonetaryAmountDecimalMaxValidatorTest {

    private final ConstraintValidator<DecimalMax, MonetaryAmount> unit = new MonetaryAmountDecimalMaxValidator();

    @Test
    public void nullIsValid() {
        unit.initialize(decimalMax("0"));

        final boolean valid = unit.isValid(null, context());

        assertThat(valid, is(true));
    }

    @Test
    public void validIfLess() {
        unit.initialize(decimalMax("0", true));
        final boolean valid = unit.isValid(euro("-1"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfGreater() {
        unit.initialize(decimalMax("0", true));
        final boolean valid = unit.isValid(euro("1"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfInclude() {
        unit.initialize(decimalMax("0", true));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfNotInclude() {
        unit.initialize(decimalMax("0", false));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfLessAndNotIncluded() {
        unit.initialize(decimalMax("0", false));
        final boolean valid = unit.isValid(euro("-1"), context());

        assertThat(valid, is(true));
    }

    private MonetaryAmount euro(final String value) {
        return Money.of(new BigDecimal(value), "EUR");
    }

    private ConstraintValidatorContext context() {
        return mock(ConstraintValidatorContext.class);
    }

    private DecimalMax decimalMax(final String value) {
        return decimalMax(value, true);
    }

    private DecimalMax decimalMax(final String value, final boolean inclusive) {
        final DecimalMax annotation = mock(DecimalMax.class);
        when(annotation.value()).thenReturn(value);
        when(annotation.inclusive()).thenReturn(inclusive);
        return annotation;
    }

}
