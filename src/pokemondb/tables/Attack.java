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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Attack {

    public final String TABLE = "Attacks";
    
    private final SimpleIntegerProperty AttackID;
    private final SimpleIntegerProperty Power;
    private final SimpleIntegerProperty Accuracy;
    private final SimpleIntegerProperty TypeID;
    private final SimpleIntegerProperty AtkCategoryID;
    private final SimpleStringProperty AttackName;
    private final SimpleStringProperty SpanishName;
    private final SimpleStringProperty AttackDescription;
    
    public Attack(){
        this(1, 0, 0, 1, 1, "", "", "");
    }

    public Attack(int id, int pow, int acc, int type, int cat, String name,
            String sname, String desc) {

        AttackID = new SimpleIntegerProperty(id);
        Power = new SimpleIntegerProperty(pow);
        Accuracy = new SimpleIntegerProperty(acc);
        TypeID = new SimpleIntegerProperty(type);
        AtkCategoryID = new SimpleIntegerProperty(cat);
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
     * @param atID
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
     * @param power
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
     * @param accuracy
     */
    public void setAccuracy(int accuracy) {
        Accuracy.set(accuracy);
    }

    /**
     * @return the TypeID
     */
    public int getTypeID() {
        return TypeID.get();
    }

    /**
     * @param tID
     */
    public void setTypeID(int tID) {
        TypeID.set(tID);
    }

    /**
     * @return the AtkCategoryID
     */
    public int getAtkCategoryID() {
        return AtkCategoryID.get();
    }

    /**
     * @param atkCatID
     */
    public void setAtkCategoryID(int atkCatID) {
        AtkCategoryID.set(atkCatID);
    }

    /**
     * @return the AttackName
     */
    public String getAttackName() {
        return AttackName.get();
    }

    /**
     * @param name
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
     * @param sName
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
     * @param description
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
     * @throws java.sql.SQLException
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT A.SpanishName AS Nombre, AttackName AS "
                + "'Nombre (Inglés)', Power AS Poder, Accuracy AS Precisión, "
                + "B.SpanishName AS Tipo, CategoryName AS Categoría, "
                + "AttackDescription AS Descripción FROM " + TABLE + " A "
                + "LEFT JOIN Types B ON A.TypeID = B.TypeID "
                + "LEFT JOIN AtkCategory C ON A.AtkCategoryID = C.AtkCategoryID "
                + "WHERE AttackName LIKE '%" + condition + "%' OR A.SpanishName "
                + "LIKE '%" + condition + "%' OR AttackDescription LIKE "
                + "'%" + condition + "%';";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

}
