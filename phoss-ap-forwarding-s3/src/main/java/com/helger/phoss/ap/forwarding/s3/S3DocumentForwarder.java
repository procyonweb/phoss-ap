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
package com.helger.phoss.ap.forwarding.s3;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.state.ESuccess;
import com.helger.base.string.StringHelper;
import com.helger.phoss.ap.api.IInboundTransaction;
import com.helger.phoss.ap.api.spi.IDocumentForwarderSPI;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3DocumentForwarder implements IDocumentForwarderSPI
{
  private static final Logger LOGGER = LoggerFactory.getLogger (S3DocumentForwarder.class);

  private String m_sBucket;
  private String m_sRegion;
  private String m_sAccessKeyId;
  private String m_sSecretAccessKey;
  private String m_sKeyPrefix;
  private String m_sLinkEndpoint;

  public void setBucket (@NonNull final String sBucket)
  {
    m_sBucket = sBucket;
  }

  public void setRegion (@NonNull final String sRegion)
  {
    m_sRegion = sRegion;
  }

  public void setAccessKeyId (@Nullable final String sAccessKeyId)
  {
    m_sAccessKeyId = sAccessKeyId;
  }

  public void setSecretAccessKey (@Nullable final String sSecretAccessKey)
  {
    m_sSecretAccessKey = sSecretAccessKey;
  }

  public void setKeyPrefix (@Nullable final String sKeyPrefix)
  {
    m_sKeyPrefix = sKeyPrefix;
  }

  public void setLinkEndpoint (@NonNull final String sLinkEndpoint)
  {
    m_sLinkEndpoint = sLinkEndpoint;
  }

  @NonNull
  public ESuccess forwardDocument (@NonNull final IInboundTransaction aTransaction)
  {
    if (m_sBucket == null || m_sRegion == null)
    {
      LOGGER.error ("S3 bucket or region not configured");
      return ESuccess.FAILURE;
    }

    try
    {
      final S3ClientBuilder aBuilder = S3Client.builder ().region (Region.of (m_sRegion));
      if (StringHelper.isNotEmpty (m_sAccessKeyId) && StringHelper.isNotEmpty (m_sSecretAccessKey))
      {
        aBuilder.credentialsProvider (StaticCredentialsProvider.create (AwsBasicCredentials.create (m_sAccessKeyId,
                                                                                                    m_sSecretAccessKey)));
      }

      try (final S3Client aS3Client = aBuilder.build ())
      {
        final String sKey = (StringHelper.isNotEmpty (m_sKeyPrefix) ? m_sKeyPrefix + "/" : "") +
                            aTransaction.getSbdhInstanceID () +
                            ".xml";

        final PutObjectRequest aPutReq = PutObjectRequest.builder ()
                                                         .bucket (m_sBucket)
                                                         .key (sKey)
                                                         .contentType ("application/xml")
                                                         .build ();

        aS3Client.putObject (aPutReq, RequestBody.fromBytes (aTransaction.getDocumentBytes ()));

        LOGGER.info ("Uploaded transaction " + aTransaction.getID () + " to S3: s3://" + m_sBucket + "/" + sKey);

        return ESuccess.SUCCESS;
      }
    }
    catch (final Exception ex)
    {
      LOGGER.error ("S3 forwarding failed for transaction " + aTransaction.getID (), ex);
      return ESuccess.FAILURE;
    }
  }
}
