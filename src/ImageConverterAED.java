import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

public class ImageConverterAED {
    private BufferedImage image;

    public ImageConverterAED(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rotateLeft(String path) {

    }

    public void flipHorizontally(String path) {

    }

    public void flipVertically(String path) {

    }

    public void createMargin(String path) {

    }

    public void convertRGBColor(String path) {

    }

    public void bwCSVHistogram(String path) {

    }

    public void binarizeBW(String path) {

    }

    public void negativeBW(String path) {
        
    }

    public void findContours(String path) {

    }
}
