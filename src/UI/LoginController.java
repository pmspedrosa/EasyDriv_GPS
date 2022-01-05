package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    EasyDriv easyDriv;

    @FXML TextField tfEmail;
    @FXML TextField tfPassword;

    public void setEasyDriv(EasyDriv easyDriv) { this.easyDriv = easyDriv;}

    @FXML
    public void OnLogin(MouseEvent mouseEvent) {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();

        if(email.isEmpty() || email.isBlank()) {
            tfEmail.setStyle("-fx-border-color: red");
            return;
        }else{
            tfEmail.setStyle("-fx-border-color: none");
        }

        if(pass.isEmpty() || pass.isBlank()) {
            tfPassword.setStyle("-fx-border-color: red");
            return;
        }else{
            tfEmail.setStyle("-fx-border-color: none");
        }

        easyDriv.login(email, pass);

        //TODO Dialog com erro ou passar para outra window
        if(easyDriv.getActualState() == SystemState.LOGIN)
        {
            System.out.println("Erro no login");
        }else {

            System.out.println("Login com success");
        }

    }
}
