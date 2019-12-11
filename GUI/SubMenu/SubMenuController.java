package GUI.SubMenu;

import DataMap.DataMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

public interface SubMenuController {

    public AnchorPane getRootLayout();

    public void setRootLayout(AnchorPane rootLayout);

    public void setDataMap(DataMap dataMap);

    public void setHighlighted(ImageView imageView);

    @FXML
    void calculateEvent(ActionEvent event);

    void setHighlight(Boolean stat);
}
