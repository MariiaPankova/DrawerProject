package DataMap;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

class ConnectedAreasExecutor {
    private Color[][] colorMap;
    private Color transparent = new Color(0,0,0,0);

    private static class Node{
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Result maxConnectedArea(DataMap dataMap){
        colorMap = dataMap.getColorMap().clone();
        int count = 0;
        int max = 0;
        int tmp;
        for (int y=0; y<dataMap.getHeight(); y++){
            for (int x=0; x<dataMap.getWidth(); x++){
                if (!colorMap[y][x].equals(transparent) &&
                    !colorMap[y][x].equals(Color.BLACK) &&
                    !colorMap[y][x].equals(Color.GRAY)){
                    count++;
                    tmp = findConnected(x, y);
                    if (max < tmp){
                        max = tmp;
                        changeColor(Color.BLACK, transparent);
                        changeColor(Color.GRAY, Color.BLACK);
                    } else {
                        changeColor(Color.GRAY, transparent);
                    }

                }
            }
        }
        DataMap rs = new DataMap();
        dataMap.setColorMap(colorMap);
        rs.setWidth(dataMap.getWidth());
        rs.setHeight(dataMap.getHeight());
        return new Result(count, max, dataMap);
    }

    private void changeColor(Color from, Color to){
        for (int y=0; y<colorMap.length; y++){
            for (int x=0; x<colorMap[0].length; x++){
                if (colorMap[y][x].equals(from)){
                    colorMap[y][x] = to;
                }
            }
        }
    }

    private int findConnected(int x, int y)
    {
        int count=0;
        Color mainColor = colorMap[y][x];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        Node currentNode;
        colorMap[y][x] = Color.GRAY;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            boolean canUp = (currentNode.y - 1 >= 0);
            boolean canDown = (currentNode.y + 1 < colorMap.length);
            boolean canRight = (currentNode.x + 1 < colorMap[0].length);
            boolean canLeft = (currentNode.x - 1 >= 0);

            count++;
            if (canUp && colorMap[currentNode.y-1][currentNode.x].equals(mainColor))
            {
                colorMap[currentNode.y-1][currentNode.x] = Color.GRAY;
                queue.add(new Node(currentNode.x, currentNode.y-1));
            }
            if (canDown && colorMap[currentNode.y+1][currentNode.x].equals(mainColor))
            {
                colorMap[currentNode.y+1][currentNode.x] = Color.GRAY;
                queue.add(new Node(currentNode.x, currentNode.y+1));
            }
            if (canLeft && colorMap[currentNode.y][currentNode.x-1].equals(mainColor))
            {
                colorMap[currentNode.y][currentNode.x-1] = Color.GRAY;
                queue.add(new Node(currentNode.x-1, currentNode.y));
            }
            if (canRight && colorMap[currentNode.y][currentNode.x+1].equals(mainColor))
            {
                colorMap[currentNode.y][currentNode.x+1] = Color.GRAY;
                queue.add(new Node(currentNode.x+1, currentNode.y));
            }
        }
        return count;
    }

}
