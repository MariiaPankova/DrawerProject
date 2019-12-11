package GUI.SubMenu;

import DataMap.DataMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ColorCountController implements SubMenuController{

    @FXML
    private AnchorPane root;

    @FXML
    private CheckBox highlightBox;

    @FXML
    private Button calculateButton;

    @FXML
    private Label colorCount;

    @FXML
    private ColorPicker colorPicker;


    private AnchorPane rootLayout;

    private DataMap dataMap;

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

    }

    @FXML
    public void calculateEvent(ActionEvent event) {
        Color fx = this.colorPicker.getValue();
        int count = this.dataMap.getColorCount(
                new java.awt.Color((float) fx.getRed(),
                                   (float) fx.getGreen(),
                                   (float) fx.getBlue(),
                                   (float) fx.getOpacity()));
        this.colorCount.setText(String.valueOf(count));
    }

    @Override
    public void setHighlight(Boolean stat) {
        //unnecessary for this menu
    }

}