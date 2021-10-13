package com.example.starter;

import io.vertx.core.*;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start() {

    // Create a Router
    Router router = Router.router(vertx);

    vertx.deployVerticle(new ManageBookVerticle(router));
    vertx.deployVerticle(new AddBookVerticle(router));
  }
}
