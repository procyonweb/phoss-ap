package com.helger.phoss.ap.core.helper;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.string.StringHex;
import com.helger.security.messagedigest.EMessageDigestAlgorithm;

@Immutable
public final class HashHelper
{
  private HashHelper ()
  {}

  @NonNull
  public static String sha256Hex (final byte @NonNull [] aBytes)
  {
    final byte [] aHash = EMessageDigestAlgorithm.SHA_256.createMessageDigest ().digest (aBytes);
    return StringHex.getHexEncoded (aHash);
  }
}
