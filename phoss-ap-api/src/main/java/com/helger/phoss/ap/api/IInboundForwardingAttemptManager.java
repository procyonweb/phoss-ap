package com.helger.phoss.ap.api;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.collection.commons.ICommonsList;
import com.helger.phoss.ap.api.model.IInboundForwardingAttempt;

/**
 * Manager interface for creating and querying forwarding transaction attempts.
 *
 * @author Philip Helger
 */
public interface IInboundForwardingAttemptManager
{
  @Nullable
  String createSuccess (@NonNull String sInboundTransactionID);

  @Nullable
  String createFailure (@NonNull String sInboundTransactionID,
                        @Nullable String sErrorCode,
                        @Nullable String sErrorDetails);

  @NonNull
  ICommonsList <IInboundForwardingAttempt> getByTransactionID (@NonNull String sInboundTransactionID);
}
