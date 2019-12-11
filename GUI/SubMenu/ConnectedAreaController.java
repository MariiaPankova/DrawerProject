package GUI.SubMenu;

import DataMap.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ConnectedAreaController implements SubMenuController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label connectedCounter;

    @FXML
    private Label maxArea;

    @FXML
    private CheckBox highlightBox;

    private AnchorPane rootLayout;

    private DataMap dataMap;

    private ImageView imageViewHighlight;

    @FXML
    public void calculateEvent(ActionEvent event) {
        Result result = dataMap.getConnectedAreas();
        this.connectedCounter.setText(String.valueOf(result.getCount()));
        this.maxArea.setText(String.valueOf(result.getMax()));
        this.imageViewHighlight.setImage(DataMapExporter.toWritableImage(result.getDm()));
        highlightBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                imageViewHighlight.setVisible(newValue);
            }
        });
    }

    @Override
    public void setHighlight(Boolean stat) {
        highlightBox.setSelected(stat);
    }

    @Override
    public AnchorPane getRootLayout() {
        return rootLayout;
    }

    @Override
    public void setRootLayout(AnchorPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    @Override
    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public void setHighlighted(ImageView imageView) {
        this.imageViewHighlight = imageView;
    }
}
