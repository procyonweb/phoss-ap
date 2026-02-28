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
package com.helger.phoss.ap.forwarding.http;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.state.ESuccess;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.response.ResponseHandlerByteArray;
import com.helger.phoss.ap.api.model.IInboundTransaction;
import com.helger.phoss.ap.api.spi.IDocumentForwarderSPI;

public class HttpDocumentForwarder implements IDocumentForwarderSPI
{
  private static final Logger LOGGER = LoggerFactory.getLogger (HttpDocumentForwarder.class);

  private String m_sEndpoint;

  public void setEndpoint (@NonNull final String sEndpoint)
  {
    m_sEndpoint = sEndpoint;
  }

  @NonNull
  public ESuccess forwardDocument (@NonNull final IInboundTransaction aTransaction)
  {
    if (m_sEndpoint == null)
    {
      LOGGER.error ("HTTP forwarding endpoint not configured");
      return ESuccess.FAILURE;
    }

    try (final HttpClientManager aHttpClientMgr = new HttpClientManager ())
    {
      final HttpPost aPost = new HttpPost (m_sEndpoint);
      aPost.setEntity (new ByteArrayEntity (aTransaction.getDocumentBytes (),
                                            ContentType.APPLICATION_XML));

      LOGGER.info ("Forwarding inbound transaction " + aTransaction.getID () +
                   " (SBDH: " + aTransaction.getSbdhInstanceID () + ") to " + m_sEndpoint);

      aHttpClientMgr.execute (aPost, new ResponseHandlerByteArray ());

      LOGGER.info ("HTTP forwarding successful for transaction " + aTransaction.getID ());
      return ESuccess.SUCCESS;
    }
    catch (final IOException ex)
    {
      LOGGER.error ("HTTP forwarding failed for transaction " + aTransaction.getID (), ex);
      return ESuccess.FAILURE;
    }
  }
}
