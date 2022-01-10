package com.ghassen.education.service;

import com.ghassen.education.dao.entities.Personne;
import com.ghassen.education.enums.Genre;
import com.ghassen.education.view.MyList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ghassen.mellassi on 09/10/2022
 */
public class MyThread extends Thread {
    Graphics graphics;
    MyList myList;

    public MyThread(MyList myList){
        this.myList=myList;
    }

    public void run(){
        this.graphics= myList.myComponent.getGraphics();
        Timer timer = new Timer(1000, e -> historygramme(myList.model.getRowCount()));
            timer.setRepeats(true);
            timer.setDelay(1000);
            timer.start();
    }

    public void historygramme(int nbrPersonnes){
        List<Personne> personnes = new ArrayList<>();
        myList.myDataBase.remplirTab(myList.model);
        for (int i = 0; i < myList.model.getRowCount() ; i++) {
            personnes.add(new Personne(
                    (int) myList.model.getValueAt(i,0),
                    (String) myList.model.getValueAt(i,1),
                    Genre.valueOf(((String) myList.model.getValueAt(i,2)).toUpperCase()))
            );
        }

        int femmeNbr= (int) personnes.stream().filter(x -> x.getGenre()==Genre.FEMME).count();
        int hommeNbr= (int) personnes.stream().filter(x -> x.getGenre()==Genre.HOMME).count();

        HashMap<Genre,Integer> data = new HashMap<>();
        data.put(Genre.FEMME,femmeNbr);
        data.put(Genre.HOMME,hommeNbr);
        //MyComponent est dynamique vous pouvez ajouter plus de barres
        myList.myComponent.setValues(data);

        //Cette methode est commenté vue que si les axes ne sont pas
        //responsive a la resize de la fenetre donc elle deplacer vers la classe MyComponent
        //Apres des testes elle peut fonctionner mais sa sera mieux dans le composant
        /* axesXY(nbrPersonnes,"Genre","Nombre de personnes"); */

    }

    public void axesXY(int nbrPersonnes,String labelX,String labelY){
        drawArrowLine(graphics,10,myList.myComponent.getSize().height-10,550,myList.myComponent.getSize().height-10,2,4);
        drawArrowLine(graphics,10,myList.myComponent.getSize().height-10,10,10, 3,6);
        graphics.drawLine(5,50,15,50);

        graphics.setColor(Color.white);
        graphics.drawString(labelX,550,350);
        graphics.drawString(labelY,10,10);

        graphics.setColor(Color.white);
        graphics.setFont (new Font("Arial", Font.PLAIN, 15));
        graphics.drawString(String.valueOf(nbrPersonnes) ,20,50);
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
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

        g.setColor(Color.white);
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

}
