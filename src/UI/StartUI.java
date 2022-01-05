package UI;

import Logic.EasyDriv;
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
    Stage stage;

    //Parents
    private Parent loginRoot;

    LoginController loginController;

    @Override
    public void start(Stage primaryStage) throws IOException {

        easyDriv = new EasyDriv();

        stage = primaryStage;

        initializeRootsAndControllers();

        Scene scene = new Scene(loginRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

        //Image icone = new Image(Objects.requireNonNull(StartUI.class.getResourceAsStream("Resources/icone.png")));
        primaryStage.setScene(scene);

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
        loginController.setEasyDriv(easyDriv);
    }

    private FXMLLoader loaderFXML(String fxml) {
        return new FXMLLoader(StartUI.class.getResource("Resources/" + fxml + ".fxml"));
    }

    public static void main(String[] args)  { launch(); }

}
