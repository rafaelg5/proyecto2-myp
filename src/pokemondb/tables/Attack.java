/*
 * Copyright (C) 2015 Rafael de Jesús García García
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pokemondb.tables;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Attack {

    public final String TABLE = "Attacks";

    private final SimpleIntegerProperty AttackID;
    private final SimpleIntegerProperty Power;
    private final SimpleIntegerProperty Accuracy;
    private final SimpleObjectProperty<ImageView> TypeID;
    private final SimpleObjectProperty<ImageView> AtkCategoryID;
    private final SimpleStringProperty AttackName;
    private final SimpleStringProperty SpanishName;
    private final SimpleStringProperty AttackDescription;

    public Attack() throws SQLException {
        this(1, 0, 0, 0, 0, "", "", "");
    }

    public Attack(int id, int pow, int acc, int type, int cat, String name,
            String sname, String desc) throws SQLException {

        AttackID = new SimpleIntegerProperty(id);
        Power = new SimpleIntegerProperty(pow);
        Accuracy = new SimpleIntegerProperty(acc);

        ImageView iV1 = new ImageView(), iV2 = new ImageView();
        if (type != 0 && cat != 0) {
            Connection conn = ConnectionDB.open();
            ResultSet r1, r2;
            String sql1 = "SELECT TypeSprite FROM Types WHERE TypeID = " + type + ";";
            String sql2 = "SELECT CategorySprite FROM AtkCategory WHERE "
                    + "AtkCategoryID = " + cat + ";";
            r1 = conn.createStatement().executeQuery(sql1);
            r2 = conn.createStatement().executeQuery(sql2);
            Image img1, img2;            

            URL url1 = getClass()
                    .getResource("/pokemondb/graphicinterface/img/types/"
                            + r1.getString(1) + ".gif"),
                    url2 = getClass()
                    .getResource("/pokemondb/graphicinterface/img/categories/"
                            + r2.getString(1) + ".png");
            img1 = new Image(url1.toString());
            img2 = new Image(url2.toString());
            iV1 = new ImageView(img1);
            iV2 = new ImageView(img2);
        }

        TypeID = new SimpleObjectProperty<>(iV1);
        AtkCategoryID = new SimpleObjectProperty<>(iV2);
        AttackName = new SimpleStringProperty(name);
        SpanishName = new SimpleStringProperty(sname);
        AttackDescription = new SimpleStringProperty(desc);
    }

    /**
     * @return the AttackID
     */
    public int getAttackID() {
        return AttackID.get();
    }

    /**
     * @return the AttackID property
     */
    public SimpleIntegerProperty attackIDProperty() {
        return AttackID;
    }

    /**
     * @param atID the id to set
     */
    public void setAttackID(int atID) {
        AttackID.set(atID);
    }

    /**
     * @return the Power
     */
    public int getPower() {
        return Power.get();
    }

    /**
     * @return the Power property
     */
    public SimpleIntegerProperty powerProperty() {
        return Power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        Power.set(power);
    }

    /**
     * @return the Accuracy
     */
    public int getAccuracy() {
        return Accuracy.get();
    }

    /**
     * @return the Accuracy property
     */
    public SimpleIntegerProperty accuracyProperty() {
        return Accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(int accuracy) {
        Accuracy.set(accuracy);
    }

    /**
     * @return the TypeID
     */
    public ImageView getTypeID() {
        return TypeID.get();
    }

    /**
     * @return the TypeID property
     */
    public SimpleObjectProperty<ImageView> typeIDProperty() {
        return TypeID;
    }

    /**
     * @param tID the id to set
     * @throws java.sql.SQLException
     */
    public void setTypeID(int tID) throws SQLException {

        Connection conn = ConnectionDB.open();
        ResultSet r;
        String sql = "SELECT TypeSprite FROM Types WHERE TypeID = " + tID + ";";
        r = conn.createStatement().executeQuery(sql);

        Image img;
        ImageView iV;

        URL url = getClass()
                .getResource("/pokemondb/graphicinterface/img/"
                        + r.getString(0));

        img = new Image(url.toString());
        iV = new ImageView(img);

        TypeID.set(iV);
    }

    /**
     * @return the AtkCategoryID
     */
    public ImageView getAtkCategoryID() {
        return AtkCategoryID.get();
    }

    /**
     * @return the AtkCategoryID property
     */
    public SimpleObjectProperty<ImageView> atkCategoryIDProperty() {
        return AtkCategoryID;
    }

    /**
     * @param atkCatID the id to set
     * @throws java.sql.SQLException
     */
    public void setAtkCategoryID(int atkCatID) throws SQLException {
        Connection conn = ConnectionDB.open();
        ResultSet r;
        String sql = "SELECT CategorySprite FROM AtkCategory WHERE "
                + "AtkCategoryID = " + atkCatID + ";";
        r = conn.createStatement().executeQuery(sql);

        Image img;
        ImageView iV;

        URL url = getClass()
                .getResource("/pokemondb/graphicinterface/img/"
                        + r.getString(0));
        img = new Image(url.toString());
        iV = new ImageView(img);

        TypeID.set(iV);
    }

    /**
     * @return the AttackName
     */
    public String getAttackName() {
        return AttackName.get();
    }

    /**
     * @return the AttackName property
     */
    public SimpleStringProperty attackNameProperty() {
        return AttackName;
    }

    /**
     * @param name the name to set
     */
    public void setAttackName(String name) {
        AttackName.set(name);
    }

    /**
     * @return the SpanishName
     */
    public String getSpanishName() {
        return SpanishName.get();
    }

    /**
     * @return the SpanishName property
     */
    public SimpleStringProperty spanishNameProperty() {
        return SpanishName;
    }

    /**
     * @param sName the name to set
     */
    public void setSpanishName(String sName) {
        SpanishName.set(sName);
    }

    /**
     * @return the AttackDescription
     */
    public String getAttackDescription() {
        return AttackDescription.get();
    }

    /**
     * @return the AttackDescription property
     */
    public SimpleStringProperty attackDescriptionProperty() {
        return AttackDescription;
    }

    /**
     * @param description the description to set
     */
    public void setAttackDescription(String description) {
        AttackDescription.set(description);
    }

    /**
     * Returns the attacks table.
     *
     * @param condition the AttackName, SpanishName, AttackDescription, TypeID
     * or Power.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();

        String sql = "SELECT A.SpanishName AS Nombre, AttackName AS "
                + "'Nombre (Inglés)', Power AS Poder, Accuracy AS Precisión, "
                + "B.SpanishName AS Tipo, CategoryName AS Categoría, "
                + "AttackDescription AS Descripción, AttackID AS ID "
                + "FROM " + TABLE + " A LEFT JOIN Types B ON A.TypeID = "
                + "B.TypeID LEFT JOIN AtkCategory C ON A.AtkCategoryID = "
                + "C.AtkCategoryID WHERE AttackName LIKE '%" + condition
                + "%' OR A.SpanishName LIKE '%" + condition + "%' OR "
                + "AttackDescription LIKE '%" + condition + "%';";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

}
