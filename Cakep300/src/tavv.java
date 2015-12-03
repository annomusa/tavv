
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import javax.swing.ImageIcon;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raffi
 */
public final class tavv extends javax.swing.JFrame {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    /**
     * Creates new form tavv
     */
    public tavv() {
        initComponents();
        
        Mat greyscale = Imgcodecs.imread("C:\\Users\\raffi\\Documents\\TA\\tavv\\imagemalam2\\SSPadalarang2.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat ori = Imgcodecs.imread("C:\\Users\\raffi\\Documents\\TA\\tavv\\imagemalam2\\SSPadalarang2.jpg");
        
        // grayscale
        jLabel1.setIcon(new ImageIcon(toBufferedImage(ori))); 
        jLabel2.setIcon(new ImageIcon(toBufferedImage(greyscale)));
        
        // top hat
        Mat kernel = Mat.ones(15, 15, CvType.CV_8UC1);
        Mat tophat = new Mat();
        Imgproc.morphologyEx(greyscale, tophat, Imgproc.MORPH_TOPHAT, kernel);
        jLabel3.setIcon(new ImageIcon(toBufferedImage(tophat)));
        
        // otsu + open
        Mat kernel2 = Mat.ones(5, 5, CvType.CV_8UC1);
        Mat otsu = new Mat();
        Imgproc.threshold(tophat, otsu, 0, 255, Imgproc.THRESH_OTSU);
        Imgproc.morphologyEx(otsu, otsu, Imgproc.MORPH_OPEN, kernel2);
        Imgproc.morphologyEx(otsu, otsu, Imgproc.MORPH_CLOSE, kernel2);
        jLabel4.setIcon(new ImageIcon(toBufferedImage(otsu)));
        
        // connected component
        Mat labelling = otsu;
        Mat stats = new Mat();
        Mat centroids = new Mat(); 
        Imgproc.connectedComponentsWithStats(otsu, labelling, stats, centroids);
//        System.out.println(centroids.dump());
//        System.out.println(stats.dump());
        
               
        // get data centroid
        int a1;
        centroids.convertTo(centroids, CvType.CV_32S);
        int size_centroids = (int)centroids.total() * centroids.channels();
        int[] data_centroids = new int[size_centroids];
        int[][] data_centroids2 = new int[size_centroids][2];
        int jlh_obj = 0;
        a1 = centroids.get(0, 0, data_centroids);
        System.out.println(a1);
        for(int i=0; i<data_centroids.length; i++){
            System.out.print(data_centroids[i] + " ");
            if(i%2==1) {
                data_centroids2[jlh_obj][1] = data_centroids[i]; 
                System.out.println();
                jlh_obj++;
            } else data_centroids2[jlh_obj][0] = data_centroids[i]; 
        }
        
        // get data stats
        // left_most; top_most; widht; height; area;
        int size_stats = (int)stats.total() * stats.channels();
        int[] data_stats = new int[size_stats];
        int[][] data_stats2 = new int[size_stats/5][size_stats];
        a1 = stats.get(0, 0, data_stats);
        System.out.println(a1);
        int y=0;
        for(int i=0; i<data_stats.length; i++){
            switch (i%5) {
                case 0:
                    System.out.print("x : " + data_stats[i] +" ");
                    data_stats2[y][0] = data_stats[i];
                    break;
                case 1:
                    System.out.print("y : " + data_stats[i] +" ");
                    data_stats2[y][1] = data_stats[i];
                    break;
                case 2:
                    System.out.print("w : " + data_stats[i] +" ");
                    data_stats2[y][2] = data_stats[i];
                    break;
                case 3:
                    System.out.print("h : " + data_stats[i] +" ");
                    data_stats2[y][3] = data_stats[i];
                    break;
                case 4:
                    System.out.println("a : " + data_stats[i] );
                    data_stats2[y][4] = data_stats[i];
                    y++;
                    break;
                default:
                    break;
            }
        }
        
        // gambar kotak
        for(int i=1;i<y;i++)
        Imgproc.rectangle(ori, new Point(data_stats2[i][0],data_stats2[i][1]), 
                new Point(data_stats2[i][0]+data_stats2[i][2],data_stats2[i][1]+data_stats2[i][3]),
                new Scalar(0, 255, 255), 2);
        jLabel5.setIcon(new ImageIcon(toBufferedImage(ori)));
        
        for(int i=1;i<jlh_obj;i++){
            for(int j=1;j<jlh_obj;j++){
                if(i==j) continue;
                if(Math.abs(data_centroids2[i][0] - data_centroids2[j][0]) < 50){
                    if(Math.abs(data_centroids2[j][1] - data_centroids2[i][1]) < 10){
                        Imgproc.rectangle(ori, new Point(data_stats2[i][0],data_stats2[i][1]), 
                            new Point(data_stats2[j][0]+data_stats2[j][2],data_stats2[j][1]+data_stats2[j][3]),
                            new Scalar(255, 0, 255), 2);
                    }
                }
            }
        }
        jLabel6.setIcon(new ImageIcon(toBufferedImage(ori)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tavv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tavv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tavv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tavv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new tavv().setVisible(true);
            }
        });
    }
    
    public Image toBufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels

        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
