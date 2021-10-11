package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;


public class AddBookVerticle extends AbstractVerticle{
  final Router router;

  public AddBookVerticle(Router router){
    this.router = router;
  }

  @Override
  public void start() {
    router.route().handler(BodyHandler.create());
    router.post("/addbooks").handler(this::postData);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);

    System.out.println("Adding Books server started on 8080");
  }

  public void postData(@NotNull RoutingContext context){
    JsonArray arrayofBooks = new JsonArray();
    JsonObject body = context.getBodyAsJson();
    String bookName = body.getString("bookName");
    String author = body.getString("author");
    arrayofBooks.add(body);

    try{
      File jsonfile = new File("Jsonfile.json");
      FileWriter fileWriter = new FileWriter(jsonfile);
      fileWriter.write(String.valueOf(arrayofBooks));
      fileWriter.flush();
      fileWriter.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    context.response().end("Book is: " + arrayofBooks);
  }

}


