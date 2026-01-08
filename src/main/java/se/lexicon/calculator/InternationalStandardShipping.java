package se.lexicon.calculator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCostCalculator;

/**
 * Calculates shipping costs for international standard shipments.
 * <p>
 * - Uses Spring's @Value for property injection
 * - Added as a Spring bean using @Component
 * - Automatically registered via component scanning
 * </p>
 */
@Component
public class InternationalStandardShipping implements ShippingCostCalculator {

    @Value("${shipping.international.standard.base.cost}")
    private double baseCost;

    @Value("${shipping.international.standard.cost.per.kg}")
    private double costPerKg;

    @PostConstruct
    private void init() {
        System.out.println("Bean created: InternationalStandardShipping");
    }

    /**
     * Checks if this calculator supports the request
     */
    @Override
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.INTERNATIONAL && r.speed() == Speed.STANDARD;
    }

    /**
     * Calculates shipping cost for international standard
     */
    @Override
    public double calculate(ShippingRequest r) {
        return baseCost + costPerKg * r.weightKg();
    }
}

