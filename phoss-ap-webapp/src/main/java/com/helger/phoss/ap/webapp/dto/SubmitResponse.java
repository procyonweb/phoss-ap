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
package com.helger.phoss.ap.webapp.dto;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.UsedViaReflection;
import com.helger.phoss.ap.api.codelist.EOutboundStatus;

public class SubmitResponse
{
  private String transactionID;
  private String sbdhInstanceID;
  private String status;
  private String errorDetails;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public SubmitResponse ()
  {}

  public SubmitResponse (@Nullable final String sTransactionID,
                         @Nullable final String sSbdhInstanceID,
                         @Nullable final String sStatus,
                         @Nullable final String sErrorDetails)
  {
    transactionID = sTransactionID;
    sbdhInstanceID = sSbdhInstanceID;
    status = sStatus;
    errorDetails = sErrorDetails;
  }

  public String getTransactionID ()
  {
    return transactionID;
  }

  public void setTransactionID (final String sTransactionID)
  {
    transactionID = sTransactionID;
  }

  public String getSbdhInstanceID ()
  {
    return sbdhInstanceID;
  }

  public void setSbdhInstanceID (final String sSbdhInstanceID)
  {
    sbdhInstanceID = sSbdhInstanceID;
  }

  public String getStatus ()
  {
    return status;
  }

  public void setStatus (final String sStatus)
  {
    status = sStatus;
  }

  public String getErrorDetails ()
  {
    return errorDetails;
  }

  public void setErrorDetails (final String sErrorDetails)
  {
    errorDetails = sErrorDetails;
  }

  @NonNull
  public static SubmitResponse success (@Nullable final String sTransactionID,
                                        @Nullable final String sSbdhInstanceID,
                                        @NonNull final EOutboundStatus eStatus)
  {
    return new SubmitResponse (sTransactionID, sSbdhInstanceID, eStatus.getID (), null);
  }

  @NonNull
  public static SubmitResponse rejected (@Nullable final String sTransactionID,
                                         @Nullable final String sSbdhInstanceID,
                                         @Nullable final String sErrorDetails)
  {
    return new SubmitResponse (sTransactionID, sSbdhInstanceID, EOutboundStatus.REJECTED.getID (), sErrorDetails);
  }
}
