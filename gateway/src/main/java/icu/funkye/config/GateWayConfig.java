package icu.funkye.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator addHeaderRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("demo-gateway-service", r -> r.path("/provider/**").uri("lb://demo-service"))
            .route("demo-gateway-client", r -> r.path("/test/**").uri("lb://demo-client")).build();
    }
}
