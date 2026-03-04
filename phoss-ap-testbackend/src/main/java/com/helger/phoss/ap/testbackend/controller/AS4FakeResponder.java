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
package com.helger.phoss.ap.testbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Receives HTTP-forwarded documents from phoss-ap's {@code HttpDocumentForwarderSPI}. Handles both
 * sync and async modes:
 * <ul>
 * <li><b>Sync mode</b>: the AP expects a JSON response containing {@code countryCodeC4}</li>
 * <li><b>Async mode</b>: the AP only checks for HTTP 200; reporting is triggered later via the
 * callback API</li>
 * </ul>
 * The endpoint URL matches the default in phoss-ap's {@code application.properties}:
 * {@code forwarding.http.endpoint=http://localhost:8888/forwarding/url}
 *
 * @author Philip Helger
 */
@RestController
@RequestMapping ("/api/as4mock")
public class AS4FakeResponder
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AS4FakeResponder.class);

  @PostMapping (path = "/plaintext/200",
                consumes = MediaType.MULTIPART_RELATED_VALUE,
                produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity <String> plainText200 (@SuppressWarnings ("unused") @RequestBody final byte [] aBody)
  {
    LOGGER.info ("In plaintext/200");
    return ResponseEntity.ok ("Any crap");
  }

  @PostMapping (path = "/plaintext/403",
                consumes = MediaType.MULTIPART_RELATED_VALUE,
                produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity <String> plainText403 (@SuppressWarnings ("unused") @RequestBody final byte [] aBody)
  {
    LOGGER.info ("In plaintext/403");
    return ResponseEntity.status (403).body ("Any crap");
  }

  @PostMapping (path = "/plaintext/500",
                consumes = MediaType.MULTIPART_RELATED_VALUE,
                produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity <String> plainText500 (@SuppressWarnings ("unused") @RequestBody final byte [] aBody)
  {
    LOGGER.info ("In plaintext/500");
    return ResponseEntity.internalServerError ().body ("Any crap");
  }
}
