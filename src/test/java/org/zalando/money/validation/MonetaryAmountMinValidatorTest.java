package org.zalando.money.validation;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonetaryAmountMinValidatorTest {

    private final ConstraintValidator<Min, MonetaryAmount> unit = new MonetaryAmountMinValidator();

    @Test
    public void nullIsValid() {
        unit.initialize(min(0));

        final boolean valid = unit.isValid(null, context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfLess() {
        unit.initialize(min(0));
        final boolean valid = unit.isValid(euro("-1"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfGreater() {
        unit.initialize(min(0));
        final boolean valid = unit.isValid(euro("1"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void validIfInclude() {
        unit.initialize(min(0));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(true));
    }

    private MonetaryAmount euro(final String value) {
        return Money.of(new BigDecimal(value), "EUR");
    }

    private ConstraintValidatorContext context() {
        return mock(ConstraintValidatorContext.class);
    }

    private Min min(final long value) {
        final Min annotation = mock(Min.class);
        when(annotation.value()).thenReturn(value);
        return annotation;
    }

}
