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
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

public class TypeTest {

    private Connection conn;
    Random random;
    
    public TypeTest(){
        conn = ConnectionDB.open();
        random = new Random();
    }

    /**
     * Test of getTypeID method, of class Type.
     */
    @Test
    public void testGetTypeID(){
        Type type = new Type();
        int n = type.getTypeID();
        assertEquals(1, n);
    }

    /**
     * Test of setTypeID method, of class Type.
     */
    @Test
    public void testSetTypeID(){
        Type type = new Type();
        assertEquals(1, type.getTypeID());
        int n = random.nextInt();
        type.setTypeID(n);
        assertEquals(type.getTypeID(), n);
    }

    /**
     * Test of getTypeName method, of class Type.
     */
    @Test
    public void testGetTypeName(){
        Type type = new Type();
        String s = "";
        assertEquals(type.getTypeName(), s);
    }

    /**
     * Test of setTypeName method, of class Type.
     */
    @Test
    public void testSetTypeName(){
        Type type = new Type();
        assertEquals("", type.getTypeName());
        String s = "" + random.nextInt();
        System.out.println(s);
        type.setTypeName(s);
        assertEquals(type.getTypeName(), s);
    }

    /**
     * Test of getSpanishName method, of class Type.
     *
     */
    @Test
    public void testGetSpanishName(){
        Type type = new Type();
        String s = "";
        assertEquals(type.getSpanishName(), s);
    }

    /**
     * Test of setSpanishName method, of class Type.
     *
     */
    @Test
    public void testSetSpanishName(){
        Type type = new Type();
        assertEquals("", type.getSpanishName());
        String s = "" + random.nextInt();
        type.setSpanishName(s);
        assertEquals(type.getSpanishName(), s);
    }

