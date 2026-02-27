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

import java.util.ServiceLoader;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.phoss.ap.api.spi.IDocumentForwarderSPI;
import com.helger.phoss.ap.api.spi.IDocumentVerifierSPI;
import com.helger.phoss.ap.api.spi.INotificationHandlerSPI;
import com.helger.phoss.ap.api.spi.IReceiverCheckSPI;

public final class APMetaManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (APMetaManager.class);

  private static IDocumentForwarderSPI s_aForwarder;
  private static final ICommonsList <IDocumentVerifierSPI> s_aVerifiers = new CommonsArrayList <> ();
  private static final ICommonsList <IReceiverCheckSPI> s_aReceiverChecks = new CommonsArrayList <> ();
  private static final ICommonsList <INotificationHandlerSPI> s_aNotificationHandlers = new CommonsArrayList <> ();

  private APMetaManager ()
  {}

  public static void init ()
  {
    LOGGER.info ("Initializing APMetaManager");

    // Load SPI implementations
    for (final IDocumentForwarderSPI aForwarder : ServiceLoader.load (IDocumentForwarderSPI.class))
    {
      s_aForwarder = aForwarder;
      LOGGER.info ("Loaded document forwarder: " + aForwarder.getClass ().getName ());
    }

    for (final IDocumentVerifierSPI aVerifier : ServiceLoader.load (IDocumentVerifierSPI.class))
    {
      s_aVerifiers.add (aVerifier);
      LOGGER.info ("Loaded document verifier: " + aVerifier.getClass ().getName ());
    }

    for (final IReceiverCheckSPI aCheck : ServiceLoader.load (IReceiverCheckSPI.class))
    {
      s_aReceiverChecks.add (aCheck);
      LOGGER.info ("Loaded receiver check: " + aCheck.getClass ().getName ());
    }

    for (final INotificationHandlerSPI aHandler : ServiceLoader.load (INotificationHandlerSPI.class))
    {
      s_aNotificationHandlers.add (aHandler);
      LOGGER.info ("Loaded notification handler: " + aHandler.getClass ().getName ());
    }

    LOGGER.info ("APMetaManager initialized successfully");
  }

  public static void shutdown ()
  {
    LOGGER.info ("Shutting down APMetaManager");
  }

  @Nullable
  public static IDocumentForwarderSPI getForwarder ()
  {
    return s_aForwarder;
  }

  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <IDocumentVerifierSPI> getAllVerifiers ()
  {
    return s_aVerifiers.getClone ();
  }

  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <IReceiverCheckSPI> getAllReceiverChecks ()
  {
    return s_aReceiverChecks.getClone ();
  }

  @NonNull
  @ReturnsMutableCopy
  public static ICommonsList <INotificationHandlerSPI> getAllNotificationHandlers ()
  {
    return s_aNotificationHandlers.getClone ();
  }
}
