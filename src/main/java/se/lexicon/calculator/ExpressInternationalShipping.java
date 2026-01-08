package se.lexicon.calculator;

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

    @Override
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.INTERNATIONAL && r.speed() == Speed.EXPRESS;
    }

    @Override
    public double calculate(ShippingRequest r) {
        return 25 + 4.5 * r.weightKg();
    }
}
