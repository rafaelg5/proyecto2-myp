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

public class Pokemon {

    public final String TABLE = "Pokemon";
    private final SimpleStringProperty PokemonName;
    private final SimpleStringProperty PokemonSprite;
    private final SimpleStringProperty PokemonDescription;
    private final SimpleIntegerProperty PokemonID;
    private final SimpleIntegerProperty DexNumber;
    private final SimpleIntegerProperty BaseHP;
    private final SimpleIntegerProperty BaseAtk;
    private final SimpleIntegerProperty BaseDef;
    private final SimpleIntegerProperty BaseSpAtk;
    private final SimpleIntegerProperty BaseSpDef;
    private final SimpleIntegerProperty BaseSpd;
    private final SimpleDoubleProperty Height;
    private final SimpleDoubleProperty Weight;

    public Pokemon() {
        this("", "", "", 1, 1, 0, 0, 0, 0, 0, 0, 0.0D, 0.0D);
    }

    public Pokemon(String name, String sprite, String desc, int id, int num,
            int hp, int atk, int def, int spAtk, int spDef, int spd,
            double height, double weight) {

        PokemonName = new SimpleStringProperty(name);
        PokemonSprite = new SimpleStringProperty(sprite);
        PokemonDescription = new SimpleStringProperty(desc);
        PokemonID = new SimpleIntegerProperty(id);
        DexNumber = new SimpleIntegerProperty(num);
        BaseHP = new SimpleIntegerProperty(hp);
        BaseAtk = new SimpleIntegerProperty(atk);
        BaseDef = new SimpleIntegerProperty(def);
        BaseSpAtk = new SimpleIntegerProperty(spAtk);
        BaseSpDef = new SimpleIntegerProperty(spDef);
        BaseSpd = new SimpleIntegerProperty(spd);
        Height = new SimpleDoubleProperty(height);
        Weight = new SimpleDoubleProperty(weight);
    }

    /**
     * @return the PokemonName
     */
    public String getPokemonName() {
        return PokemonName.get();
    }

    /**
     * @return the PokemonName property
     */
    public SimpleStringProperty pokemonNameProperty() {
        return PokemonName;
    }

    /**
     * @param name the name to set.
     */
    public void setPokemonName(String name) {
        PokemonName.set(name);
    }

    /**
     * @return the PokemonSprite
     */
    public String getPokemonSprite() {
        return PokemonSprite.get();
    }

    /**
     * @return the PokemonSprite property
     */
    public SimpleStringProperty pokemonSpriteProperty() {
        return PokemonSprite;
    }

    /**
     * @param sprite the sprite to set.
     */
    public void setPokemonSprite(String sprite) {
        PokemonSprite.set(sprite);
    }

    /**
     * @return the PokemonDescription
     */
    public String getPokemonDescription() {
        return PokemonDescription.get();
    }

    /**
     * @return the PokemonDescription property
     */
    public SimpleStringProperty pokemonDescriptionProperty() {
        return PokemonDescription;
    }

    /**
     * @param description the description to set.
     */
    public void setPokemonDescription(String description) {
        PokemonDescription.set(description);
    }

    /**
     * @return the PokemonID
     */
    public int getPokemonID() {
        return PokemonID.get();
    }

    /**
     * @return the PokemonID property
     */
    public SimpleIntegerProperty pokemonIDProperty() {
        return PokemonID;
    }

    /**
     * @param id the id to set.
     */
    public void setPokemonID(int id) {
        PokemonID.set(id);
    }

    /**
     * @return the DexNumber
     */
    public int getDexNumber() {
        return DexNumber.get();
    }

    /**
     * @return the DexNumber property
     */
    public SimpleIntegerProperty dexNumberProperty() {
        return DexNumber;
    }

    /**
     * @param num the number to set.
     */
    public void setDexNumber(int num) {
        DexNumber.set(num);
    }

    /**
     * @return the BaseHP
     */
    public int getBaseHP() {
        return BaseHP.get();
    }

    /**
     * @return the BaseHP property
     */
    public SimpleIntegerProperty baseHPProperty() {
        return BaseHP;
    }

    /**
     * @param hp the hp to set.
     */
    public void setBaseHP(int hp) {
        BaseHP.set(hp);
    }

    /**
     * @return the BaseAtk
     */
    public int getBaseAtk() {
        return BaseAtk.get();
    }

    /**
     * @return the BaseAtk property
     */
    public SimpleIntegerProperty baseAtkProperty() {
        return BaseAtk;
    }

