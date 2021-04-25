// import java.util.Scanner;

public class ImageConverter {
    public static void main(String[] args) throws Exception {

        // Scanner option = new Scanner(System.in);
        // System.out.println("");

        // ImageConverterAED image = new ImageConverterAED("./images/jeep_bw.jpg");
        // image.bwNegative("./images/jeep_neg.jpg");

        // ImageConverterAED image = new ImageConverterAED("./images/anaBW.jpg");
        // image.findContours("./images/ana_contour.jpg");

        ImageConverterAED image = new ImageConverterAED("./images/anaBW.jpg");
        image.blurImage("./images/ana_blur_kernel3.jpg", 3);

    }
}
