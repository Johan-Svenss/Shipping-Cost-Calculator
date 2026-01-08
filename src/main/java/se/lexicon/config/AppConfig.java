package se.lexicon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import se.lexicon.calculator.ExpressInternationalShipping;
import se.lexicon.calculator.StandardDomesticShipping;
import se.lexicon.service.ShippingCalculatorFactory;
import se.lexicon.service.ShippingService;
import se.lexicon.service.ShippingCostCalculator;

import java.util.List;

/**
 * Spring application configuration for shipping cost calculator.
 * <p>
 * This class:
 * 1. Registers all components with Spring Container
 * 2. Uses component scanning to auto-detect calculators
 * 3. Provides constructor-based dependency injection
 * 4. Follows Spring best practices for minimal configuration
 * </p>
 */
@Configuration
@ComponentScan(basePackages = "se.lexicon.calculator") // Auto-scan calculator package
@PropertySource("classpath:application.properties")
public class AppConfig {

    /**
     * Creates the shipping calculator factory that uses Spring-managed calculators.
     * <p>
     * Spring automatically injects all implementations of ShippingCostCalculator
     * (found via component scanning) into this factory.
     *
     * - Component scanning (from @ComponentScan) finds all @Component classes
     * - Spring resolves all calculators at bean creation time
     * - List is injected via constructor (Spring's preferred DI)
     * </p>
     */
    @Bean
    public ShippingCalculatorFactory calculatorFactory(List<ShippingCostCalculator> calculators) {
        return new ShippingCalculatorFactory(calculators);
    }

    /**
     * Creates the shipping service that uses the factory.
     * <p>
     * Spring injects the factory bean via constructor injection (best practice).
     *
     * - No manual object creation (pure Spring IoC)
     * - Factory is resolved by Spring before service creation
     * - Follows Spring's "composition root" pattern
     * </p>
     */
    @Bean
    public ShippingService shippingService(ShippingCalculatorFactory factory) {
        return new ShippingService(factory);
    }
}
