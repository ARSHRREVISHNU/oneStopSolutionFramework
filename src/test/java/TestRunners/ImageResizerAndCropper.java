package TestRunners;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageResizerAndCropper {
    public static void main(String[] args) {
        try {
            // Load the input image
            File inputFile = new File("src/test/java/adyar.jpg"); // Replace with your image path
            BufferedImage inputImage = ImageIO.read(inputFile);

            System.out.println("Original Image Dimensions: " + inputImage.getWidth() + "x" + inputImage.getHeight());

            // Desired dimensions
            int targetWidth = 768;
            int targetHeight = 576;

            // Resize the input image while maintaining aspect ratio
            BufferedImage resizedImage = resizeImage(inputImage, targetWidth, targetHeight);
            System.out.println("Resized Image Dimensions: " + resizedImage.getWidth() + "x" + resizedImage.getHeight());

            // Crop the resized image to the exact target dimensions
            BufferedImage croppedImage = cropImageToTarget(resizedImage, targetWidth, targetHeight);
            System.out.println("Cropped Image Dimensions: " + croppedImage.getWidth() + "x" + croppedImage.getHeight());

            // Save the final cropped image
            File outputFile = new File("src/test/java/adyarCropped1.jpg"); // Replace with your desired output path
            ImageIO.write(croppedImage, "jpg", outputFile);

            System.out.println("Image successfully resized and cropped to 768x576!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to resize the image while maintaining aspect ratio
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        double aspectRatio = (double) width / height;

        // Calculate new dimensions while maintaining aspect ratio
        int newWidth = targetWidth;
        int newHeight = (int) (targetWidth / aspectRatio);

        if (newHeight < targetHeight) {
            newHeight = targetHeight;
            newWidth = (int) (targetHeight * aspectRatio);
        }

        System.out.println("New Dimensions for Resizing: " + newWidth + "x" + newHeight);

        // Create a resized image
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        return resizedBufferedImage;
    }

    // Method to crop the image to the exact target dimensions
    private static BufferedImage cropImageToTarget(BufferedImage image, int targetWidth, int targetHeight) {
        int x = (image.getWidth() - targetWidth) / 2;
        int y = (image.getHeight() - targetHeight) / 2;

        // Ensure cropping coordinates are valid
        if (x < 0) x = 0;
        if (y < 0) y = 0;

        System.out.println("Cropping Coordinates: x=" + x + ", y=" + y);

        return image.getSubimage(x, y, targetWidth, targetHeight);
    }
}
