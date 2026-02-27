/*
 * Copyright (C) 2015-2026 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.phoss.ap.core;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.failsafe.CircuitBreaker;

public final class CircuitBreakerManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CircuitBreakerManager.class);

  private static final ConcurrentHashMap <String, CircuitBreaker <Void>> s_aBreakers = new ConcurrentHashMap <> ();

  private CircuitBreakerManager ()
  {}

  @NonNull
  private static CircuitBreaker <Void> _getOrCreate (@NonNull final String sEndpointURL)
  {
    return s_aBreakers.computeIfAbsent (sEndpointURL, k -> {
      LOGGER.info ("Creating circuit breaker for endpoint URL '" + k + "'");
      return CircuitBreaker.<Void> builder ()
                           .withFailureThreshold (APConfig.getCircuitBreakerFailureThreshold ())
                           .withDelay (Duration.ofMillis (APConfig.getCircuitBreakerOpenDurationMs ()))
                           .withSuccessThreshold (APConfig.getCircuitBreakerHalfOpenMaxAttempts ())
                           .build ();
    });
  }

  public static boolean isOpen (@NonNull final String sEndpointURL)
  {
    final CircuitBreaker <Void> aCB = s_aBreakers.get (sEndpointURL);
    if (aCB == null)
      return false;
    return aCB.isOpen ();
  }

  public static void recordSuccess (@NonNull final String sEndpointURL)
  {
    _getOrCreate (sEndpointURL).recordSuccess ();
  }

  public static void recordFailure (@NonNull final String sEndpointURL)
  {
    _getOrCreate (sEndpointURL).recordFailure ();
  }
}
