package org.zalando.money.validation;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonetaryAmountMaxValidatorTest {

    private final ConstraintValidator<Max, MonetaryAmount> unit = new MonetaryAmountMaxValidator();

    @Test
    public void nullIsValid() {
        unit.initialize(max(0));

        final boolean valid = unit.isValid(null, context());

        assertThat(valid, is(true));
    }

    @Test
    public void validIfLess() {
        unit.initialize(max(0));
        final boolean valid = unit.isValid(euro("-1"), context());

        assertThat(valid, is(true));
    }

    @Test
    public void invalidIfGreater() {
        unit.initialize(max(0));
        final boolean valid = unit.isValid(euro("1"), context());

        assertThat(valid, is(false));
    }

    @Test
    public void validIfInclude() {
        unit.initialize(max(0));
        final boolean valid = unit.isValid(euro("0"), context());

        assertThat(valid, is(true));
    }

    private MonetaryAmount euro(final String value) {
        return Money.of(new BigDecimal(value), "EUR");
    }

    private ConstraintValidatorContext context() {
        return mock(ConstraintValidatorContext.class);
    }

    private Max max(final long value) {
        final Max annotation = mock(Max.class);
        when(annotation.value()).thenReturn(value);
        return annotation;
    }

}
