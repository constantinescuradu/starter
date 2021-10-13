package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Files;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;


public class AddBookVerticle extends AbstractVerticle{
  final Router router;

  public AddBookVerticle(Router router){
    this.router = router;
  }

  @Override
  public void start() {
    router.route().handler(BodyHandler.create().setBodyLimit(100));
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

    if(body.isEmpty()) {
      body.put(bookName, author);
    }

    while(arrayofBooks.isEmpty()){
      arrayofBooks.add(body);
    }

    try{
      File jsonfile = new File("Jsonfile.json");
      FileWriter fileWriter = new FileWriter(jsonfile);
      for(int i = 0; i < arrayofBooks.size(); i++){
        fileWriter.write(String.valueOf(arrayofBooks));
      }
      fileWriter.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    context.response().end("Book is: " + arrayofBooks);
  }
}


