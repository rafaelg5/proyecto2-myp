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

public class Type {

    public final String TABLE = "Types";
    private final SimpleIntegerProperty TypeID;
    private final SimpleStringProperty TypeName;
    private final SimpleStringProperty SpanishName;
    private final SimpleObjectProperty<ImageView> TypeSprite;

    public Type() {
        this(1, "", "", "");
    }

    public Type(int id, String tn, String sn, String sprite) {

        TypeID = new SimpleIntegerProperty(id);
        TypeName = new SimpleStringProperty(tn);
        SpanishName = new SimpleStringProperty(sn);

        Image img;
        ImageView i;
        if (sprite.trim().equals("")) {
            i = new ImageView();
        } else {
            URL url = getClass()
                    .getResource("/pokemondb/graphicinterface/img/types/" + 
                            sprite);
            img = new Image(url.toString());
            i = new ImageView(img);
        }
        TypeSprite = new SimpleObjectProperty<>(i);
    }

    /**
     * @return the TypeID
     */
    public int getTypeID() {
        return TypeID.get();
    }

    /**
     * @return the TypeID property
     */
    public SimpleIntegerProperty typeIDProperty() {
        return TypeID;
    }

    /**
     * @param id the TypeID to set
     */
    public void setTypeID(int id) {
        TypeID.set(id);
    }

    /**
     * @return the TypeName
     */
    public String getTypeName() {
        return TypeName.get();
    }

    /**
     * @return the TypeName property
     */
    public SimpleStringProperty typeNameProperty() {
        return TypeName;
    }

    /**
     * @param name the TypeName to set
     */
    public void setTypeName(String name) {
        TypeName.set(name);
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
     * @param sName the SpanishName to set
     */
    public void setSpanishName(String sName) {
        SpanishName.set(sName);
    }

    /**
     * @return the TypeSprite
     */
    public ImageView getTypeSprite() {
        return TypeSprite.get();
    }

    /**
     * @return the TypeSprite property
     */
    public SimpleObjectProperty<ImageView> typeSpriteProperty() {
        return TypeSprite;
    }

    /**
     * @param sprite the TypeSprite to set
     */
    public void setTypeSprite(String sprite) {
        Image img;
        if (sprite.trim().equals("")) {
            img = null;
        } else {

            img = new Image(getClass()
                    .getResource("/pokemondb/graphicinterface/img/" + sprite).
                    toString());
        }
        TypeSprite.set(new ImageView(img));
    }

    /**
     * Returns the Type table.
     *
     * @param condition the TypeName of a Type or the SpanishName.
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAll(String condition) throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Nombre, TypeName AS "
                + "'Nombre (Inglés)', TypeSprite AS Ícono, TypeID AS ID FROM "
                + TABLE + " WHERE TypeName LIKE '%" + condition + "%' "
                + "OR SpanishName LIKE '%" + condition + "%'";

        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the Attacks' Type table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectAttacks() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT A.SpanishName AS Nombre, AttackName AS "
                + "'Nombre (Inglés)', Power AS Poder, Accuracy AS Precisión, "
                + "B.SpanishName AS Tipo, CategorySprite AS Categoría, "
                + "AttackDescription AS Descripción FROM Attacks A LEFT JOIN "
                + "Types B ON A.TypeID = B.TypeID LEFT JOIN AtkCategory C ON "
                + "A.AtkCategoryID = C.AtkCategoryID WHERE B.TypeID = "
                + TypeID.get() + ";";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the Pokemon_Type table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectPokemon() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT DexNumber AS '#', PokemonName AS Nombre, "
                + "Height AS 'Altura (m)', Weight AS 'Peso (kg)', BaseHP AS PS, "
                + "BaseAtk AS Ataque, BaseDef AS Defensa, BaseSpAtk AS "
                + "'At. Especial', BaseSpDef AS 'Def. Especial', BaseSpd AS "
                + "Velocidad, PokemonDescription AS Descripción FROM Pokemon A "
                + "LEFT JOIN Pokemon_Types B ON A.PokemonID = B.PokemonID WHERE "
                + "B.TypeID = " + TypeID.get() + " ORDER BY DexNumber;";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Returns the Type Chart table.
     *
     * @return the table as a ResultSet.
     * @throws java.sql.SQLException if a database access error occurs
     */
    public ResultSet selectDamageTaken() throws SQLException {
        Connection conn = ConnectionDB.open();
        String sql = "SELECT A.SpanishName AS Tipo, C.SpanishName AS "
                + "'Tipo Atacante', DamageTaken AS 'Daño Recibido' FROM "
                + TABLE + " A LEFT JOIN TypesDamage B ON A.TypeID = B.TypeID "
                + "LEFT JOIN " + TABLE + " C ON B.AtkTypeID = C.TypeID;";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        return rs;
    }
}
