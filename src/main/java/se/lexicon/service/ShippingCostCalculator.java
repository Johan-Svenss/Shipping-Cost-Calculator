package se.lexicon.service;


import se.lexicon.model.ShippingRequest;


public interface ShippingCostCalculator {
    boolean supports(ShippingRequest req);

    double calculate(ShippingRequest req);
}



