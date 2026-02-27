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
package com.helger.phoss.ap.forwarding.sftp;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.state.ESuccess;
import com.helger.jsch.session.DefaultSessionFactory;
import com.helger.jsch.sftp.SftpRunner;
import com.helger.phoss.ap.api.IInboundTransaction;
import com.helger.phoss.ap.api.spi.IDocumentForwarderSPI;

public class SftpDocumentForwarder implements IDocumentForwarderSPI
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SftpDocumentForwarder.class);

  private String m_sHost;
  private int m_nPort = 22;
  private String m_sUser;
  private String m_sPassword;
  private String m_sDirectory;

  public void setHost (@NonNull final String sHost) { m_sHost = sHost; }
  public void setPort (final int nPort) { m_nPort = nPort; }
  public void setUser (@NonNull final String sUser) { m_sUser = sUser; }
  public void setPassword (@Nullable final String sPassword) { m_sPassword = sPassword; }
  public void setDirectory (@NonNull final String sDirectory) { m_sDirectory = sDirectory; }

  @NonNull
  public ESuccess forwardDocument (@NonNull final IInboundTransaction aTransaction)
  {
    if (m_sHost == null || m_sUser == null || m_sDirectory == null)
    {
      LOGGER.error ("SFTP host, user, or directory not configured");
      return ESuccess.FAILURE;
    }

    try
    {
      final DefaultSessionFactory aFactory = new DefaultSessionFactory (m_sUser, m_sHost, m_nPort);
      if (m_sPassword != null)
        aFactory.setPassword (m_sPassword);

      try (final SftpRunner aSftpRunner = new SftpRunner (aFactory))
      {
        final String sRemotePath = m_sDirectory + "/" + aTransaction.getSbdhInstanceID () + ".xml";

        aSftpRunner.execute (sftp -> {
          try
          {
            sftp.put (new ByteArrayInputStream (aTransaction.getDocumentBytes ()), sRemotePath);
          }
          catch (final com.jcraft.jsch.SftpException ex2)
          {
            throw new IOException ("SFTP put failed", ex2);
          }
        });

        LOGGER.info ("Uploaded transaction " + aTransaction.getID () + " to SFTP: " + sRemotePath);
        return ESuccess.SUCCESS;
      }
    }
    catch (final Exception ex)
    {
      LOGGER.error ("SFTP forwarding failed for transaction " + aTransaction.getID (), ex);
      return ESuccess.FAILURE;
    }
  }
}
