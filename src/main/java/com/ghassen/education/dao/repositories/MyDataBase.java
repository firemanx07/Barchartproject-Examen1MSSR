package com.ghassen.education.dao.repositories;

import com.ghassen.education.dao.entities.Personne;
import com.ghassen.education.enums.Genre;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ghassen.mellassi on 11/17/2021
 */
public class MyDataBase {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String driver = "org.sqlite.JDBC";
    private String driverPrefix = "sqlite";
    private String host = this.getClass().getClassLoader().getResource("examen").getPath();
    private String user = "postgres";
    private String passwd = "postgres";

    public MyDataBase() {

    }

    public Connection connection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager
                .getConnection("jdbc:"+ driverPrefix +"://" + host);
    }

    public int ajouter(Personne personne){

        try {
            connect = connection();
            preparedStatement = connect
                    .prepareStatement("insert into utilisateurs(nom,genre) values(?,?)");
            preparedStatement.setString(1,personne.getNom());
            preparedStatement.setString(2,personne.getGenre().getGenreName());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return 1;
    }

    public void remplirTab(DefaultTableModel model){
        List<Personne> personnes = new ArrayList<>();
        model.getDataVector().removeAllElements();
        try {
            connect = connection();
            preparedStatement = connect
                    .prepareStatement("select * from utilisateurs");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String genre = resultSet.getString("genre");

                Personne personne = new Personne(id,nom,Genre.valueOf(genre.toUpperCase()));
                personnes.add(personne);
            }
            personnes.forEach(x-> model.addRow(new Object[]{x.getId(),x.getNom(),x.getGenre().getGenreName()}));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
