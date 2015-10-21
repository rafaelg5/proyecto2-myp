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

import java.sql.ResultSet;
import java.sql.SQLException;

public class Attack {
    
    public final String TABLE = "Attacks";
    private int AttackID;
    private int Power;
    private int Accuracy;
    private int TypeID;
    private int AtkCategoryID;
    private String AttackName;
    private String SpanishName;
    private String AttackDescription;    
    
    public Attack(int id, int pow, int acc, int type, int cat, String name,
            String sname, String desc){
        
        AttackID = id;
        Power = pow;
        Accuracy = acc;
        TypeID = type;
        AtkCategoryID = cat;
        AttackName = name;
        SpanishName = sname;
        AttackDescription = desc;
    }

    /**
     * @return the AttackID
     */
    public int getAttackID() {
        return AttackID;
    }

    /**
     * @param AttackID the AttackID to set
     */
    public void setAttackID(int AttackID) {
        this.AttackID = AttackID;
    }

    /**
     * @return the Power
     */
    public int getPower() {
        return Power;
    }

    /**
     * @param Power the Power to set
     */
    public void setPower(int Power) {
        this.Power = Power;
    }

    /**
     * @return the Accuracy
     */
    public int getAccuracy() {
        return Accuracy;
    }

    /**
     * @param Accuracy the Accuracy to set
     */
    public void setAccuracy(int Accuracy) {
        this.Accuracy = Accuracy;
    }

    /**
     * @return the TypeID
     */
    public int getTypeID() {
        return TypeID;
    }

    /**
     * @param TypeID the TypeID to set
     */
    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    /**
     * @return the AtkCategoryID
     */
    public int getAtkCategoryID() {
        return AtkCategoryID;
    }

    /**
     * @param AtkCategoryID the AtkCategoryID to set
     */
    public void setAtkCategoryID(int AtkCategoryID) {
        this.AtkCategoryID = AtkCategoryID;
    }

    /**
     * @return the AttackName
     */
    public String getAttackName() {
        return AttackName;
    }

    /**
     * @param AttackName the AttackName to set
     */
    public void setAttackName(String AttackName) {
        this.AttackName = AttackName;
    }

    /**
     * @return the SpanishName
     */
    public String getSpanishName() {
        return SpanishName;
    }

    /**
     * @param SpanishName the SpanishName to set
     */
    public void setSpanishName(String SpanishName) {
        this.SpanishName = SpanishName;
    }

    /**
     * @return the AttackDescription
     */
    public String getAttackDescription() {
        return AttackDescription;
    }

    /**
     * @param AttackDescription the AttackDescription to set
     */
    public void setAttackDescription(String AttackDescription) {
        this.AttackDescription = AttackDescription;
    }
    
    /**
     * Returns the attacks table.
     *
     * @param condition the AttackName, SpanishName, AttackDescription, 
     * TypeID or Power.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAll(String condition) throws SQLException {
        return null;
    }    
    
}
