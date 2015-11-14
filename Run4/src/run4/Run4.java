/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run4;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
/**
 *
 * @author Asus
 */
public class Run4 {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println(mat.dump());
    }
    
}
