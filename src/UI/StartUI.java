package UI;

import Logic.EasyDriv;
import Utils.JSONManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static UI.Resources.Constants.WINDOW_HEIGHT;
import static UI.Resources.Constants.WINDOW_WIDTH;

public class StartUI extends Application {

    private EasyDriv easyDriv;
    private Stage stage;

    //Parents
    private Parent loginRoot;

    private LoginController loginController;
    private Scene loginScene;


    @Override
    public void start(Stage primaryStage) throws IOException {

        easyDriv = new EasyDriv();
        stage = primaryStage;

        initializeRootsAndControllers();
        //Image icone = new Image(Objects.requireNonNull(StartUI.class.getResourceAsStream("Resources/icone.png")));
        primaryStage.setScene(loginScene);

        primaryStage.setResizable(false);
        primaryStage.setTitle("EasyDriv");
        //primaryStage.getIcons().add(icone);

        primaryStage.setOnCloseRequest(ev -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();

    }

    private void initializeRootsAndControllers() throws IOException {
        FXMLLoader loader = loaderFXML("login");
        loginRoot = loader.load();
        loginController = loader.getController();
        loginScene = new Scene(loginRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        loginController.set(easyDriv, stage, loginScene);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }

    public static void main(String[] args)  { launch(); }

}
