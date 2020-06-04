package com.lyt.orc;

/**
 * @ClassName TestOcrUtils
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/3 16:42
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestOcrUtils {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
//        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/1.gif"));
//        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/3.jpg"));
//        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/3.png"));
        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/地摊.jpg"));
//        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/5.png"));
//        System.out.println(getImgText("C:/Users/Administrator/Desktop/图片/25.jpg"));
        Long end = System.currentTimeMillis();
        System.err.println("耗时：" + (end - start));
    }

    public static String  getImgText(String imageLocation) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:/IDEA/Tesseract-OCR/tessdata");
        try {
            instance.setLanguage("chi_sim");
            File imageFile = new File(imageLocation);
//			 String imgText = instance.doOCR();
            BufferedImage bi  = ImageIO.read(imageFile);
            return instance.doOCR(bi);
        } catch (TesseractException e) {
            e.getMessage();
            return "Error while reading image";
        }catch (IOException e) {
            e.getMessage();
            return "Error while reading image";
        }
    }

}
