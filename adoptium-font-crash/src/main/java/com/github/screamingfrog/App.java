package com.github.screamingfrog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AppImpl.launch(AppImpl.class, args);
    }
    
    public static class AppImpl extends Application
    {
        
        @Override
        public void start(
            final Stage primaryStage) 
        {
            primaryStage.setTitle("Hello World");
            final var textArea = new TextArea("Hello World!");
            primaryStage.setScene(new Scene(textArea, 800, 600));
            primaryStage.show();
        }
    }
}
