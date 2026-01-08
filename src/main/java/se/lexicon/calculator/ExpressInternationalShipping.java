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
public class ExpressInternationalShipping implements ShippingCostCalculator {

    @Value("${shipping.international.express.base.fee}")
    private double baseFee;

    @Value("${shipping.international.express.cost.kg}")
    private double costPerKg;

    @PostConstruct
    private void init() {
        System.out.println("ExpressInternationalShipping init");
    }

    @Override
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.INTERNATIONAL && r.speed() == Speed.EXPRESS;
    }

    @Override
    public double calculate(ShippingRequest r) {
        return baseFee + costPerKg * r.weightKg();
    }
}
