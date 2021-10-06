package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class ManageBookVerticle extends AbstractVerticle {

  final Router router;

  public ManageBookVerticle(Router router){
    this.router = router;
  }

  @Override
  public void start() throws Exception {
 /*   router.route("/managebooks").handler(context1 -> {
      context1.response()
        .putHeader("content-type","text/plain")
        .end("Managing Books" + context1.queryParam("name"));
    });
*/

    router.get("/managebooks").handler(context1 -> {
      HttpServerResponse httpServerResponse = context1.response();
      httpServerResponse.setChunked(true);
      httpServerResponse.write("GET Triggered\n");
      httpServerResponse.end();

    });
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);

    System.out.println("Managing Books server started on 8080");
  }
}
