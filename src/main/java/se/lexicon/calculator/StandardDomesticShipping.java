package se.lexicon.calculator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCostCalculator;

/**
 * This class will be registered as a component
 * Spring will automatically find this class by scanning this package, if not exclude it
 */
@Component
public class StandardDomesticShipping implements ShippingCostCalculator {

    @Value("${shipping.domestic.standard.base.fee}")
    private double baseFee;

    @Value("${shipping.domestic.standard.cost.kg}")
    private double costPerKg;

    @PostConstruct
    private void  init()
    {
        System.out.println("Standard Domestic Shipping Calculator");
    }

    @Override
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.DOMESTIC && r.speed() == Speed.STANDARD;
    }

    @Override
    public double calculate(ShippingRequest r) {
        return baseFee + costPerKg * r.weightKg();
    }
}