package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        //create app
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        //описываем что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello World"));
        app.start(7070); //стартуем веб-сервер
    }
}
