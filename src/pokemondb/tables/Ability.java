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
import javafx.beans.property.*;

public class Ability {

    public final String TABLE = "Abilities";
    private final SimpleIntegerProperty AbilityID;
    private final SimpleStringProperty AbilityName;
    private final SimpleStringProperty SpanishName;
    private final SimpleStringProperty AbilityDescription;

    public Ability() {
        this(1, "", "", "");
    }

    public Ability(int id, String an, String sn, String desc) {
        AbilityID = new SimpleIntegerProperty(id);
        AbilityName = new SimpleStringProperty(an);
        SpanishName = new SimpleStringProperty(sn);
        AbilityDescription = new SimpleStringProperty(desc);
    }

    /**
     * @return the AbilityID
     */
    public int getAbilityID() {
        return AbilityID.get();
    }
    
    /**
     * @return the AbilityID property
     */
    public SimpleIntegerProperty abilityIDProperty(){
        return AbilityID;
    }

    /**
     * @param id
     */
    public void setAbilityID(int id) {
        AbilityID.set(id);
    }

    /**
     * @return the AbilityName
     */
    public String getAbilityName() {
        return AbilityName.get();
    }
    
    /**
     * @return the AbilityName property
     */
    public SimpleStringProperty abilityNameProperty(){
        return AbilityName;
    }

    /**
     * @param name
     */
    public void setAbilityName(String name) {
        AbilityName.set(name);
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
    public SimpleStringProperty spanishNameProperty(){
        return SpanishName;
    }

    /**
     * @param sName
     */
    public void setSpanishName(String sName) {
        SpanishName.set(sName);
    }

    /**
     * @return the AbilityDescription
     */
    public String getAbilityDescription() {
        return AbilityDescription.get();
    }
    
    /**
     * @return the AbilityDescription property
     */
    public SimpleStringProperty abilityDescriptionProperty(){
        return AbilityDescription;
    }

    /**
     * @param description
     */
    public void setAbilityDescription(String description) {
        AbilityDescription.set(description);
    }

    /**
     * Returns the abilities table.
     *
     * @param condition the AbilityName, SpanishName or AbilityDescription.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Nombre, AbilityName AS "
                + "'Nombre (Inglés)', AbilityDescription AS Descripción, "
                + "AbilityID AS ID FROM "
                + TABLE + " WHERE AbilityName LIKE '%" + condition + "%' "
                + "OR SpanishName LIKE '%" + condition + "%' "
                + "OR AbilityDescription LIKE '%" + condition + "%'";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the Pokemon_Ability table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectPokemon() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT DexNumber AS '#', PokemonName AS Nombre, "
                + "Height AS 'Altura (m)', Weight AS 'Peso (kg)', "
                + "BaseHP AS PS, BaseAtk AS Ataque, BaseDef AS Defensa, "
                + "BaseSpAtk AS 'At. Especial', BaseSpDef AS 'Def. Especial', "
                + "BaseSpd AS Velocidad, PokemonDescription AS Descripción FROM "
                + "Pokemon A LEFT JOIN Pokemon_Abilities B ON A.PokemonID = "
                + "B.PokemonID WHERE B.AbilityID = " + AbilityID.get()
                + " ORDER BY DexNumber;";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }
}
