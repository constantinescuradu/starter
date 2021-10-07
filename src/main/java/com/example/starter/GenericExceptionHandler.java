package com.example.starter;

import io.vertx.core.Handler;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.common.WebEnvironment;

/**
 * Replaces the built in, slim, exception handler with a more expressive version. During trace
 * logging, the counts are presented as well for more observability.
 *
 * @author thced
 */
public class GenericExceptionHandler implements Handler<Throwable> {

  private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);
  private long counter = 0;

  @Override
  public void handle(Throwable exception) {
    counter++;
    if (WebEnvironment.development()) {
      var message = String.format("Unhandled exception (%d)", counter);
      log.error(message, exception);
    } else {
      log.error("Unhandled exception", exception);
    }
  }
}
