package com.example.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class ManageBookVerticle extends AbstractVerticle {

  final Router router;

  public ManageBookVerticle(Router router){
    this.router = router;
  }
  @Override
  public void start() {
    router.route().handler(BodyHandler.create());
    router.get("/managebooks").handler(this::fetchData);
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8081);
    System.out.println("Managing Books server started on 8080");
  }

  public void fetchData(RoutingContext context) {

    try{
      JSONParser parser = new JSONParser();
      JSONArray bookarray = (JSONArray) parser.parse(new FileReader("C:\\Users\\rconsta2\\IdeaProjects\\starter\\Jsonfile.json"));

      for(Object object: bookarray){
        JSONObject book = (JSONObject) object;

        String bookName = (String) book.get("bookName");
        System.out.println(bookName);

        String author = (String) book.get("author");
        System.out.println(author);
      }
    }catch (Exception e){
      System.out.println("An Exception has occured");
    }
  }
}
