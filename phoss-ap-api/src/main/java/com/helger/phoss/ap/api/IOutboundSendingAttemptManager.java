package com.helger.phoss.ap.api;

import java.time.OffsetDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.ICommonsList;
import com.helger.phoss.ap.api.codelist.EAttemptStatus;
import com.helger.phoss.ap.api.model.IOutboundSendingAttempt;

/**
 * Manager interface for creating and querying outbound transaction attempts.
 *
 * @author Philip Helger
 */
public interface IOutboundSendingAttemptManager
{
  @Nullable
  String create (@NonNull String sOutboundTransactionID,
                 @NonNull String sAS4MessageID,
                 @NonNull OffsetDateTime aAS4Timestamp,
                 @Nullable String sReceiptMessageID,
                 @Nullable Integer aHttpStatusCode,
                 @NonNull EAttemptStatus eAttemptStatus,
                 @Nullable String sErrorDetails);

  @NonNull
  ICommonsList <IOutboundSendingAttempt> getByTransactionID (@NonNull String sOutboundTransactionID);
}
