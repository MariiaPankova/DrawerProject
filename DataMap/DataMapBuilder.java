package DataMap;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DataMapBuilder {
    private final static float minHue = 120f/255; //corresponds to green
    private final static float maxHue = 0; //corresponds to red


    static private Color int2ColMapper(int oldValue, int min, int max){
        float value = (float)(oldValue - min)/(float) (max - min);
        float hue = value*maxHue + (1-value)*minHue;
        return new Color(Color.HSBtoRGB(hue, 1, 0.5f));
    }

    public static DataMap fromIntFile(File file) throws FileNotFoundException {
        DataMap dm = new DataMap();
        Scanner scanner = new Scanner(file);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int min = scanner.nextInt();
        int max = scanner.nextInt();

        Color[][] colorMap = new Color[n][m];

        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                colorMap[i][j] = int2ColMapper(scanner.nextInt(), min, max);
            }
        }
        dm.setHeight(n);
        dm.setWidth(m);
        dm.setColorMap(colorMap);
        return dm;
    }

    public static DataMap fromColorMap(Color[][] colorMap){
        DataMap dm = new DataMap();
        dm.setColorMap(deepCopy(colorMap));
        dm.setHeight(colorMap.length);
        dm.setWidth(colorMap[0].length);
        return dm;
    }

    private static Color[][] deepCopy(Color[][] original) {
        if (original == null) {
            return null;
        }

        Color[][] result = new Color[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return result;
    }
}
