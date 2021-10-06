package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;


public class AddBookVerticle extends AbstractVerticle{
  final Router router;

  public AddBookVerticle(Router router){
    this.router = router;
  }

  @Override
  public void start() throws Exception {
    router.route().handler(BodyHandler.create());
    router.post("/addbooks").handler(this::postData);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);

    System.out.println("Adding Books server started on 8080");
  }

  public void postData(RoutingContext context){
    JsonArray arrayofBooks = new JsonArray();
    JsonObject body = context.getBodyAsJson();
    String bookName = body.getString("bookName");
    String author = body.getString("author");

    //aici ma gandesc sa scriu tot jsonarray-ul intr-un text,
   // ca in momentul in care verticle-ul primeste post request, fiecare carte sa fie stocata in memorie

    arrayofBooks.add(body);

    try{

      File jsonfile = new File("Jsonfile.txt");
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

