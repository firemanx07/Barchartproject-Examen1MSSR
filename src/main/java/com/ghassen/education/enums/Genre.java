package com.ghassen.education.enums;

import java.awt.*;

/**
 * Created by ghassen.mellassi on 12/24/2021
 */
public enum Genre {
    FEMME("Femme", Color.pink),
    HOMME("Homme", Color.blue);

    private String genreName;
    private Color colorName;

    public String getGenreName() {
        return genreName;
    }

    public Color getColorName() {
        return colorName;
    }

    Genre(String genreName, Color colorName) {
        this.genreName = genreName;
        this.colorName = colorName;
    }
}
