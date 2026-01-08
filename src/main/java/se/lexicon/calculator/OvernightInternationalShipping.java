package se.lexicon.calculator;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCostCalculator;


@Component
public class OvernightInternationalShipping implements ShippingCostCalculator {

    @PostConstruct
    public void init() {
        System.out.println("Overnight International Shipping Calculator");
    }

    @Override
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.INTERNATIONAL && r.speed() == Speed.OVERNIGHT;
    }

    @Override
    public double calculate(ShippingRequest r) {
        return 40 + 7.5 * r.weightKg();
    }
}
