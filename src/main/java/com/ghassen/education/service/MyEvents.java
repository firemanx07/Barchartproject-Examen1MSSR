package com.ghassen.education.service;

import com.ghassen.education.dao.repositories.MyDataBase;
import com.ghassen.education.dao.entities.Personne;
import com.ghassen.education.enums.Genre;
import com.ghassen.education.view.MyForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ghassen.mellassi on 09/10/2022
 */
public class MyEvents implements ActionListener {
    MyForm myForm;
    MyDataBase myDataBase;

    public MyEvents(MyForm myForm){
        this.myForm=myForm;
        this.myDataBase=new MyDataBase();
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource()==myForm.ajouter) {
            Personne personne = new Personne(myForm.nomText.getText(),null);
            if(myForm.femme.isSelected()){
                personne.setGenre(Genre.FEMME);
            } else {
                personne.setGenre(Genre.HOMME);
            }
            myDataBase.ajouter(personne);
        }
    }
}
