package com.example.starter;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Launcher for Vert.x application.
 *
 * <p>Contains possible hooks for customizing the start/stop of the application.
 *
 */
public class ApplicationLauncher extends Launcher {

  /** Start up the Vert.x application. */
  public static void main(String[] args) {
    new ApplicationLauncher().dispatch(args);
  }

  @Override
  public void beforeStartingVertx(VertxOptions options) {
    super.beforeStartingVertx(options);

  }

  @Override
  public void afterStartingVertx(Vertx vertx) {
    configureExceptionHandling(vertx);
  }

  /**
   * Configures a global, fallback, exception handler that will print the stacktrace. Vert.x usually
   * swallows these exceptions and prints a reduced message.
   *
   * @param vertx The vert.x instance to configure
   */
  private void configureExceptionHandling(Vertx vertx) {
    vertx.exceptionHandler(new GenericExceptionHandler());
  }
}
