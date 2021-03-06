package de.jdufner.microservice.primes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Created by jdufner on 08.12.16.
 */
@RestController
@EnableAutoConfiguration
public class PrimesController {

  private static final Logger LOG = LoggerFactory.getLogger(PrimesController.class);

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(
      @PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }

  @HystrixCommand(fallbackMethod = "primes")
  @RequestMapping(path = "/")
  public List<Integer> primes2() {
    List<Integer> primzahlen = new ArrayList<>();
    int divisionCounter = 0;
    int max = 1000000;
    for(int divisor = 2; divisor < max; divisor++) {
      boolean istPrimzahl = true;
      int wurzel = (int) sqrt(divisor);
      for (int primzahl : primzahlen) {
        if (primzahl <= wurzel) {
          divisionCounter++;
          if (divisor % primzahl == 0) {
            istPrimzahl = false;
            break;
          }
        } else {
          break;
        }
      }
//      for (int dividend = 2; dividend <= wurzel; dividend++) {
//        divisionCounter++;
//        if (divisor % dividend == 0) {
//          istPrimzahl = false;
//          break;
//        }
//      }
      if (istPrimzahl) {
        primzahlen.add(divisor);
      }
    }
    LOG.info("Für die Berechnung von Primzahlen bis {} waren {} Divisionen nötig.", max, divisionCounter);
    return primzahlen;
  }

  public List<Integer> primes() {
    return Arrays.asList(2, 3, 5, 7, 9);
  }

}
