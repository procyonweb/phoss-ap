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
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for class {@link OutboundTransactionResponse}.
 *
 * @author Philip Helger
 */
public final class OutboundTransactionResponseTest
{
  @Test
  public void testDefaultConstructor ()
  {
    final OutboundTransactionResponse a = new OutboundTransactionResponse ();
    assertNull (a.getID ());
    assertNull (a.getTransactionType ());
    assertNull (a.getSenderID ());
    assertNull (a.getReceiverID ());
    assertNull (a.getDocTypeID ());
    assertNull (a.getProcessID ());
    assertNull (a.getSbdhInstanceID ());
    assertNull (a.getStatus ());
    assertEquals (0, a.getAttemptCount ());
    assertNull (a.getCreatedDT ());
    assertNull (a.getCompletedDT ());
    assertNull (a.getReportingStatus ());
    assertNull (a.getNextRetryDT ());
    assertNull (a.getErrorDetails ());
    assertNull (a.getMlsStatus ());
  }

  @Test
  public void testSetters ()
  {
    final OutboundTransactionResponse a = new OutboundTransactionResponse ();
    a.setID ("id1");
    a.setTransactionType ("business_document");
    a.setSenderID ("sender1");
    a.setReceiverID ("receiver1");
    a.setDocTypeID ("docType1");
    a.setProcessID ("process1");
    a.setSbdhInstanceID ("sbdh-1");
    a.setStatus ("sent");
    a.setAttemptCount (2);
    a.setCreatedDT ("2026-03-29T10:00:00Z");
    a.setCompletedDT ("2026-03-29T10:01:00Z");
    a.setReportingStatus ("reported");
    a.setNextRetryDT ("2026-03-29T11:00:00Z");
    a.setErrorDetails ("some error");
    a.setMlsStatus ("received_ap");

    assertEquals ("id1", a.getID ());
    assertEquals ("business_document", a.getTransactionType ());
    assertEquals ("sender1", a.getSenderID ());
    assertEquals ("receiver1", a.getReceiverID ());
    assertEquals ("docType1", a.getDocTypeID ());
    assertEquals ("process1", a.getProcessID ());
    assertEquals ("sbdh-1", a.getSbdhInstanceID ());
    assertEquals ("sent", a.getStatus ());
    assertEquals (2, a.getAttemptCount ());
    assertEquals ("2026-03-29T10:00:00Z", a.getCreatedDT ());
    assertEquals ("2026-03-29T10:01:00Z", a.getCompletedDT ());
    assertEquals ("reported", a.getReportingStatus ());
    assertEquals ("2026-03-29T11:00:00Z", a.getNextRetryDT ());
    assertEquals ("some error", a.getErrorDetails ());
    assertEquals ("received_ap", a.getMlsStatus ());
  }
}
