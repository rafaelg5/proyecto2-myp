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
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonTest {

    private final int NAME = 0;
    private final int SPRITE = 1;
    private final int DESCRIPTION = 2;
    private final int ID = 3;
    private final int NUM_DEX = 4;
    private final int HP = 5;
    private final int ATK = 6;
    private final int DEF = 7;
    private final int SP_ATK = 8;
    private final int SP_DEF = 9;
    private final int SPEED = 10;
    private final int ALT_FORM = 11;
    private final int HEIGHT = 12;
    private final int WEIGHT = 13;

    private Connection conn;
    private Random rand;
    private String[] values;

    private Pokemon randomPoke() throws SQLException {
        ResultSet rs = getPokemonInfo();

        values = new String[rs.getMetaData().getColumnCount()];

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            values[i - 1] = rs.getString(i);
        }

        Pokemon pokemon = new Pokemon(values[0], values[1], values[2],
                Integer.parseInt(values[3]), Integer.parseInt(values[4]),
                Integer.parseInt(values[5]), Integer.parseInt(values[6]),
                Integer.parseInt(values[7]), Integer.parseInt(values[8]),
                Integer.parseInt(values[9]), Integer.parseInt(values[10]),
                Integer.parseInt(values[11]), Double.parseDouble(values[12]),
                Double.parseDouble(values[13]));

        ConnectionDB.close();
        return pokemon;
    }

    private ResultSet getPokemonInfo() throws SQLException {
        conn = ConnectionDB.open();

        rand = new Random();
        int range = rand.nextInt(171) + 1;
        String sql = "SELECT PokemonName, PokemonSprite, PokemonDescription,"
                + "PokemonID, DexNumber, BaseHP, BaseAtk, BaseDef, BaseSpAtk,"
                + "BaseSpDef, BaseSpd, AlternateForm, Height, Weight"
                + " FROM Pokemon WHERE PokemonID = " + range + ";";
        ResultSet rs;

        rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Test of getPokemonName method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPokemonName() throws SQLException {
        Pokemon pokemon = randomPoke();
        String s1 = values[NAME],
                s2 = pokemon.getPokemonName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setPokemonName method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPokemonName() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getPokemonName(), values[NAME]);

        ResultSet rs = getPokemonInfo();
        String s = rs.getString(NAME + 1);
        pokemon.setPokemonName(s);
        assertEquals(pokemon.getPokemonName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getPokemonSprite method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPokemonSprite() throws SQLException {
        Pokemon pokemon = randomPoke();
        String s1 = values[SPRITE],
                s2 = pokemon.getPokemonSprite();
        assertEquals(s1, s2);
    }

    /**
     * Test of setPokemonSprite method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPokemonSprite() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getPokemonSprite(), values[SPRITE]);

        ResultSet rs = getPokemonInfo();
        String s = rs.getString(SPRITE + 1);
        pokemon.setPokemonSprite(s);
        assertEquals(pokemon.getPokemonSprite(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getPokemonDescription method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPokemonDescription() throws SQLException {
        Pokemon pokemon = randomPoke();
        String s1 = values[DESCRIPTION],
                s2 = pokemon.getPokemonDescription();
        assertEquals(s1, s2);
    }

    /**
     * Test of setPokemonDescription method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPokemonDescription() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getPokemonDescription(), values[DESCRIPTION]);

        ResultSet rs = getPokemonInfo();
        String s = rs.getString(DESCRIPTION + 1);
        pokemon.setPokemonDescription(s);
        assertEquals(pokemon.getPokemonDescription(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getPokemonID method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPokemonID() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[ID]),
                n2 = pokemon.getPokemonID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setPokemonID method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPokemonID() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getPokemonID(), Integer.parseInt(values[ID]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(ID + 1);
        pokemon.setPokemonID(n);
        assertEquals(pokemon.getPokemonID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getDexNumber method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetDexNumber() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[NUM_DEX]),
                n2 = pokemon.getDexNumber();
        assertEquals(n1, n2);
    }

    /**
     * Test of setDexNumber method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetDexNumber() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getDexNumber(), Integer.parseInt(values[NUM_DEX]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(NUM_DEX + 1);
        pokemon.setDexNumber(n);
        assertEquals(pokemon.getDexNumber(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseHP method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseHP() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[HP]),
                n2 = pokemon.getBaseHP();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseHP method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseHP() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseHP(), Integer.parseInt(values[HP]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(HP + 1);
        pokemon.setBaseHP(n);
        assertEquals(pokemon.getBaseHP(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseAtk method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseAtk() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[ATK]),
                n2 = pokemon.getBaseAtk();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseAtk method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseAtk() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseAtk(), Integer.parseInt(values[ATK]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(ATK + 1);
        pokemon.setBaseAtk(n);
        assertEquals(pokemon.getBaseAtk(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseDef method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseDef() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[DEF]),
                n2 = pokemon.getBaseDef();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseDef method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseDef() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseDef(), Integer.parseInt(values[DEF]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(DEF + 1);
        pokemon.setBaseDef(n);
        assertEquals(pokemon.getBaseDef(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseSpAtk method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseSpAtk() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[SP_ATK]),
                n2 = pokemon.getBaseSpAtk();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseSpAtk method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseSpAtk() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseSpAtk(), Integer.parseInt(values[SP_ATK]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(SP_ATK + 1);
        pokemon.setBaseSpAtk(n);
        assertEquals(pokemon.getBaseSpAtk(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseSpDef method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseSpDef() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[SP_DEF]),
                n2 = pokemon.getBaseSpDef();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseSpDef method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseSpDef() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseSpDef(), Integer.parseInt(values[SP_DEF]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(SP_DEF + 1);
        pokemon.setBaseSpDef(n);
        assertEquals(pokemon.getBaseSpDef(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getBaseSpd method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetBaseSpd() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[SPEED]),
                n2 = pokemon.getBaseSpd();
        assertEquals(n1, n2);
    }

    /**
     * Test of setBaseSpd method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetBaseSpd() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getBaseSpd(), Integer.parseInt(values[SPEED]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(SPEED + 1);
        pokemon.setBaseSpd(n);
        assertEquals(pokemon.getBaseSpd(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getAlternateForm method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAlternateForm() throws SQLException {
        Pokemon pokemon = randomPoke();
        int n1 = Integer.parseInt(values[ALT_FORM]),
                n2 = pokemon.getAlternateForm();
        assertEquals(n1, n2);
    }

    /**
     * Test of setAlternateForm method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAlternateForm() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertEquals(pokemon.getAlternateForm(),
                Integer.parseInt(values[ALT_FORM]));
        ResultSet rs = getPokemonInfo();
        int n = rs.getInt(ALT_FORM + 1);
        pokemon.setAlternateForm(n);
        assertEquals(pokemon.getAlternateForm(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getHeight method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetHeight() throws SQLException {
        Pokemon pokemon = randomPoke();
        double n1 = Double.parseDouble(values[HEIGHT]),
                n2 = pokemon.getHeight();
        assertTrue(n1 == n2);
    }

    /**
     * Test of setHeight method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetHeight() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertTrue(pokemon.getHeight() == Double.parseDouble(values[HEIGHT]));
        ResultSet rs = getPokemonInfo();
        double n = rs.getDouble(HEIGHT + 1);
        pokemon.setHeight(n);
        assertTrue(pokemon.getHeight() == n);
        ConnectionDB.close();
    }

    /**
     * Test of getWeight method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetWeight() throws SQLException {
        Pokemon pokemon = randomPoke();
        double n1 = Double.parseDouble(values[WEIGHT]),
                n2 = pokemon.getWeight();
        assertTrue(n1 == n2);
    }

    /**
     * Test of setWeight method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetWeight() throws SQLException {
        Pokemon pokemon = randomPoke();
        assertTrue(pokemon.getWeight() == Double.parseDouble(values[WEIGHT]));
        ResultSet rs = getPokemonInfo();
        double n = rs.getDouble(WEIGHT + 1);
        pokemon.setWeight(n);
        assertTrue(pokemon.getWeight() == n);
        ConnectionDB.close();
    }

    /**
     * Test of selectAll method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Pokemon pokemon = randomPoke();
        int num = rand.nextInt(171) + 1;
        String n = "" + num;
        ResultSet rs = pokemon.selectAll("" + num);
        while (rs.next()) {
            assertTrue(rs.getString("#").contains(n));
        }
        ResultSet rs2 = pokemon.selectAll("saur");
        while (rs2.next()) {
            assertTrue(rs2.getString("Nombre").contains("saur"));
        }
    }

    /**
     * Test of selectPreevolutions method,
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectPreevolutions() throws SQLException {
        Pokemon pokemon = randomPoke();
        ResultSet rs = pokemon.selectPreevolutions();

        conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM Pokemon A LEFT JOIN Preevolutions B ON A.PokemonID = "
                + "B.PreevolutionID WHERE B.PokemonID = "
                + pokemon.getPokemonID() + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Pokemon A LEFT JOIN "
                + "Preevolutions B ON A.PokemonID = B.PreevolutionID WHERE "
                + "B.PokemonID = " + pokemon.getPokemonID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = pokemon.selectPreevolutions();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Nombre"), rs.getString("Nombre"));
            assertEquals(rs2.getString("Sprite"), rs.getString("Sprite"));
        }
    }

    /**
     * Test of selectEvolutions method,
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectEvolutions() throws SQLException {
        Pokemon pokemon = randomPoke();
        ResultSet rs = pokemon.selectEvolutions();

        conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM Pokemon A LEFT JOIN Evolutions B ON A.PokemonID = "
                + "B.EvolutionID WHERE B.PokemonID = " + pokemon.getPokemonID()
                + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Pokemon A LEFT JOIN "
                + "Evolutions B ON A.PokemonID = B.EvolutionID WHERE "
                + "B.PokemonID = " + pokemon.getPokemonID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = pokemon.selectEvolutions();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Nombre"), rs.getString("Nombre"));
            assertEquals(rs2.getString("Sprite"), rs.getString("Sprite"));
        }

    }

    /**
     * Test of selectAlternateForms method,
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAlternateForms() throws SQLException {
        Pokemon pokemon = randomPoke();
        ResultSet rs = pokemon.selectAlternateForms();

        conn = ConnectionDB.open();
        String sql = "SELECT PokemonName AS Nombre, PokemonSprite AS Sprite "
                + "FROM Pokemon A LEFT JOIN AlternateForms B ON A.PokemonID = "
                + "B.AlternateFormID WHERE B.PokemonID = "
                + pokemon.getPokemonID() + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Pokemon A LEFT JOIN "
                + "AlternateForms B ON A.PokemonID = B.AlternateFormID WHERE "
                + "B.PokemonID = " + pokemon.getPokemonID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = pokemon.selectAlternateForms();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Nombre"), rs.getString("Nombre"));
            assertEquals(rs2.getString("Sprite"), rs.getString("Sprite"));
        }
    }

    /**
     * Test of selectAbilities method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAbilities() throws SQLException {
        Pokemon pokemon = randomPoke();
        ResultSet rs = pokemon.selectAbilities();

        conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Habilidad, AbilityDescription AS "
                + "Descripción FROM Abilities A LEFT JOIN "
                + "Pokemon_Abilities B ON A.AbilityID = B.AbilityID "
                + "WHERE B.PokemonID = " + pokemon.getPokemonID() + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);

        String sql2 = "SELECT count(*) FROM Abilities A LEFT JOIN "
                + "Pokemon_Abilities B ON A.AbilityID = B.AbilityID WHERE "
                + "B.PokemonID = " + pokemon.getPokemonID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = pokemon.selectAbilities();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Habilidad"), rs.getString("Habilidad"));
            assertEquals(rs2.getString("Descripción"),
                    rs.getString("Descripción"));
        }
    }

    /**
     * Test of selectTypes method, of class Pokemon.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectTypes() throws SQLException {
        Pokemon pokemon = randomPoke();
        ResultSet rs = pokemon.selectTypes();

        conn = ConnectionDB.open();
        String sql = "SELECT SpanishName AS Tipo, TypeSprite AS Sprite "
                + "FROM Types A LEFT JOIN Pokemon_Types B ON A.TypeID = "
                + "B.TypeID WHERE B.PokemonID = " 
                + pokemon.getPokemonID() + ";";
        ResultSet rs2 = conn.createStatement().executeQuery(sql);
        
        String sql2 = "SELECT count(*) FROM Types A LEFT JOIN Pokemon_Types B "
                + "ON A.TypeID = B.TypeID WHERE B.PokemonID = " 
                + pokemon.getPokemonID() + ";";

        int count = 0;
        while (rs.next()) {
            count++;
        }

        assertFalse(conn.createStatement().executeQuery(sql2).getInt(1) != count);
        rs = pokemon.selectTypes();

        for (int i = 0; i < count; i++) {
            rs.next();
            rs2.next();
            assertEquals(rs2.getString("Tipo"), rs.getString("Tipo"));
            assertEquals(rs2.getString("Sprite"), rs.getString("Sprite"));
        }
    }

}
