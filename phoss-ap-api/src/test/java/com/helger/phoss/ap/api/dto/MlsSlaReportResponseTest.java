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

import java.util.List;

import org.junit.Test;

/**
 * Test class for class {@link MlsSlaReportResponse}.
 *
 * @author Philip Helger
 */
public final class MlsSlaReportResponseTest
{
  @Test
  public void testDefaultConstructor ()
  {
    final MlsSlaReportResponse a = new MlsSlaReportResponse ();
    assertEquals (0, a.getTotalCount ());
    assertEquals (0, a.getWithinSlaCount ());
    assertEquals (0.0, a.getCompliancePercent (), 0.001);
    assertEquals (0.0, a.getTargetPercent (), 0.001);
    assertEquals (0, a.getThresholdSeconds ());
    assertFalse (a.isMeetingSla ());
    assertNull (a.getEntries ());
  }

  @Test
  public void testSetters ()
  {
    final MlsSlaReportResponse a = new MlsSlaReportResponse ();
    a.setTotalCount (100);
    a.setWithinSlaCount (99);
    a.setCompliancePercent (99.0);
    a.setTargetPercent (99.5);
    a.setThresholdSeconds (1200);
    a.setMeetingSla (false);

    final MlsSlaEntryResponse aEntry = new MlsSlaEntryResponse ("sbdh-1", "m1", "m2", 300, true);
    a.setEntries (List.of (aEntry));

    assertEquals (100, a.getTotalCount ());
    assertEquals (99, a.getWithinSlaCount ());
    assertEquals (99.0, a.getCompliancePercent (), 0.001);
    assertEquals (99.5, a.getTargetPercent (), 0.001);
    assertEquals (1200, a.getThresholdSeconds ());
    assertFalse (a.isMeetingSla ());
    assertEquals (1, a.getEntries ().size ());
    assertEquals ("sbdh-1", a.getEntries ().get (0).getSbdhInstanceID ());
  }

  @Test
  public void testMeetingSla ()
  {
    final MlsSlaReportResponse a = new MlsSlaReportResponse ();
    a.setMeetingSla (true);
    assertTrue (a.isMeetingSla ());
  }
}
