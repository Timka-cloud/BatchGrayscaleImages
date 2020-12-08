package ForExcellentStudents;
import edu.duke.*;

import java.io.File;

public class GrayScaleConverter {
    // I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage){
        // I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // for each pixel in outImage
        for(Pixel pixel : outImage.pixels()){
            //Look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // compute inPixel's red + inPixel's blue + inPixel's green
            // divide the sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            // set pixel's red to average
            pixel.setRed(average);
            // set pixel's blue to average
            pixel.setGreen(average);
            // set pixel's green to average
            pixel.setBlue(average);
        }
        // outImage is your answer
        return outImage;
    }

    public void testGray(){
        // for choose the image
        ImageResource ir = new ImageResource();
        // then call the method and give this image
        ImageResource gray = makeGray(ir);
        //draws the image in a separate window
        gray.draw();
    } // it's for testing

    public void selectAndConvert(){   // select many images to convert to gray
        // DirectoryResource give us to choose many files
        DirectoryResource dr = new DirectoryResource();
        // Iterable files
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }

    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            // here we get in the string fname the name of image
            String fname = inImage.getFileName();
            // we create a new String newName to save tne name and plus word "copy-"
            String newName = "gray-" + fname;
            // and set the name
            gray.setFileName(newName);
            gray.draw();

            // and saved
            gray.save();

        }
    }

    public static void main(String[] args) {
        GrayScaleConverter gsc = new GrayScaleConverter();

        gsc.doSave();
    }
}
