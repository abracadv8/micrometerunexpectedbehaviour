package net.andreaskluth.spring.micrometerunexpectedbehaviour;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MicrometerUnexpectedBehaviourApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicrometerUnexpectedBehaviourApplication.class, args);
  }

  @org.springframework.context.annotation.Configuration
  public static class Configuration {

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
      return new TimedAspect(meterRegistry);
    }
  }

  @RestController
  public static class DemoController {

    @Timed("demo_controller_omg")
    @GetMapping("/")
    public String omg() {
      return "test";
    }
  }
}
