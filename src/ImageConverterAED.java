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
                    Color color = new Color(image.getRGB(j, i));
                    switch (rgb) {
                        case 'r':
                            rgbImage.setRGB(j, i, new Color(color.getRed(), 0, 0).getRGB());
                        break;
                        case 'g':
                            rgbImage.setRGB(j, i, new Color(0, color.getGreen(), 0).getRGB());
                        break;
                        case 'b':
                            rgbImage.setRGB(j, i, new Color(0, 0, color.getBlue()).getRGB());
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

    public void blurImage(String path, int kernel) {
        try {            
            BufferedImage blurImage = new BufferedImage(image.getWidth() - (kernel - 1), image.getHeight() - (kernel - 1), BufferedImage.TYPE_INT_RGB);
            int kernelCrop = ((kernel - 1) / 2);

            for (int i = kernelCrop; i < image.getHeight() - kernelCrop; i++) {
                for (int j = kernelCrop; j < image.getWidth() - kernelCrop; j++) {
                    int sumR = 0;
                    int sumG = 0;
                    int sumB = 0;

                    for (int k = (i - kernelCrop); k <= (i + kernelCrop); k++) {
                        for (int l = (j - kernelCrop); l <= (j + kernelCrop); l++) {
                            sumR += new Color(image.getRGB(l, k)).getRed();
                            sumG += new Color(image.getRGB(l, k)).getGreen();
                            sumB += new Color(image.getRGB(l, k)).getBlue();
                        }
                    }

                    int avgR = (int) (sumR / Math.pow(kernel, 2));
                    int avgG = (int) (sumG / Math.pow(kernel, 2));
                    int avgB = (int) (sumB / Math.pow(kernel, 2));

                    blurImage.setRGB(j - kernelCrop, i - kernelCrop, new Color(avgR, avgG, avgB).getRGB());
                }
            }

            ImageIO.write(blurImage, "jpg", new File(path));
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
            int M[][] = new int[image.getHeight()][image.getWidth()];
            int biggestGradient = 0;


            for (int r = 1; r < image.getHeight() - 1; r++) {
                for (int c = 1; c < image.getWidth() - 1; c++) {
                    int tempX = 0;
                    int tempY = 0;

                    for (int k = (r - 1), i = 0; k <= (r + 1); k++, i++) {
                        for (int l = (c - 1), j = 0; l <= (c + 1); l++, j++) {
                            tempX += Gx[i][j] * new Color(image.getRGB(l, k)).getRed();
                            tempY += Gy[i][j] * new Color(image.getRGB(l, k)).getRed();
                        }
                    }
                    
                    M[r][c] = (int) (Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2)));

                    if (M[r][c] > biggestGradient)
                        biggestGradient = M[r][c];
                }
            }
            
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
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
}
