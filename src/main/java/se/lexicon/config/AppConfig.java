package se.lexicon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.lexicon.calculator.ExpressInternationalShipping;
import se.lexicon.calculator.StandardDomesticShipping;
import se.lexicon.service.ShippingCalculatorFactory;
import se.lexicon.service.ShippingCostCalculator;
import se.lexicon.service.ShippingService;

import java.util.List;

@Configuration
public class AppConfig {

    // Define the factory of shipping calculator
    @Bean
    public ShippingCalculatorFactory shippingCalculatorFactory() {
        return new ShippingCalculatorFactory(
                List.of(
                    new StandardDomesticShipping(),
                    new ExpressInternationalShipping()
            )
        );
    }
    // Define the service that uses the factory
    @Bean
    public ShippingService shippingService(ShippingCalculatorFactory factory) {
        return new ShippingService(factory);
    }
}
