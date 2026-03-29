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
package com.helger.phoss.ap.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.peppol.mls.EPeppolMLSResponseCode;

/**
 * Test class for class {@link MlsOutcome}.
 *
 * @author Philip Helger
 */
public final class MlsOutcomeTest
{
  @Test
  public void testAcceptance ()
  {
    final MlsOutcome a = MlsOutcome.acceptance ();
    assertSame (EPeppolMLSResponseCode.ACCEPTANCE, a.getResponseCode ());
    assertEquals ("AP", a.getResponseCodeID ());
    assertNull (a.getResponseText ());
    assertFalse (a.hasIssues ());
  }

  @Test
  public void testAcknowledging ()
  {
    final MlsOutcome a = MlsOutcome.acknowledging ();
    assertSame (EPeppolMLSResponseCode.ACKNOWLEDGING, a.getResponseCode ());
    assertEquals ("AB", a.getResponseCodeID ());
    assertFalse (a.hasIssues ());
  }

  @Test
  public void testRejection ()
  {
    final MlsOutcomeIssue aIssue = MlsOutcomeIssue.failureOfDelivery ("Cannot deliver");
    final MlsOutcome a = MlsOutcome.rejection ("Rejected", aIssue);
    assertSame (EPeppolMLSResponseCode.REJECTION, a.getResponseCode ());
    assertEquals ("RE", a.getResponseCodeID ());
    assertEquals ("Rejected", a.getResponseText ());
    assertTrue (a.hasIssues ());
    assertEquals (1, a.getIssues ().size ());
  }
}
