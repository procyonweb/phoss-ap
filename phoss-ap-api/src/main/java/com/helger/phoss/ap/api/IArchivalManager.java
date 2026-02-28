package com.helger.phoss.ap.api;

import org.jspecify.annotations.NonNull;

import com.helger.base.state.ESuccess;

/**
 * Base interface for archiving transactions.
 *
 * @author Philip Helger
 */
public interface IArchivalManager
{
  /**
   * Archive the provided outbound transaction. This includes the main transaction as well as all
   * attempts.
   *
   * @param sID
   *        The outbound transaction to archive. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @NonNull
  ESuccess archiveOutboundTransaction (@NonNull String sID);

  /**
   * Archive the provided inbound transaction. This includes the main transaction as well as all
   * attempts.
   *
   * @param sID
   *        The inbound transaction to archive. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @NonNull
  ESuccess archiveInboundTransaction (@NonNull String sID);
}
