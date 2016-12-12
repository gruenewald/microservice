/*
 * Copyright 2016, Jürgen Dufner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.jdufner.microservice.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.sqrt;

/**
 * @author Jürgen Dufner
 * @since 0.0.1
 */
@RestController
@EnableAutoConfiguration
public class PrimesController {

  private static final Logger LOG = LoggerFactory.getLogger(PrimesController.class);

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(path = "/")
  public PrimesResult primes(@RequestParam(value = "maxPrimeNumber", defaultValue = "1000000") int maxPrimeNumber) {
    LOG.info("Ermittle alle Primzahlen bis {}.", maxPrimeNumber);
    PrimesResult primesResult = new PrimesResult();
    primesResult.setId(counter.incrementAndGet());
    List<Integer> primeNumbers = new ArrayList<>();
    long divisionCounter = 0;
    for (int divisor = 2; divisor < maxPrimeNumber; divisor++) {
      boolean isPrimeNumber = true;
      int sqrt = (int) sqrt(divisor);
      for (int primeNumber : primeNumbers) {
        if (primeNumber <= sqrt) {
          divisionCounter++;
          if (divisor % primeNumber == 0) {
            isPrimeNumber = false;
            break;
          }
        } else {
          break;
        }
      }
//      for (int dividend = 2; dividend <= wurzel; dividend++) {
//        divisionCounter++;
//        if (divisor % dividend == 0) {
//          isPrimeNumber = false;
//          break;
//        }
//      }
      if (isPrimeNumber) {
        primeNumbers.add(divisor);
      }
    }
    LOG.info("Für die Berechnung von Primzahlen bis {} waren {} Divisionen nötig.", maxPrimeNumber, divisionCounter);
    primesResult.setPrimeNumbers(primeNumbers);
    primesResult.setDivisionCounter(divisionCounter);
    primesResult.setHostname(getHostname());
    return primesResult;
  }

  private String getHostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }

}
