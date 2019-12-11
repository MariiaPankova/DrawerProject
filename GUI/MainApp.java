package GUI;

import DataMap.*;
import GUI.SubMenu.ConnectedAreaController;
import GUI.SubMenu.SubMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private VBox rootLayout;
    private Stage primaryStage;
    private MapDrawerController mainController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initRootLayout();
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MapDrawerGUI.fxml"));
            rootLayout = loader.load();
            mainController = loader.getController();
            mainController.setStage(primaryStage);
            mainController.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadSubMenu(DataMap.HighlightType highlightType){
        FXMLLoader loader = new FXMLLoader();
        SubMenuController subMenuController;
        AnchorPane root;
        try {
            switch (highlightType) {
                case CONNECTED_AREA:
                    loader.setLocation(MainApp.class.getResource("SubMenu/ConnectedArea.fxml"));
                    break;
                case ONE_COLOR:
                    loader.setLocation(MainApp.class.getResource("SubMenu/ColorCount.fxml"));
                    break;
            }
            root = loader.load();
            subMenuController = loader.getController();
            subMenuController.setRootLayout(root);
            mainController.setHighlight(false);
            mainController.setSubMenuController(subMenuController);
            mainController.refreshEvent(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
