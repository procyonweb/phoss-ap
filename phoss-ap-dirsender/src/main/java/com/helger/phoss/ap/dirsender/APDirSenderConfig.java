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
package com.helger.phoss.ap.dirsender;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.config.fallback.IConfigWithFallback;
import com.helger.phoss.ap.api.config.APConfigProvider;
import com.helger.phoss.ap.api.config.APConfigurationProperties;

/**
 * Configuration accessor for the directory-based SBD sender.
 *
 * @author Philip Helger
 * @since v0.2.0
 */
@Immutable
public final class APDirSenderConfig
{
  private APDirSenderConfig ()
  {}

  @NonNull
  private static IConfigWithFallback _getConfig ()
  {
    return APConfigProvider.getConfig ();
  }

  /**
   * @return {@code true} if the directory sender is enabled.
   */
  public static boolean isEnabled ()
  {
    return _getConfig ().getAsBoolean (APConfigurationProperties.DIRSENDER_ENABLED,
                                       APConfigurationProperties.DIRSENDER_ENABLED_DEFAULT);
  }

  /**
   * @return The absolute path of the watch directory. May be <code>null</code> if not configured.
   */
  @Nullable
  public static String getDirectory ()
  {
    return _getConfig ().getAsString (APConfigurationProperties.DIRSENDER_DIRECTORY);
  }

  /**
   * @return The interval in milliseconds between directory scans.
   */
  @Nonnegative
  public static long getScanIntervalMs ()
  {
    return _getConfig ().getAsLong (APConfigurationProperties.DIRSENDER_SCAN_INTERVAL_MS,
                                    APConfigurationProperties.DIRSENDER_SCAN_INTERVAL_MS_DEFAULT);
  }

  /**
   * @return The delay in milliseconds before the first directory scan after startup.
   */
  @Nonnegative
  public static long getInitialDelayMs ()
  {
    return _getConfig ().getAsLong (APConfigurationProperties.DIRSENDER_INITIAL_DELAY_MS,
                                    APConfigurationProperties.DIRSENDER_INITIAL_DELAY_MS_DEFAULT);
  }
}
