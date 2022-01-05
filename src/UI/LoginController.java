package UI;

import Logic.EasyDriv;
import Logic.States.SystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static UI.Resources.Constants.WINDOW_HEIGHT;
import static UI.Resources.Constants.WINDOW_WIDTH;

public class LoginController {
    private EasyDriv easyDriv;
    private Stage stage;
    private Scene adminScene;
    private Scene userScene;
    private Parent adminPanelRoot;
    private Parent userPanelRoot;
    private AdminPanelController adminPanelController;
    private UserPanelController userPanelController;

    @FXML TextField tfEmail;
    @FXML TextField tfPassword;

    public LoginController() throws IOException
    {
        FXMLLoader loader = loaderFXML("adminPanel");
        adminPanelRoot = loader.load();
        adminPanelController = loader.getController();
        adminScene = new Scene(adminPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);


        loader = loaderFXML("userPanel");
        userPanelRoot = loader.load();
        userPanelController = loader.getController();
        userScene = new Scene(userPanelRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void set(EasyDriv easyDriv, Stage stage, Scene loginScene) throws IOException
    {
        this.easyDriv = easyDriv;
        this.stage = stage;
        adminPanelController.set(easyDriv, stage, loginScene, adminScene);
        userPanelController.set(easyDriv, stage, loginScene);
    }

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

        switch (easyDriv.getActualState())
        {
            case LOGIN -> System.out.println("Erro no login");        //TODO Dialog com erro
            case MENU -> loginSucess();
        }
    }

    private void loginSucess()
    {
        if (easyDriv.getUser().isAdmin())
            stage.setScene(adminScene);
        else
            stage.setScene(userScene);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }
}
