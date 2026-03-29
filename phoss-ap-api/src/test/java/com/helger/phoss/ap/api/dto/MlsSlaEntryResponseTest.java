/*
 * Copyright (C) 2026 Philip Helger (www.helger.com)
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
package com.helger.phoss.ap.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class {@link MlsSlaEntryResponse}.
 *
 * @author Philip Helger
 */
public final class MlsSlaEntryResponseTest
{
  @Test
  public void testDefaultConstructor ()
  {
    final MlsSlaEntryResponse a = new MlsSlaEntryResponse ();
    assertNull (a.getSbdhInstanceID ());
    assertNull (a.getM1 ());
    assertNull (a.getM2OrM3 ());
    assertEquals (0, a.getDurationSeconds ());
    assertFalse (a.isWithinSla ());
  }

  @Test
  public void testFullConstructor ()
  {
    final MlsSlaEntryResponse a = new MlsSlaEntryResponse ("sbdh-1",
                                                           "2026-03-29T10:00:00Z",
                                                           "2026-03-29T10:05:00Z",
                                                           300,
                                                           true);
    assertEquals ("sbdh-1", a.getSbdhInstanceID ());
    assertEquals ("2026-03-29T10:00:00Z", a.getM1 ());
    assertEquals ("2026-03-29T10:05:00Z", a.getM2OrM3 ());
    assertEquals (300, a.getDurationSeconds ());
    assertTrue (a.isWithinSla ());
  }

  @Test
  public void testSetters ()
  {
    final MlsSlaEntryResponse a = new MlsSlaEntryResponse ();
    a.setSbdhInstanceID ("sbdh-2");
    a.setM1 ("m1-ts");
    a.setM2OrM3 ("m2-ts");
    a.setDurationSeconds (600);
    a.setWithinSla (false);
    assertEquals ("sbdh-2", a.getSbdhInstanceID ());
    assertEquals ("m1-ts", a.getM1 ());
    assertEquals ("m2-ts", a.getM2OrM3 ());
    assertEquals (600, a.getDurationSeconds ());
    assertFalse (a.isWithinSla ());
  }
}
