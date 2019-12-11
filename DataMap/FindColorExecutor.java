package DataMap;


import java.awt.*;

class FindColorExecutor {
    public static int countColor(Color color, DataMap dataMap){
        int count = 0;
        Color[][] colorMap = dataMap.getColorMap().clone();
        for (Color[] colors : colorMap) {
            for (int x = 0; x < colorMap[0].length; x++) {
                if (colors[x].equals(color)) {
                    count++;
                }
            }
        }
        return count;
    }
}
