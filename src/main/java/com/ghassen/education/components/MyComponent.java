package com.ghassen.education.components;

import com.ghassen.education.enums.Genre;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ghassen.mellassi on 12/22/2021
 */
public class MyComponent extends JComponent {
    private Map<Genre,Integer> data;
    int newWidth;
    int newHeight;
    String labelX="";
    String labelY="";
    Graphics2D g2;

    public void setValues(HashMap<Genre,Integer> data) {
        this.data = data;
        if(this.getGraphics()!=null) {
            update(this.getGraphics());
        }
    }

    public MyComponent(Map<Genre,Integer> data, String labelX, String labelY) {
        setPreferredSize(this.getSize());
        if(data==null){
            this.data=new HashMap<>();
        } else {
            this.data=data;
        }
        this.labelX=labelX;
        this.labelY=labelY;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.newWidth=getWidth()-10;
        this.newHeight=getHeight()-10;
        int totalNumber = data.values().stream().reduce(0, Integer::sum);


        this.g2 = (Graphics2D)g;
        Dimension d = getSize();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, d.width, d.height);

        int y0=(int) this.getSize().getHeight()-10;
        if(totalNumber!=0) {
            int i = 1;
            for (Map.Entry<Genre, Integer> entry : data.entrySet()) {
                g2.setColor(entry.getKey().getColorName());
                //(max DIV totalNumber) * homme or femme
                //y = positionYSurLeLigne - BarHeight
                int size = ((y0 - 50) / (totalNumber)) * entry.getValue();
                int y = y0 - size;
                g2.fillRect(i * (newWidth / 4), y, 50, size);

                g2.setColor(Color.white);
                g2.setFont (new Font("Arial", Font.PLAIN, 10));
                g2.drawString(entry.getKey().getGenreName(),i * (newWidth / 4),newHeight+8);

                i++;
            }
        }
        axesXY(totalNumber,labelX,labelY);

    }

    /**
     *
     * @param nbrPersonnes
     * @param labelX
     * @param labelY
     */
    public void axesXY(int nbrPersonnes,String labelX,String labelY){
        drawArrowLine(g2,10,newHeight,newWidth,newHeight,2,4);
        drawArrowLine(g2,10,newHeight,10,10, 4,6);

        g2.setColor(Color.RED);
        g2.setFont (new Font("Arial", Font.PLAIN, 15));
        g2.drawString(labelX,newWidth-50,newHeight-10);
        g2.drawString(labelY,15,15);

        g2.setColor(Color.YELLOW);
        g2.drawLine(5,50,15,50);
        g2.setFont (new Font("Arial", Font.PLAIN, 15));
        g2.drawString(String.valueOf(nbrPersonnes) ,20,50);
    }

    /**
     *
     * @param g Graphics pour dessigner
     * @param x1 point 1 du ligne
     * @param y1 point 1 du ligne
     * @param x2 point 2 du ligne
     * @param y2 point 2 du ligne
     * @param d diamétre du polygone
     * @param h hauteur du polygone
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g2.setColor(Color.GREEN);
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

}