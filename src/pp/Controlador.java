package pp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable
{
    Mochila mochila;
    @FXML
    private TextField men, out;
    @FXML
    private Button dec;

    @FXML
    void cifrar(ActionEvent e)
    {
        if (!men.getText().isEmpty())
        {
            dec.setDisable(false);
            out.setText(mochila.cifrar(men.getText().getBytes()));
            out.selectAll();
        }
        else
        {
            out.setText("No se a podido cifrar el mensaje");
        }
    }

    @FXML
    void decifrar(ActionEvent e)
    {
        try
        {
            out.setText(mochila.decifrar(men.getText()));
        }
        catch (Exception ex)
        {
            out.setText("No se a podido decifrar el mensaje");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mochila = new Mochila();
    }
}
