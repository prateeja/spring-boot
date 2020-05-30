package com.example.springboot.monitoring.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;

@Configuration
public class MonitoringConfiguration {
	/**
	 * Enable metrics registry customizer 
	 * This is to enable any customer metrics we would like to push like pushing common tags
	 * registry.config().commonTags("region", "<region name>");
	 * Also we are filtering out the URI which we don't like to index
	 * 
	 * @return MeterRegistryCustomizer<MeterRegistry> metrics registry
	 */
	@Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return registry -> registry.config()
                .meterFilter(MeterFilter.deny(id -> {
                    String uri = id.getTag("uri");
                    return uri != null && uri.startsWith("/actuator");
                }))
                .meterFilter(MeterFilter.deny(id -> {
                    String uri = id.getTag("uri");
                    return uri != null && uri.contains("favicon");
                }))
                .meterFilter(new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                        return config.merge(DistributionStatisticConfig.builder()
                        		.percentiles(0.1, 0.25, 0.5, 0.75, 0.9, 0.95, 0.99, 0.995, 0.999)
                                .percentilesHistogram(true)
                                .build());
                    }
                });
    }
	
	/**
     * AspectJ aspect for intercepting types or method annotated with @Timed.
     * By default @Timed annotation does not work with method execution 
     * so we have to enable TimedAspect with uses AspectJ for intercepting method annotation
     * 
     * @param registry default meter registry
     * 
     * @return TimedAspect timed aspect
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
       return new TimedAspect(registry);
    }
}
