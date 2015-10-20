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
import java.sql.Statement;

public class Pokemon {

    public final String TABLE = "Pokemon";
    private String PokemonName;
    private String PokemonSprite;
    private String PokemonDescription;
    private int PokemonID;
    private int DexNumber;
    private int BaseHP;
    private int BaseAtk;
    private int BaseDef;
    private int BaseSpAtk;
    private int BaseSpDef;
    private int BaseSpd;
    private int AlternateForm;
    private float Height;
    private float Weight;

    public Pokemon(String name, String sprite, String desc, int id, int num,
            int hp, int atk, int def, int spAtk, int spDef, int spd,
            int altForm, float height, float weight) {

        PokemonName = name;
        PokemonSprite = sprite;
        PokemonDescription = desc;
        PokemonID = id;
        DexNumber = num;
        BaseHP = hp;
        BaseAtk = atk;
        BaseDef = def;
        BaseSpAtk = spAtk;
        BaseSpDef = spDef;
        BaseSpd = spd;
        AlternateForm = altForm;
        Height = height;
        Weight = weight;
    }

    /**
     * @return the PokemonName
     */
    public String getPokemonName() {
        return PokemonName;
    }

    /**
     * @param PokemonName the PokemonName to set
     */
    public void setPokemonName(String PokemonName) {
        this.PokemonName = PokemonName;
    }

    /**
     * @return the PokemonSprite
     */
    public String getPokemonSprite() {
        return PokemonSprite;
    }

    /**
     * @param PokemonSprite the PokemonSprite to set
     */
    public void setPokemonSprite(String PokemonSprite) {
        this.PokemonSprite = PokemonSprite;
    }

    /**
     * @return the PokemonDescription
     */
    public String getPokemonDescription() {
        return PokemonDescription;
    }

    /**
     * @param PokemonDescription the PokemonDescription to set
     */
    public void setPokemonDescription(String PokemonDescription) {
        this.PokemonDescription = PokemonDescription;
    }

    /**
     * @return the PokemonID
     */
    public int getPokemonID() {
        return PokemonID;
    }

    /**
     * @param PokemonID the PokemonID to set
     */
    public void setPokemonID(int PokemonID) {
        this.PokemonID = PokemonID;
    }

    /**
     * @return the DexNumber
     */
    public int getDexNumber() {
        return DexNumber;
    }

    /**
     * @param DexNumber the DexNumber to set
     */
    public void setDexNumber(int DexNumber) {
        this.DexNumber = DexNumber;
    }

    /**
     * @return the BaseHP
     */
    public int getBaseHP() {
        return BaseHP;
    }

    /**
     * @param BaseHP the BaseHP to set
     */
    public void setBaseHP(int BaseHP) {
        this.BaseHP = BaseHP;
    }

    /**
     * @return the BaseAtk
     */
    public int getBaseAtk() {
        return BaseAtk;
    }

    /**
     * @param BaseAtk the BaseAtk to set
     */
    public void setBaseAtk(int BaseAtk) {
        this.BaseAtk = BaseAtk;
    }

    /**
     * @return the BaseDef
     */
    public int getBaseDef() {
        return BaseDef;
    }

    /**
     * @param BaseDef the BaseDef to set
     */
    public void setBaseDef(int BaseDef) {
        this.BaseDef = BaseDef;
    }

    /**
     * @return the BaseSpAtk
     */
    public int getBaseSpAtk() {
        return BaseSpAtk;
    }

    /**
     * @param BaseSpAtk the BaseSpAtk to set
     */
    public void setBaseSpAtk(int BaseSpAtk) {
        this.BaseSpAtk = BaseSpAtk;
    }

    /**
     * @return the BaseSpDef
     */
    public int getBaseSpDef() {
        return BaseSpDef;
    }

    /**
     * @param BaseSpDef the BaseSpDef to set
     */
    public void setBaseSpDef(int BaseSpDef) {
        this.BaseSpDef = BaseSpDef;
    }

    /**
     * @return the BaseSpd
     */
    public int getBaseSpd() {
        return BaseSpd;
    }

    /**
     * @param BaseSpd the BaseSpd to set
     */
    public void setBaseSpd(int BaseSpd) {
        this.BaseSpd = BaseSpd;
    }

    /**
     * @return 1, if the Pokemon is an alternate form. 0, otherwise.
     */
    public int getAlternateForm() {
        return AlternateForm;
    }

    /**
     * @param AlternateForm the AlternateForm to set
     */
    public void setAlternateForm(int AlternateForm) {
        this.AlternateForm = AlternateForm;
    }

    /**
     * @return the Height
     */
    public float getHeight() {
        return Height;
    }

    /**
     * @param Height the Height to set
     */
    public void setHeight(float Height) {
        this.Height = Height;
    }

    /**
     * @return the Weight
     */
    public float getWeight() {
        return Weight;
    }

    /**
     * @param Weight the Weight to set
     */
    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    /**
     * Returns the Pokemon table.
     *
     * @param condition the DexNumber of a Pokemon or the PokemonName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM " + TABLE + " WHERE DexNumber == '"
                + condition + "' OR PokemonName LIKE '%" + condition + "%';";
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
    
    /**
     * Returns the a Pokemon's evolutions table.
     *
     * @param condition the PokemonName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectEvolutions(String condition) throws SQLException {
        return null;
    }
    
    /**
     * Returns a Pokemon's abilities table.
     *
     * @param condition the PokemonName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectAbilities(String condition) throws SQLException {
        return null;
    }
    
    /**
     * Returns a Pokemon's type(s) table.
     *
     * @param condition the PokemonName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException
     */
    public ResultSet selectTypes(String condition) throws SQLException {
        return null;
    }

}
