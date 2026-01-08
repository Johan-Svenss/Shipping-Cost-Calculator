package se.lexicon.service;

import org.springframework.stereotype.Component;
import se.lexicon.model.ShippingRequest;

import java.util.List;

/**
 * This uses the spring dependency injection for all available calculations
 */
public class ShippingCalculatorFactory {

    private final List<ShippingCostCalculator> calculators;

    public ShippingCalculatorFactory(List<ShippingCostCalculator> calculators) {
        this.calculators = calculators;
    }

    public ShippingCostCalculator getCalculator(ShippingRequest req) {
        return calculators.stream()
                .filter(c -> c.supports(req))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No calculator for request: " + req));
    }
}