    /**
     * @param atk the attack to set.
     */
    public void setBaseAtk(int atk) {
        BaseAtk.set(atk);
    }

    /**
     * @return the BaseDef
     */
    public int getBaseDef() {
        return BaseDef.get();
    }

    /**
     * @return the BaseDef property
     */
    public SimpleIntegerProperty baseDefProperty() {
        return BaseDef;
    }

    /**
     * @param def the defense to set.
     */
    public void setBaseDef(int def) {
        BaseDef.set(def);
    }

    /**
     * @return the BaseSpAtk
     */
    public int getBaseSpAtk() {
        return BaseSpAtk.get();
    }

    /**
     * @return the BaseSpAtk property
     */
    public SimpleIntegerProperty baseSpAtkProperty() {
        return BaseSpAtk;
    }

    /**
     * @param spAtk the sp. attack to set.
     */
    public void setBaseSpAtk(int spAtk) {
        BaseSpAtk.set(spAtk);
    }

    /**
     * @return the BaseSpDef
     */
    public int getBaseSpDef() {
        return BaseSpDef.get();
    }

    /**
     * @return the BaseSpDef property
     */
    public SimpleIntegerProperty baseSpDefProperty() {
        return BaseSpDef;
    }

    /**
     * @param spDef the sp. defense to set.
     */
    public void setBaseSpDef(int spDef) {
        BaseSpDef.set(spDef);
    }

    /**
     * @return the BaseSpd
     */
    public int getBaseSpd() {
        return BaseSpd.get();
    }

    /**
     * @return the BaseSpd property
     */
    public SimpleIntegerProperty baseSpdProperty() {
        return BaseSpd;
    }

    /**
     * @param spd the speed to set.
     */
    public void setBaseSpd(int spd) {
        BaseSpd.set(spd);
    }

    /**
     * @return the Height
     */
    public double getHeight() {
        return Height.get();
    }

    /**
     * @return the height property
     */
    public SimpleDoubleProperty heightProperty() {
        return Height;
    }

    /**
     * @param height the height to set.
     */
    public void setHeight(double height) {
        Height.set(height);
    }

    /**
     * @return the Weight
     */
    public double getWeight() {
        return Weight.get();
    }

    /**
     * @return the weight property
     */
    public SimpleDoubleProperty weightProperty() {
        return Weight;
    }

    /**
     * @param weight the weight to set.
     */
    public void setWeight(double weight) {
        Weight.set(weight);
    }

    /**
     * Returns the Pokemon table.
     *
     * @param condition the DexNumber of a Pokemon or the PokemonName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT PokemonID AS ID, PokemonSprite AS Sprite,"
                + "DexNumber AS '#', PokemonName AS Nombre, "
                + "Height AS Altura, Weight AS Peso, BaseHP AS PS, "
                + "BaseAtk AS Ataque, BaseDef AS Defensa, BaseSpAtk AS "
                + "'At. Especial', BaseSpDef AS 'Def. Especial', BaseSpd AS "
                + "Velocidad, PokemonDescription AS Descripción FROM " + TABLE
                + " WHERE DexNumber LIKE '%" + condition + "%' OR PokemonName "
                + "LIKE '%" + condition + "%';";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the a Pokemon's pre-evolution table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectPreevolutions() throws SQLException {

        Connection conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM " + TABLE + " A LEFT JOIN Preevolutions B ON "
                + "A.PokemonID = B.PreevolutionID WHERE B.PokemonID = "
                + PokemonID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the a Pokemon's evolution(s) table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectEvolutions() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM " + TABLE + " A LEFT JOIN Evolutions B ON "
                + "A.PokemonID = B.EvolutionID WHERE B.PokemonID = "
                + PokemonID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the a Pokemon's alternate form(s) table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAlternateForms() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM " + TABLE + " A LEFT JOIN AlternateForms B ON "
                + "A.PokemonID = B.AlternateFormID WHERE B.PokemonID = "
                + PokemonID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns a Pokemon's abilities table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAbilities() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Habilidad, AbilityDescription AS "
                + "Descripción FROM Abilities A LEFT JOIN "
                + "Pokemon_Abilities B ON A.AbilityID = B.AbilityID "
                + "WHERE B.PokemonID = " + PokemonID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns a Pokemon's type(s) table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectTypes() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Tipo, TypeSprite AS "
                + "Sprite FROM Types A LEFT JOIN "
                + "Pokemon_Types B ON A.TypeID = B.TypeID "
                + "WHERE B.PokemonID = " + PokemonID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

}
