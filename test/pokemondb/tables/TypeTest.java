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

    private final int ID = 0;
    private final int NAME = 1;
    private final int S_NAME = 2;
    private final int SPRITE = 3;

    private Connection conn;
    private Random rand;
    private String[] values;

    private Type randomType() throws SQLException {
        ResultSet rs = getTypeInfo();

        values = new String[rs.getMetaData().getColumnCount()];

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            values[i - 1] = rs.getString(i);
        }

        Type type = new Type(Integer.parseInt(values[0]), values[1], values[2],
                values[3]);

        ConnectionDB.close();
        return type;
    }

    private ResultSet getTypeInfo() throws SQLException {
        conn = ConnectionDB.open();

        rand = new Random();
        int range = rand.nextInt(18) + 1;
        String sql = "SELECT * FROM Types WHERE TypeID = " + range + ";";
        ResultSet rs;

        rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Test of getTypeID method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTypeID() throws SQLException {
        Type type = randomType();
        int n1 = Integer.parseInt(values[ID]),
                n2 = type.getTypeID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setTypeID method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetTypeID() throws SQLException {
        Type type = randomType();
        assertEquals(type.getTypeID(), Integer.parseInt(values[ID]));
        ResultSet rs = getTypeInfo();
        int n = rs.getInt(ID + 1);
        type.setTypeID(n);
        assertEquals(type.getTypeID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getTypeName method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTypeName() throws SQLException {
        Type type = randomType();
        String s1 = values[NAME],
                s2 = type.getTypeName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setTypeName method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetTypeName() throws SQLException {
        Type type = randomType();
        assertEquals(type.getTypeName(), values[NAME]);

        ResultSet rs = getTypeInfo();
        String s = rs.getString(NAME + 1);
        type.setTypeName(s);
        assertEquals(type.getTypeName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getSpanishName method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetSpanishName() throws SQLException {
        Type type = randomType();
        String s1 = values[S_NAME],
                s2 = type.getSpanishName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setSpanishName method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSpanishName() throws SQLException {
        Type type = randomType();
        assertEquals(type.getSpanishName(), values[S_NAME]);

        ResultSet rs = getTypeInfo();
        String s = rs.getString(S_NAME + 1);
        type.setSpanishName(s);
        assertEquals(type.getSpanishName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getTypeSprite method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTypeSprite() throws SQLException {
        Type type = randomType();
        String s1 = values[SPRITE],
                s2 = type.getTypeSprite();
        assertEquals(s1, s2);
    }

    /**
     * Test of setTypeSprite method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetTypeSprite() throws SQLException {
        Type type = randomType();
        assertEquals(type.getTypeSprite(), values[SPRITE]);

        ResultSet rs = getTypeInfo();
        String s = rs.getString(SPRITE + 1);
        type.setTypeSprite(s);
        assertEquals(type.getTypeSprite(), s);
        ConnectionDB.close();
    }

    /**
     * Test of selectAll method, of class Type.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Type type = randomType();

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
        Type type = randomType();
        ResultSet rs = type.selectAttacks();

        conn = ConnectionDB.open();
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
        Type type = randomType();
        ResultSet rs = type.selectPokemon();

        conn = ConnectionDB.open();
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
        Type type = randomType();
        ResultSet rs = type.selectDamageTaken();

        conn = ConnectionDB.open();
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

}
