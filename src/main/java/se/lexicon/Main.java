package se.lexicon;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.calculator.ExpressInternationalShipping;
import se.lexicon.calculator.StandardDomesticShipping;
import se.lexicon.config.AppConfig;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCalculatorFactory;
import se.lexicon.service.ShippingCostCalculator;
import se.lexicon.service.ShippingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create the Spring Application Context with our Configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the service bean from the spring context
        ShippingService shippingService = context.getBean(ShippingService.class);

        // Test the shipping service implementation
        testShippment(shippingService);

    }

    private static void testShippment(ShippingService shippingService) {
        ShippingRequest domesticStandardRequest = new ShippingRequest(
                Destination.DOMESTIC,
                Speed.STANDARD,
                250.0
        );

        System.out.println("Domestic standart: " + shippingService.quote(domesticStandardRequest));

    }
}
