package com.github.screamingfrog;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.time.Instant;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.UIManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class App 
{
    private static final String FONT = "/fonts/Roboto-Regular.ttf";

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
            try
            {
                loadFont(FONT);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            primaryStage.setTitle("Hello World");
            final var textArea = new TextArea(AppImpl.getEnv());
            primaryStage.setScene(new Scene(textArea, 800, 600));
            primaryStage.show();
        }
        
        private static String getEnv()
        {
            final StringBuilder sb = new StringBuilder();

            sb.append("Locale: " + Locale.getDefault().toString());
            sb.append(System.lineSeparator());
            sb.append("Platform: " + getPlatformInfo());
            sb.append(System.lineSeparator());
            sb.append("Java: " + getJavaInfo());
            sb.append(System.lineSeparator());
            sb.append("TimeZone: " + TimeZone.getDefault().getDisplayName(Locale.UK)
                    + ", offset=" + TimeZone.getDefault().getOffset(Instant.now().toEpochMilli()));

            return sb.toString();
            
        }

        private static String getPlatformInfo()
        {
            return "Name '" + System.getProperty("os.name") + "' " + "Version '"
                    + System.getProperty("os.version") + "' " + "Arch '" + System.getProperty("os.arch") + "'";
        }
        
        private static String getJavaInfo()
        {
            return "Vendor '" + System.getProperty("java.vendor") + "' " + 
                    "URL '" + System.getProperty("java.vendor.url") + "' " + 
                    "Version '" + System.getProperty("java.version") + "' " +
                    "Home '" + System.getProperty("java.home") + "'";
        }
        
        static void loadFont(
            final String font) throws Exception
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            try (final InputStream fontStream = App.class.getResourceAsStream(font))
            {                
                final Font awtFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                        
                ge.registerFont(awtFont);
            }
        }
   
    }
}
