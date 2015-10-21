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

public class Type {

    public final String TABLE = "Types";
    private int TypeID;
    private String TypeName;
    private String SpanishName;
    private String TypeSprite;

    public Type(int id, String tn, String sn, String sprite) {
        TypeID = id;
        TypeName = tn;
        SpanishName = sn;
        TypeSprite = sprite;
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
     * @return the TypeName
     */
    public String getTypeName() {
        return TypeName;
    }

    /**
     * @param TypeName the TypeName to set
     */
    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
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
     * @return the TypeSprite
     */
    public String getTypeSprite() {
        return TypeSprite;
    }

    /**
     * @param TypeSprite the TypeSprite to set
     */
    public void setTypeSprite(String TypeSprite) {
        this.TypeSprite = TypeSprite;
    }

    /**
     * Returns the Type table.
     *
     * @param condition the TypeName of a Type or the SpanishName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAll(String condition) throws SQLException {
        return null;
    }
    
    /**
     * Returns the Attacks' Type table.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAttacks() throws SQLException{
        return null;
    }
    
    /**
     * Returns the Pokemon Type table.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectPokemon() throws SQLException{
        return null;
    }
    
    /**
     * Returns the Type Chart table.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectDamageTaken() throws SQLException{
        return null;
    }
}
