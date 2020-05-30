# Monitoring with Spring Boot using Micrometer & Actuator
Micrometer is a dimensional-first metrics collection facade whose aim is to allow you to time, count, and gauge your code with a vendor neutral API. Through classpath and configuration, you may select one or several monitoring systems to export your metrics data to. Think of it like SLF4J, but for metrics!

Micrometer is the metrics collection facility included in Spring Boot 2’s Actuator. It has also been backported to Spring Boot 1.5, 1.4, and 1.3 with the addition of another dependency.

Micrometer adds richer meter primitives to the counters and gauges that existed in Spring Boot 1. For example, a single Micrometer Timer is capable of producing time series related to throughput, total time, maximum latency of recent samples, pre-computed percentiles, percentile histograms, and SLA boundary counts.

## Spring Boot 2 autoconfigures quite a few metrics for you, including:

1. JVM, report utilization of:
2. Various memory and buffer pools
3. Statistics related to garbage collection
4. Thread utilization
5. Number of classes loaded/unloaded
6. CPU usage
7. Spring MVC and WebFlux request latencies
8. RestTemplate latencies
9. Cache utilization
10. Datasource utilization, including HikariCP pool metrics
11. RabbitMQ connection factories
12. File descriptor usage
13. Logback: record the number of events logged to Logback at each level
14. Uptime: report a gauge for uptime and a fixed gauge representing the application’s absolute start time
15. Tomcat usage
