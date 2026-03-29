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
package com.helger.phoss.ap.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.peppolid.peppol.doctype.EPredefinedDocumentTypeIdentifier;
import com.helger.peppolid.peppol.process.EPredefinedProcessIdentifier;

/**
 * Test class for class {@link CPhossAP}.
 *
 * @author Philip Helger
 */
public final class CPhossAPTest
{
  @Test
  public void testDefaultLocale ()
  {
    assertNotNull (CPhossAP.DEFAULT_LOCALE);
  }

  @Test
  public void testIsPeppolSeatID ()
  {
    assertTrue (CPhossAP.isPeppolSeatID ("POP000001"));
    assertTrue (CPhossAP.isPeppolSeatID ("PAP123456"));
    assertFalse (CPhossAP.isPeppolSeatID (null));
    assertFalse (CPhossAP.isPeppolSeatID (""));
    assertFalse (CPhossAP.isPeppolSeatID ("short"));
    assertFalse (CPhossAP.isPeppolSeatID ("TOOLONGID"));
  }

  @Test
  public void testIsMLS ()
  {
    assertTrue (CPhossAP.isMLS (EPredefinedDocumentTypeIdentifier.PEPPOL_MLS_1_0.getURIEncoded (),
                                EPredefinedProcessIdentifier.urn_peppol_edec_mls.getURIEncoded ()));
    assertFalse (CPhossAP.isMLS ("some-doc-type", "some-process"));
    assertFalse (CPhossAP.isMLS (EPredefinedDocumentTypeIdentifier.PEPPOL_MLS_1_0.getURIEncoded (), "wrong-process"));
  }
}
