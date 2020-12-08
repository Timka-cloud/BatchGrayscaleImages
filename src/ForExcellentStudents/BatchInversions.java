package ForExcellentStudents;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        int all = 255;
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = (all - inPixel.getRed());
            int green = (all - inPixel.getGreen());
            int blue = (all - inPixel.getBlue());
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);

        }
        return outImage;
    }

    public void selectManyAndInvertAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource is = new ImageResource(f);
            ImageResource invert = makeInversion(is);
            String fname = is.getFileName();
            String newName = "inverted-" + fname;
            invert.setFileName(newName);
            invert.draw();
            invert.save();

        }
    }



    public static void main(String[] args) {
        BatchInversions bi = new BatchInversions();
        bi.selectManyAndInvertAndSave();
    }
}
