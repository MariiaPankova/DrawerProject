package DataMap;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class DataMapExporter {

    public static BufferedImage toBufferedImage(DataMap dataMap){
        BufferedImage bf = new BufferedImage(dataMap.getWidth(), dataMap.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for (int y=0; y<dataMap.getHeight(); y++){
            for (int x=0; x<dataMap.getWidth(); x++){
                bf.setRGB(x, y, dataMap.getColorMap()[y][x].getRGB());
            }
        }
        return bf;
    }

    public static WritableImage toWritableImage(DataMap dataMap){

        WritableImage wr = new WritableImage(dataMap.getWidth(), dataMap.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < dataMap.getWidth(); x++) {
            for (int y = 0; y < dataMap.getHeight(); y++) {
                pw.setArgb(x, y, dataMap.getColorMap()[y][x].getRGB());
            }
        }
        return wr;
    }
}
