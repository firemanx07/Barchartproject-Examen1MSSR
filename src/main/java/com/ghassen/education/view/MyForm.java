package com.ghassen.education.view;

import com.ghassen.education.service.MyEvents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ghassen.mellassi on 09/10/2022
 */
public class MyForm extends JFrame {

    JLabel titleLabel=new JLabel("Examen");
    JLabel nomLabel=new JLabel("Nom :");
    JLabel genreLabel=new JLabel("Genre :");
    public JTextField nomText=new JTextField();
    public JButton ajouter=new JButton("Ajouter");
    JButton list=new JButton("Lister");
    public JRadioButton femme= new JRadioButton("Femme");
    public JRadioButton homme= new JRadioButton("Homme");
    ButtonGroup radioGroup=new ButtonGroup();

    MyEvents event;

    public MyForm(){
        super("titleLabel");
        this.event=new MyEvents(this);
        ajouter.addActionListener(event);
        list.addActionListener(e -> new MyList());
        homme.setToolTipText("This RadioButton is enteded to select User gender as Male ");
        femme.setToolTipText("This RadioButton is enteded to select User gender as Female ");
        radioGroup.add(homme);
        radioGroup.add(femme);
        homme.setSelected(true);

        JPanel titlePanel=new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        Border borderWithTitle = BorderFactory.createTitledBorder("Informations");

        JPanel panelFormulaire=new JPanel();
        panelFormulaire.setLayout(new GridLayout(2,2));
        panelFormulaire.setBorder(borderWithTitle);

        JPanel radioPannel= new JPanel(new GridLayout(1,2));
        radioPannel.add(homme);
        radioPannel.add(femme);

        panelFormulaire.add(nomLabel);
        panelFormulaire.add(nomText);
        panelFormulaire.add(genreLabel);
        panelFormulaire.add(radioPannel);

        JPanel panelBouttons=new JPanel();
        panelBouttons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        list.setToolTipText("This button will open a statistics page");
        panelBouttons.add(list);
        ajouter.setToolTipText("This button will add the user with the information entered");
        panelBouttons.add(ajouter);

        JPanel panelAssemblage=new JPanel();
        panelAssemblage.setLayout(new BorderLayout());
        panelAssemblage.add("Center",panelFormulaire);
        panelAssemblage.add("South",panelBouttons);

        Container c=getContentPane();
        c.setLayout(new BorderLayout());
        c.add("North",titlePanel);
        c.add("South",panelBouttons);
        c.add("Center",panelFormulaire);

        setSize(500,175);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]){
        new MyForm();
    }
}
