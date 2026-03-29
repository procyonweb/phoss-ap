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
 * Test class for class {@link ReportResponse}.
 *
 * @author Philip Helger
 */
public final class ReportResponseTest
{
  @Test
  public void testDefaultConstructor ()
  {
    final ReportResponse a = new ReportResponse ();
    assertNull (a.getTransactionID ());
    assertNull (a.getStatus ());
    assertNull (a.getMessage ());
  }

  @Test
  public void testFullConstructor ()
  {
    final ReportResponse a = new ReportResponse ("tx1", "updated", "C4 set to DE");
    assertEquals ("tx1", a.getTransactionID ());
    assertEquals ("updated", a.getStatus ());
    assertEquals ("C4 set to DE", a.getMessage ());
  }

  @Test
  public void testSetters ()
  {
    final ReportResponse a = new ReportResponse ();
    a.setTransactionID ("tx2");
    a.setStatus ("ok");
    a.setMessage ("done");
    assertEquals ("tx2", a.getTransactionID ());
    assertEquals ("ok", a.getStatus ());
    assertEquals ("done", a.getMessage ());
  }
}
