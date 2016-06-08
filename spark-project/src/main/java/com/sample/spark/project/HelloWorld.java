package com.sample.spark.project;

import freemarker.template.Configuration;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;


public class HelloWorld {

    public static void main(String[] args) {
        // 待受のポート番号を変えることもできる（デフォルトは4567）
        // port(4567);
        
        // /src/main/resources/public を公開ディレクトリとする
        staticFileLocation("/public");

        // http://localhost:4567/hello
        get("/hello", (req, res) -> "Hello World");

        // テンプレートファイルの保存先をresources/views以下に設定するための設定情報
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(HelloWorld.class, "/views");

        // http://localhost:4567
        get("/", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello, FreeMaker!!");
            // テンプレートファイルの拡張子をftlからhtmlに変更
            return new ModelAndView(attributes, "hello.html");
        // confを設定情報としてFreeMarkerEngineに渡す
        }, new FreeMarkerEngine(conf));

        // http://localhost:4567/stop
        get("/stop", (req, res) -> {
            stop();
            return null;
        });
    }
}