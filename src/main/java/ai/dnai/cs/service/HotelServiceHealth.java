package ai.dnai.cs.service;


import ai.dnai.cs.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * This is an optional class used to inject application specific health check
 * into the Spring Boot health management endpoint.
 */
@Component
public class HotelServiceHealth implements HealthIndicator {

    @Autowired
    private ServiceProperties configuration;

    @Autowired
    private AppConfig appConfig;

    // extend this to create an application-specific health check according to http://goo.gl/vt8I7O
    @Override
    public Health health() {
        return Health.up().withDetail("details", "{ 'internals' : 'getting close to limit', 'profile' : '" + this.configuration.getName() + "' }")
                .withDetail("systemProperties", configuration.getSystemProperties())
                .withDetail("customProperties", appConfig)
                .status("itsok!").build();
    }

}
