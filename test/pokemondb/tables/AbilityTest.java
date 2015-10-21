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
import org.junit.Test;
import static org.junit.Assert.*;

public class AbilityTest {

    private final int ID = 0;
    private final int NAME = 1;
    private final int S_NAME = 2;
    private final int DESCRIPTION = 3;

    private Connection conn;
    private Random rand;
    private String[] values;

    private Ability randomAbility() throws SQLException {
        ResultSet rs = getAbilityInfo();

        values = new String[rs.getMetaData().getColumnCount()];

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            values[i - 1] = rs.getString(i);
        }

        Ability ability = new Ability(Integer.parseInt(values[0]), values[1], values[2],
                values[3]);

        ConnectionDB.close();
        return ability;
    }

    private ResultSet getAbilityInfo() throws SQLException {
        conn = ConnectionDB.open();

        rand = new Random();
        int range = rand.nextInt(120) + 1;
        String sql = "SELECT * FROM Abilities WHERE AbilityID = " + range + ";";
        ResultSet rs;

        rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Test of getAbilityID method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAbilityID() throws SQLException {
        Ability ability = randomAbility();
        int n1 = Integer.parseInt(values[ID]),
                n2 = ability.getAbilityID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setAbilityID method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAbilityID() throws SQLException {
        Ability ability = randomAbility();
        assertEquals(ability.getAbilityID(), Integer.parseInt(values[ID]));
        ResultSet rs = getAbilityInfo();
        int n = rs.getInt(ID + 1);
        ability.setAbilityID(n);
        assertEquals(ability.getAbilityID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getAbilityName method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAbilityName() throws SQLException {
        Ability ability = randomAbility();
        String s1 = values[NAME],
                s2 = ability.getAbilityName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setAbilityName method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAbilityName() throws SQLException {
        Ability ability = randomAbility();
        assertEquals(ability.getAbilityName(), values[NAME]);

        ResultSet rs = getAbilityInfo();
        String s = rs.getString(NAME + 1);
        ability.setAbilityName(s);
        assertEquals(ability.getAbilityName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getSpanishName method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetSpanishName() throws SQLException {
        Ability ability = randomAbility();
        String s1 = values[S_NAME],
                s2 = ability.getSpanishName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setSpanishName method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSpanishName() throws SQLException {
        Ability ability = randomAbility();
        assertEquals(ability.getSpanishName(), values[S_NAME]);

        ResultSet rs = getAbilityInfo();
        String s = rs.getString(S_NAME + 1);
        ability.setSpanishName(s);
        assertEquals(ability.getSpanishName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getAbilityDescription method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAbilityDescription() throws SQLException {
        Ability ability = randomAbility();
        String s1 = values[DESCRIPTION],
                s2 = ability.getAbilityDescription();
        assertEquals(s1, s2);
    }

    /**
     * Test of setAbilityDescription method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAbilityDescription() throws SQLException {
        Ability ability = randomAbility();
        assertEquals(ability.getAbilityDescription(), values[DESCRIPTION]);

        ResultSet rs = getAbilityInfo();
        String s = rs.getString(DESCRIPTION + 1);
        ability.setAbilityDescription(s);
        assertEquals(ability.getAbilityDescription(), s);
        ConnectionDB.close();
    }

    /**
     * Test of selectAll method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Ability ability = randomAbility();
        ResultSet rs = ability.selectAll("water");
        while (rs.next()) {
            assertTrue(rs.getString("Nombre").contains("water")
                    || rs.getString("Nombre (Inglés)").contains("water")
                    || rs.getString("Descripción").contains("water"));
        }
        ResultSet rs2 = ability.selectAll("lol");
        while (rs2.next()) {
            assertFalse(rs2.getString("Nombre").contains("lol")
                    || rs2.getString("Nombre (Inglés)").contains("lol")
                    || rs2.getString("Descripción").contains("lol"));
        }
    }

    /**
     * Test of selectPokemon method, of class Ability.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectPokemon() throws SQLException {
        Ability ability = randomAbility();
        ResultSet rs = ability.selectPokemon();

        conn = ConnectionDB.open();
        String sql = "SELECT DexNumber AS '#', PokemonName AS Nombre, "
                + "Height AS 'Altura (m)', Weight AS 'Peso (kg)', "
                + "BaseHP AS PS, BaseAtk AS Ataque, BaseDef AS Defensa, "
                + "BaseSpAtk AS 'At. Especial', BaseSpDef AS 'Def. Especial', "
                + "BaseSpd AS Velocidad, PokemonDescription AS Descripción FROM "
                + "Pokemon A LEFT JOIN Pokemon_Abilities B ON A.PokemonID = "
                + "B.PokemonID WHERE B.TypeID = " + ability.getAbilityID()
                + " ORDER BY DexNumber;";

        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM "
                + "Pokemon A LEFT JOIN Pokemon_Abilities B ON A.PokemonID = "
                + "B.PokemonID WHERE B.TypeID = " + ability.getAbilityID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1)
                != count);
        rs = ability.selectPokemon();
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
}