    /**
     * Test of selectAll method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Type type = new Type();

        String test = "er";
        test = test.toLowerCase();
        ResultSet rs = type.selectAll(test);
        while (rs.next()) {
            assertTrue(rs.getString("Nombre").toLowerCase().contains(test)
                    || rs.getString("Nombre (Inglés)").toLowerCase()
                    .contains(test));
        }

        test = "lol";
        test = test.toLowerCase();
        ResultSet rs2 = type.selectAll(test);
        while (rs2.next()) {
            assertFalse(rs2.getString("Nombre").toLowerCase().contains(test)
                    || rs2.getString("Nombre (Inglés)").toLowerCase()
                    .contains(test));
        }
    }

    /**
     * Test of selectAttacks method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAttacks() throws SQLException {
        Type type = new Type();
        ResultSet rs = type.selectAttacks();

        String sql = "SELECT A.SpanishName AS Nombre, AttackName AS "
                + "'Nombre (Inglés)', Power AS Poder, Accuracy AS Precisión, "
                + "B.SpanishName AS Tipo, CategorySprite AS Categoría, "
                + "AttackDescription AS Descripción FROM Attacks A LEFT JOIN "
                + "Types B ON A.TypeID = B.TypeID LEFT JOIN AtkCategory C ON "
                + "A.AtkCategoryID = C.AtkCategoryID WHERE B.TypeID = "
                + type.getTypeID() + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Attacks A LEFT JOIN "
                + "Types B ON A.TypeID = B.TypeID LEFT JOIN AtkCategory C ON "
                + "A.AtkCategoryID = C.AtkCategoryID WHERE B.TypeID = "
                + type.getTypeID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = type.selectAttacks();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Nombre"), rs.getString("Nombre"));
            assertEquals(rs2.getString("Nombre (Inglés)"),
                    rs.getString("Nombre (Inglés)"));
            assertEquals(rs2.getString("Poder"), rs.getString("Poder"));
            assertEquals(rs2.getString("Precisión"), rs.getString("Precisión"));
            assertEquals(rs2.getString("Tipo"), rs.getString("Tipo"));
            assertEquals(rs2.getString("Categoría"), rs.getString("Categoría"));
            assertEquals(rs2.getString("Descripción"),
                    rs.getString("Descripción"));
        }
    }

    /**
     * Test of selectPokemon method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectPokemon() throws SQLException {
        Type type = new Type();
        ResultSet rs = type.selectPokemon();

        String sql = "SELECT DexNumber AS '#', PokemonName AS Nombre, "
                + "Height AS 'Altura (m)', Weight AS 'Peso (kg)', BaseHP AS PS, "
                + "BaseAtk AS Ataque, BaseDef AS Defensa, BaseSpAtk AS "
                + "'At. Especial', BaseSpDef AS 'Def. Especial', BaseSpd AS "
                + "Velocidad, PokemonDescription AS Descripción FROM Pokemon A "
                + "LEFT JOIN Pokemon_Types B ON A.PokemonID = B.PokemonID WHERE "
                + "B.TypeID = " + type.getTypeID() + " ORDER BY DexNumber;";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Pokemon A "
                + "LEFT JOIN Pokemon_Types B ON A.PokemonID = B.PokemonID WHERE "
                + "B.TypeID = " + type.getTypeID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = type.selectPokemon();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("#"), rs.getString("#"));
            assertEquals(rs2.getString("Nombre"), rs.getString("Nombre"));
            assertEquals(rs2.getString("Altura (m)"),
                    rs.getString("Altura (m)"));
            assertEquals(rs2.getString("Peso (kg)"), rs.getString("Peso (kg)"));
            assertEquals(rs2.getString("PS"), rs.getString("PS"));
            assertEquals(rs2.getString("Ataque"), rs.getString("Ataque"));
            assertEquals(rs2.getString("Defensa"), rs.getString("Defensa"));
            assertEquals(rs2.getString("At. Especial"),
                    rs.getString("At. Especial"));
            assertEquals(rs2.getString("Def. Especial"),
                    rs.getString("Def. Especial"));
            assertEquals(rs2.getString("Velocidad"), rs.getString("Velocidad"));
            assertEquals(rs2.getString("Descripción"),
                    rs.getString("Descripción"));
        }
    }

    /**
     * Test of selectDamageTaken method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectDamageTaken() throws SQLException {
        Type type = new Type();
        ResultSet rs = type.selectDamageTaken();

        String sql = "SELECT A.SpanishName AS Tipo, C.SpanishName AS "
                + "'Tipo Atacante', DamageTaken AS 'Daño Recibido' FROM Types A "
                + "LEFT JOIN TypesDamage B ON A.TypeID = B.TypeID LEFT JOIN "
                + "Types C ON B.AtkTypeID = C.TypeID WHERE B.TypeID = "
                + type.getTypeID() + ";";

        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Types A "
                + "LEFT JOIN TypesDamage B ON A.TypeID = B.TypeID LEFT JOIN "
                + "Types C ON B.AtkTypeID = C.TypeID WHERE B.TypeID = "
                + type.getTypeID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = type.selectDamageTaken();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Tipo"), rs.getString("Tipo"));
            assertEquals(rs2.getString("Tipo Atacante"),
                    rs.getString("Tipo Atacante"));
            assertEquals(rs2.getString("Daño Recibido"),
                    rs.getString("Daño Recibido"));
        }
    }

    /**
     * Test of typeIDProperty method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testTypeIDProperty() throws SQLException {
        Type type = new Type();
        Type type2 = new Type();

        assertFalse(type.typeIDProperty()
                .equals(type2.typeIDProperty()));

        type2 = type;
        assertTrue(type.typeIDProperty()
                .equals(type2.typeIDProperty()));
    }

    /**
     * Test of typeNameProperty method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testTypeNameProperty() throws SQLException {
        Type type = new Type();
        Type type2 = new Type();

        assertFalse(type.typeNameProperty()
                .equals(type2.typeNameProperty()));

        type2 = type;
        assertTrue(type.typeNameProperty()
                .equals(type2.typeNameProperty()));
    }

    /**
     * Test of spanishNameProperty method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSpanishNameProperty() throws SQLException {
        Type type = new Type();
        Type type2 = new Type();

        assertFalse(type.spanishNameProperty()
                .equals(type2.spanishNameProperty()));

        type2 = type;
        assertTrue(type.spanishNameProperty()
                .equals(type2.spanishNameProperty()));
    }

    /**
     * Test of typeSpriteProperty method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testTypeSpriteProperty() throws SQLException {
        Type type = new Type();
        Type type2 = new Type();

        assertFalse(type.typeSpriteProperty()
                .equals(type2.typeSpriteProperty()));

        type2 = type;
        assertTrue(type.typeSpriteProperty()
                .equals(type2.typeSpriteProperty()));
    }

}
