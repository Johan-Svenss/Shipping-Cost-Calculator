package se.lexicon.service;

import se.lexicon.model.ShippingRequest;

public class ShippingService {

    private final ShippingCalculatorFactory factory;

    public ShippingService(ShippingCalculatorFactory factory) {
        this.factory = factory;
    }

    public double quote(ShippingRequest req) {
        ShippingCostCalculator calc = factory.getCalculator(req);
        return calc.calculate(req);
    }
}
