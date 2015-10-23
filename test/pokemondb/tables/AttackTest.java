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

/**
 *
 * @author rgarcia
 */
public class AttackTest {

    private final Connection conn;
    private final Random random;

    public AttackTest() {
        conn = ConnectionDB.open();
        random = new Random();
    }

    /**
     * Test of getAttackID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackID() throws SQLException {
        Attack attack = new Attack();
        int n = attack.getAttackID();
        assertEquals(1, n);
    }

    /**
     * Test of setAttackID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackID() throws SQLException {
        Attack attack = new Attack();
        assertEquals(1, attack.getAttackID());
        int n = random.nextInt();
        attack.setAttackID(n);
        assertEquals(attack.getAttackID(), n);
    }

    /**
     * Test of getPower method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPower() throws SQLException {
        Attack attack = new Attack();
        int n = attack.getPower();
        assertEquals(0, n);
    }

    /**
     * Test of setPower method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPower() throws SQLException {
        Attack attack = new Attack();
        assertEquals(0, attack.getPower());
        int n = random.nextInt();
        attack.setPower(n);
        assertEquals(attack.getPower(), n);
    }

    /**
     * Test of getAccuracy method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAccuracy() throws SQLException {
        Attack attack = new Attack();
        int n = attack.getAccuracy();
        assertEquals(0, n);
    }

    /**
     * Test of setAccuracy method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAccuracy() throws SQLException {
        Attack attack = new Attack();
        assertEquals(0, attack.getAccuracy());
        int n = random.nextInt();
        attack.setAccuracy(n);
        assertEquals(attack.getAccuracy(), n);
    }

    /**
     * Test of getAttackName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackName() throws SQLException {
        Attack attack = new Attack();
        String s = attack.getAttackName();
        assertEquals("", s);
    }

    /**
     * Test of setAttackName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackName() throws SQLException {
        Attack attack = new Attack();
        assertEquals("", attack.getAttackName());
        String s = "" + random.nextInt();
        attack.setAttackName(s);
        assertEquals(attack.getAttackName(), s);
    }

    /**
     * Test of getSpanishName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetSpanishName() throws SQLException {
        Attack attack = new Attack();
        String s = attack.getSpanishName();
        assertEquals("", s);
    }

    /**
     * Test of setSpanishName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSpanishName() throws SQLException {
        Attack attack = new Attack();
        assertEquals("", attack.getSpanishName());
        String s = "" + random.nextInt();
        attack.setSpanishName(s);
        assertEquals(attack.getSpanishName(), s);
    }

    /**
     * Test of getAttackDescription method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackDescription() throws SQLException {
        Attack attack = new Attack();
        String s = attack.getAttackDescription();
        assertEquals("", s);
    }

    /**
     * Test of setAttackDescription method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackDescription() throws SQLException {
        Attack attack = new Attack();
        assertEquals("", attack.getAttackDescription());
        String s = "" + random.nextInt();
        attack.setAttackDescription(s);
        assertEquals(attack.getAttackDescription(), s);
    }

    /**
     * Test of selectAll method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Attack attack = new Attack();

        String test = "fire";
        test = test.toLowerCase();
        ResultSet rs = attack.selectAll(test);
        while (rs.next()) {
            assertTrue(rs.getString("Nombre").toLowerCase().
                    contains(test)
                    || rs.getString("Nombre (Inglés)").toLowerCase()
                    .contains(test)
                    || rs.getString("Descripción").toLowerCase()
                    .contains(test));
        }

        test = "lol";
        test = test.toLowerCase();
        ResultSet rs2 = attack.selectAll(test);
        while (rs2.next()) {
            assertFalse(rs2.getString("Nombre").toLowerCase()
                    .contains(test)
                    || rs2.getString("Nombre (Inglés)").toLowerCase()
                    .contains(test)
                    || rs2.getString("Descripción").toLowerCase()
                    .contains(test));
        }

        ResultSet rs3 = attack.selectAll("15");
        while (rs3.next()) {
            assertTrue(rs3.getString("Tipo").contains("15")
                    || rs3.getString("Poder").contains("15"));
        }

        ResultSet rs4 = attack.selectAll("800");
        while (rs4.next()) {
            assertFalse(rs4.getString("Tipo").contains("800")
                    || rs4.getString("Poder").contains("800"));
        }
    }

    /**
     * Test of attackIDProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAttackIDProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.attackIDProperty()
                .equals(attack2.attackIDProperty()));

        attack2 = attack;
        assertTrue(attack.attackIDProperty()
                .equals(attack2.attackIDProperty()));
    }

    /**
     * Test of powerProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testPowerProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.powerProperty()
                .equals(attack2.powerProperty()));

        attack2 = attack;
        assertTrue(attack.powerProperty()
                .equals(attack2.powerProperty()));
    }

    /**
     * Test of accuracyProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAccuracyProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.accuracyProperty()
                .equals(attack2.accuracyProperty()));

        attack2 = attack;
        assertTrue(attack.accuracyProperty()
                .equals(attack2.accuracyProperty()));
    }

    /**
     * Test of typeIDProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testTypeIDProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.typeIDProperty()
                .equals(attack2.typeIDProperty()));

        attack2 = attack;
        assertTrue(attack.typeIDProperty()
                .equals(attack2.typeIDProperty()));
    }

    /**
     * Test of atkCategoryIDProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAtkCategoryIDProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.atkCategoryIDProperty()
                .equals(attack2.atkCategoryIDProperty()));

        attack2 = attack;
        assertTrue(attack.atkCategoryIDProperty()
                .equals(attack2.atkCategoryIDProperty()));
    }

    /**
     * Test of attackNameProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAttackNameProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.attackNameProperty()
                .equals(attack2.attackNameProperty()));

        attack2 = attack;
        assertTrue(attack.attackNameProperty()
                .equals(attack2.attackNameProperty()));
    }

    /**
     * Test of spanishNameProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSpanishNameProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.spanishNameProperty()
                .equals(attack2.spanishNameProperty()));

        attack2 = attack;
        assertTrue(attack.spanishNameProperty()
                .equals(attack2.spanishNameProperty()));
    }

    /**
     * Test of attackDescriptionProperty method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testAttackDescriptionProperty() throws SQLException {
        Attack attack = new Attack();
        Attack attack2 = new Attack();

        assertFalse(attack.attackDescriptionProperty()
                .equals(attack2.attackDescriptionProperty()));

        attack2 = attack;
        assertTrue(attack.attackDescriptionProperty()
                .equals(attack2.attackDescriptionProperty()));
    }

}
