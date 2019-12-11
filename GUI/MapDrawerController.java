package GUI;

import DataMap.DataMap;
import DataMap.DataMapBuilder;
import DataMap.DataMapExporter;
import GUI.SubMenu.SubMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapDrawerController {

    @FXML
    private VBox root;

    @FXML
    private MenuItem openMenu;

    @FXML
    private ImageView imageHighlight;

    @FXML
    private MenuItem saveAsMenu;

    @FXML
    private ChoiceBox<DataMap.HighlightType> choiceBox;

    @FXML
    private ImageView imageView;

    @FXML
    private Button refreshButton;

    @FXML
    private BorderPane sideMenuRoot;

    private Stage stage;
    private MainApp mainApp;
    private DataMap dataMap;
    private SubMenuController subMenuController;


    @FXML
    public void initialize() {
        ObservableList<DataMap.HighlightType> highlightTypes =
                FXCollections.observableArrayList( DataMap.HighlightType.values());
        ChangeListener<DataMap.HighlightType> changeListener = new ChangeListener<DataMap.HighlightType>() {
            @Override
            public void changed(ObservableValue<? extends DataMap.HighlightType> observableValue,
                                DataMap.HighlightType highlightType, DataMap.HighlightType t1) {
                mainApp.loadSubMenu(t1);

                subMenuController.setHighlighted(imageHighlight);
                sideMenuRoot.setCenter(subMenuController.getRootLayout());
            }
        };
        choiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
        choiceBox.setItems(highlightTypes);
    }


    @FXML
    void openMenuEvent(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Map File");
        File file = fileChooser.showOpenDialog(stage);
        try {
            this.dataMap = DataMapBuilder.fromIntFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        refreshButton.setDisable(false);
        refreshEvent(null);
    }

    @FXML
    void refreshEvent(ActionEvent event) {
        setHighlight(false);
        if (dataMap!=null) {
            imageView.setImage(DataMapExporter.toWritableImage(dataMap));
        }
        if (subMenuController != null) {
            subMenuController.setDataMap(dataMap.copy());
        }
    }

    void setHighlight(Boolean state){
        if (subMenuController!=null) {
            subMenuController.setHighlight(false);
        }
    }

    @FXML
    void saveAsMenu(ActionEvent event) {
        if (dataMap != null) {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    ImageIO.write(DataMapExporter.toBufferedImage(this.dataMap), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSubMenuController(SubMenuController subMenuController) {
        this.subMenuController = subMenuController;
    }
}
