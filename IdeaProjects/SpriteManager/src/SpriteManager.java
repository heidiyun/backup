import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpriteManager {
    private static HashMap<Integer, ArrayList<PImage>> images = new HashMap<>();
    private static PImage aniCharacter;

    public static void loadImage(PApplet pApplet, String fileName,
                                 int column, int row, int sizeX, int sizeY,
                                 int spriteCount, int state) throws IllegalAccessException {

        aniCharacter = pApplet.loadImage(fileName);

        int startX = column * sizeX;
        int startY = row * sizeY;

        List<PImage> pImages = new ArrayList<>();
        for (int i = 0; i < spriteCount; i++) {
            PImage character = aniCharacter.get(startX, startY, sizeX, sizeY);

            if (startX + sizeX > aniCharacter.width) {
                startX = column * sizeX;
                startY += sizeY;
            }

            startX += sizeX;

            if (startY + sizeY > aniCharacter.height) {
                throw new IllegalAccessException(" 이미지 범위를 벗어났습니다. ");
            }
            pImages.add(character);
        }


        images.put(state, (ArrayList<PImage>) pImages);

    }

    public static void loadImage(PApplet pApplet, String fileName,
                                 int column, int row,
                                 int sizeX, int sizeY,
                                 int spriteCount, int state, boolean isLooping) throws IllegalAccessException {

        PImage aniCharacter = pApplet.loadImage(fileName);
        int startX = column * sizeX;
        int startY = row * sizeY;
        int loopingCount = spriteCount - 2;

        List<PImage> pImages = new ArrayList<>();
        if (isLooping) {
            for (int i = 0; i < spriteCount + loopingCount; i++) {

                PImage character = aniCharacter.get(startX, startY, sizeX, sizeY);

                if (i >= spriteCount - loopingCount) {
                    startX -= sizeX;
                } else
                    startX += sizeX;

                if (startX + sizeX > aniCharacter.width) {
                    startX = column * sizeX;
                    startY += sizeY;
                }


                if (startY + sizeY > aniCharacter.height) {
                    throw new IllegalAccessException(" 이미지 범위를 벗어났습니다. ");
                }
                pImages.add(character);
            }

            images.put(state, (ArrayList<PImage>) pImages);
        }

    }

    public static void loadImage(PApplet pApplet, String fileName,
                                 int sizeX, int sizeY,
                                 int state, int[] indices) throws IllegalAccessException {

        PImage aniCharacter = pApplet.loadImage(fileName);
        int countX = aniCharacter.width / sizeX;

        List<PImage> pImages = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            int startX = (indices[i] % countX) * sizeX;
            int startY = (indices[i] / countX) * sizeY;
            PImage character = aniCharacter.get(startX, startY, sizeX, sizeY);

            pImages.add(character);
        }

        images.put(state, (ArrayList<PImage>) pImages);


    }

    public static ArrayList<PImage> getImages(int state) {
        return images.get(state);
    }

    public static PImage getImage(int state, int index) {
        ArrayList<PImage> pImages = images.get(state);
        index %= pImages.size();
        return pImages.get(index);
    }
}
