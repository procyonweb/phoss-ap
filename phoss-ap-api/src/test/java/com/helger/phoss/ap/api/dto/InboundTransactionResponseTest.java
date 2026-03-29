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
 * Test class for class {@link InboundTransactionResponse}.
 *
 * @author Philip Helger
 */
public final class InboundTransactionResponseTest
{
  @Test
  public void testDefaultConstructor ()
  {
    final InboundTransactionResponse a = new InboundTransactionResponse ();
    assertNull (a.getID ());
    assertNull (a.getSenderID ());
    assertNull (a.getReceiverID ());
    assertNull (a.getDocTypeID ());
    assertNull (a.getProcessID ());
    assertNull (a.getAS4MessageID ());
    assertNull (a.getSbdhInstanceID ());
    assertNull (a.getStatus ());
    assertEquals (0, a.getAttemptCount ());
    assertNull (a.getReceivedDT ());
    assertNull (a.getCompletedDT ());
    assertNull (a.getReportingStatus ());
    assertNull (a.getNextRetryDT ());
    assertNull (a.getErrorDetails ());
    assertNull (a.getC4CountryCode ());
    assertFalse (a.isDuplicateAS4 ());
    assertFalse (a.isDuplicateSBDH ());
    assertNull (a.getMlsResponseCode ());
  }

  @Test
  public void testSetters ()
  {
    final InboundTransactionResponse a = new InboundTransactionResponse ();
    a.setID ("id1");
    a.setSenderID ("sender1");
    a.setReceiverID ("receiver1");
    a.setDocTypeID ("docType1");
    a.setProcessID ("process1");
    a.setAS4MessageID ("as4-1");
    a.setSbdhInstanceID ("sbdh-1");
    a.setStatus ("forwarded");
    a.setAttemptCount (3);
    a.setReceivedDT ("2026-03-29T10:00:00Z");
    a.setCompletedDT ("2026-03-29T10:01:00Z");
    a.setReportingStatus ("reported");
    a.setNextRetryDT ("2026-03-29T11:00:00Z");
    a.setErrorDetails ("some error");
    a.setC4CountryCode ("DE");
    a.setDuplicateAS4 (true);
    a.setDuplicateSBDH (true);
    a.setMlsResponseCode ("AP");

    assertEquals ("id1", a.getID ());
    assertEquals ("sender1", a.getSenderID ());
    assertEquals ("receiver1", a.getReceiverID ());
    assertEquals ("docType1", a.getDocTypeID ());
    assertEquals ("process1", a.getProcessID ());
    assertEquals ("as4-1", a.getAS4MessageID ());
    assertEquals ("sbdh-1", a.getSbdhInstanceID ());
    assertEquals ("forwarded", a.getStatus ());
    assertEquals (3, a.getAttemptCount ());
    assertEquals ("2026-03-29T10:00:00Z", a.getReceivedDT ());
    assertEquals ("2026-03-29T10:01:00Z", a.getCompletedDT ());
    assertEquals ("reported", a.getReportingStatus ());
    assertEquals ("2026-03-29T11:00:00Z", a.getNextRetryDT ());
    assertEquals ("some error", a.getErrorDetails ());
    assertEquals ("DE", a.getC4CountryCode ());
    assertTrue (a.isDuplicateAS4 ());
    assertTrue (a.isDuplicateSBDH ());
    assertEquals ("AP", a.getMlsResponseCode ());
  }
}
