import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.FileWriter;

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
        try {
            BufferedImage rotateLeftImage = new BufferedImage(image.getHeight(), image.getWidth(),
                    BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    rotateLeftImage.setRGB(i, j, image.getRGB((image.getWidth() - 1) - j, i));
                }
            }

            ImageIO.write(rotateLeftImage, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void rotateRight(String path) {
        try {
            BufferedImage rotateRightImage = new BufferedImage(image.getHeight(), image.getWidth(),
                    BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    rotateRightImage.setRGB(i, j, image.getRGB(j, (image.getHeight() - 1) - i));
                }
            }

            ImageIO.write(rotateRightImage, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void flipHorizontally(String path) {
        try {
            BufferedImage flipHImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    flipHImage.setRGB(j, i, image.getRGB((image.getWidth() - 1) - j, i));
                }
            }

            ImageIO.write(flipHImage, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void flipVertically(String path) {
        try {
            BufferedImage flipVImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    flipVImage.setRGB(j, i, image.getRGB(j, (image.getHeight() - 1) - i));
                }
            }

            ImageIO.write(flipVImage, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void createMargin(int margin, String path) {
        try {
            int width = image.getWidth() + (margin * 2);
            int height = image.getHeight() + (margin * 2);
            BufferedImage imageWithMargin = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i < margin || i > (image.getHeight() - 1 + margin) ||
                        j < margin || j > (image.getWidth() - 1 + margin)) {
                        imageWithMargin.setRGB(j, i, new Color(255, 255, 255).getRGB());
                    } else {
                        imageWithMargin.setRGB(j, i, image.getRGB(j - margin, i - margin));
                    }
                }
            }

            ImageIO.write(imageWithMargin, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void convertRGBColor(String path, char rgb) {
        try {
            BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color cor = new Color(image.getRGB(j, i));
                    switch (rgb) {
                        case 'r':
                            rgbImage.setRGB(j, i, getRed(cor).getRGB());
                        break;
                        case 'g':
                            rgbImage.setRGB(j, i, getGreen(cor).getRGB());
                        break;
                        case 'b':
                            rgbImage.setRGB(j, i, getBlue(cor).getRGB());
                        break;
                    }
                }
            }

            ImageIO.write(rgbImage, "jpg", new File(path));
            System.out.println("Image convert to B&W in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void bwCSVHistogram(String path) {
        try {
            FileWriter csvFile = new FileWriter(path);
            int histogram[] = new int[256];

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color pixelColor = new Color(image.getRGB(j, i));

                    histogram[pixelColor.getRed()] = histogram[pixelColor.getRed()] + 1;
                }
            }

            for (int i = 0; i < histogram.length; i++) {
                csvFile.write("" + i + "," + histogram[i] + "\n");
            }

            csvFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void bwBinarize(String path, int limit) {
        try {
            BufferedImage binarizeImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color color = new Color(image.getRGB(j, i));

                    if (color.getRed() < limit) {
                        binarizeImg.setRGB(j, i, new Color(255, 255, 255).getRGB());
                    } else {
                        binarizeImg.setRGB(j, i, new Color(0, 0, 0).getRGB());
                    }
                }
            }

            ImageIO.write(binarizeImg, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void bwNegative(String path) {
        try {
            BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color pixelColor = new Color(image.getRGB(j, i));
                    
                    int newPixelRGB = 255 - pixelColor.getRed();

                    Color newPixelColor = new Color(newPixelRGB, newPixelRGB, newPixelRGB);

                    bwImage.setRGB(j, i, newPixelColor.getRGB());
                }
            }

            ImageIO.write(bwImage, "jpg", new File(path));
            System.out.println("Negative image generated in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void findContours(String path) {
        try {
            BufferedImage imgContours = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            int Gx[][] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
            int Gy[][] = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
            int Mx[][] = new int[image.getHeight()][image.getWidth()];
            int My[][] = new int[image.getHeight()][image.getWidth()];
            int M[][] = new int[image.getHeight()][image.getWidth()];
            int pos[][][] = {{{-1, -1}, {-1, 0}, {-1, 1}}, {{0, -1}, {0, 0}, {0, 1}}, {{1, -1}, {1, 0}, {1, 1}}};
            int biggestGradient = 0;


            for (int r = 1; r < image.getHeight() - 1; r++) {
                for (int c = 1; c < image.getWidth() - 1; c++) {
                    for (int i = 0; i < pos.length; i++) {
                        for (int[] p : pos[i]) {
                            Mx[r + p[0]][c + p[1]] = new Color(image.getRGB(c + p[1], r + p[0])).getRed();
                        }
                    }

                    int tempMx = 0;
                    for (int i = 0; i < pos.length; i++) {
                        for (int j = 0; j < pos[0].length; j++) {
                            tempMx += Gx[i][j] * Mx[r + pos[i][j][0]][c + pos[i][j][1]];
                        }
                    }
                    Mx[r][c] = tempMx;
                    
                    for (int i = 0; i < pos.length; i++) {
                        for (int[] p : pos[i]) {
                            My[r + p[0]][c + p[1]] = new Color(image.getRGB(c + p[1], r + p[0])).getRed();
                        }
                    }

                    int tempMy = 0;
                    for (int i = 0; i < pos.length; i++) {
                        for (int j = 0; j < pos[0].length; j++) {
                            tempMy += Gy[i][j] * My[r + pos[i][j][0]][c + pos[i][j][1]];
                        }
                    }
                    My[r][c] = tempMy;
                    
                    M[r][c] = (int) (Math.sqrt(Math.pow(Mx[r][c], 2) + Math.pow(My[r][c], 2)));

                    if (M[r][c] > biggestGradient)
                        biggestGradient = M[r][c];
                }
            }
            
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    M[i][j] = (int) M[i][j] * 255 / biggestGradient;
                    Color color = new Color(M[i][j], M[i][j], M[i][j]);
                    imgContours.setRGB(j, i, color.getRGB());
                }
            }

            ImageIO.write(imgContours, "jpg", new File(path));
            System.out.println("Rotate right image in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToBW(String path) {
        try {
            BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color cor = new Color(image.getRGB(j, i));
                    bwImage.setRGB(j, i, getGray(cor).getRGB());
                }
            }

            ImageIO.write(bwImage, "jpg", new File(path));
            System.out.println("Image convert to B&W in " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Color getGray(Color color) {
        int r = (int) (color.getRed() * 0.3);
        int g = (int) (color.getGreen() * 0.59);
        int b = (int) (color.getBlue() * 0.11);
        int rgb = r + g + b;

        return new Color(rgb, rgb, rgb);
    }

    private Color getRed(Color color) {
        int r = color.getRed() * 1;
        int g = color.getGreen() * 0;
        int b = color.getBlue() * 0;

        return new Color(r, g, b);
    }

    private Color getGreen(Color color) {
        int r = color.getRed() * 0;
        int g = color.getGreen() * 1;
        int b = color.getBlue() * 0;

        return new Color(r, g, b);
    }

    private Color getBlue(Color color) {
        int r = color.getRed() * 0;
        int g = color.getGreen() * 0;
        int b = color.getBlue() * 1;

        return new Color(r, g, b);
    }
    
}
