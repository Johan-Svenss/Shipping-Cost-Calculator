package se.lexicon;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.AppConfig;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingService;

public class Main {
    public static void main(String[] args) {

        // Create the Spring Application Context with our Configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the service bean from the spring context
        ShippingService shippingService = context.getBean(ShippingService.class);

        // Test the shipping service implementation
        testAllShippingStrategies(shippingService);

    }

    private static void testAllShippingStrategies(ShippingService shippingService) {
        ShippingRequest domesticStandardRequest = new ShippingRequest(
                Destination.DOMESTIC,
                Speed.STANDARD,
                10.0
        );
        System.out.println("Domestic standard: " + shippingService.quote(domesticStandardRequest));

        ShippingRequest internationalExpressRequest = new ShippingRequest(
                Destination.INTERNATIONAL,
                Speed.EXPRESS,
                15.0
        );
        System.out.println("International express: " + shippingService.quote(internationalExpressRequest));

        ShippingRequest lightDomesticRequest = new ShippingRequest(
                Destination.DOMESTIC,
                Speed.STANDARD,
                5.0
        );
        System.out.println("Light domestic: " + shippingService.quote(lightDomesticRequest));

        ShippingRequest heavyInternationalExpressRequest = new ShippingRequest(
                Destination.INTERNATIONAL,
                Speed.EXPRESS,
                20.0
        );
        System.out.println("Heavy international express: " + shippingService.quote(heavyInternationalExpressRequest));

        // Test domestic express
        ShippingRequest domesticExpressRequest = new ShippingRequest(
                Destination.DOMESTIC,
                Speed.EXPRESS,
                8.0
        );
        System.out.println("Domestic express: " + shippingService.quote(domesticExpressRequest));

        // Test international standard
        ShippingRequest internationalStandardRequest = new ShippingRequest(
                Destination.INTERNATIONAL,
                Speed.STANDARD,
                12.0
        );
        System.out.println("International standard: " + shippingService.quote(internationalStandardRequest));
    }
}
