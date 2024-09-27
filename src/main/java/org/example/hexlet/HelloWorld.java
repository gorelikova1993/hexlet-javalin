package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HelloWorld.class);


    public static void main(String[] args) {
        //create app
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        //описываем что загрузится по адресу /
        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var course = new Course("Python", "Программирование");
            var page = new CoursePage(course);
            ctx.render("course/show.jte", model("page", page));
        });
        app.get("/courses", ctx -> {
            var courses = List.of(new Course("Java", "Программирование"),
                    new Course("JS", "Веб-разработка"));
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070); //стартуем веб-сервер
    }
}
