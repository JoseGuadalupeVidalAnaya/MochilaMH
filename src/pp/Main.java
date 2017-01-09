package pp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Mochila m=new Mochila();
        //m.cifrar("hola".getBytes());
        System.out.println(m.decifrar(m.cifrar("hola".getBytes())));
        Parent root = FXMLLoader.load(getClass().getResource("Ventana.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
