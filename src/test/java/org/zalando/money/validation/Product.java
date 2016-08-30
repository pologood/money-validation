package org.zalando.money.validation;


import javax.money.MonetaryAmount;

final class Product {

    @Positive
    private final MonetaryAmount price;
    
    @PositiveOrZero
    private final MonetaryAmount discountedPrice;

    @NegativeOrZero
    private final MonetaryAmount discount;
    
    @Negative
    private final MonetaryAmount priceReduction;
    
    @Zero
    private final MonetaryAmount tax;

    public Product(MonetaryAmount price, MonetaryAmount discountedPrice, MonetaryAmount discount, MonetaryAmount priceReduction, MonetaryAmount tax) {
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discount = discount;
        this.priceReduction = priceReduction;
        this.tax = tax;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public MonetaryAmount getDiscountedPrice() {
        return discountedPrice;
    }

    public MonetaryAmount getDiscount() {
        return discount;
    }

    public MonetaryAmount getPriceReduction() {
        return priceReduction;
    }

    public MonetaryAmount getTax() {
        return tax;
    }

}
