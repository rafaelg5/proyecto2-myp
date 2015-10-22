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

    private final int ID = 0;
    private final int POWER = 1;
    private final int ACCURACY = 2;
    private final int TYPE = 3;
    private final int CATEGORY = 4;
    private final int NAME = 5;
    private final int S_NAME = 6;
    private final int DESCRIPTION = 7;

    private Connection conn;
    private Random rand;
    private String[] values;

    private Attack randomAttack() throws SQLException {
        ResultSet rs = getAttackInfo();

        values = new String[rs.getMetaData().getColumnCount()];

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            values[i - 1] = rs.getString(i);
        }

        Attack attack = new Attack(Integer.parseInt(values[0]),
                Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                Integer.parseInt(values[3]), Integer.parseInt(values[4]),
                values[5], values[6], values[7]);

        ConnectionDB.close();
        return attack;
    }

    private ResultSet getAttackInfo() throws SQLException {
        conn = ConnectionDB.open();

        rand = new Random();
        int range = rand.nextInt(620) + 1;
        String sql = "SELECT AttackID, Power, Accuracy, TypeID, AtkCategoryID, "
                + "AttackName, SpanishName, AttackDescription FROM Attacks "
                + "WHERE AttackID = " + range + ";";
        ResultSet rs;

        rs = conn.createStatement().executeQuery(sql);
        return rs;
    }

    /**
     * Test of getAttackID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackID() throws SQLException {
        Attack attack = randomAttack();
        int n1 = Integer.parseInt(values[ID]),
                n2 = attack.getAttackID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setAttackID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackID() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getAttackID(), Integer.parseInt(values[ID]));
        ResultSet rs = getAttackInfo();
        int n = rs.getInt(ID + 1);
        attack.setAttackID(n);
        assertEquals(attack.getAttackID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getPower method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetPower() throws SQLException {
        Attack attack = randomAttack();
        int n1 = Integer.parseInt(values[POWER]),
                n2 = attack.getPower();
        assertEquals(n1, n2);
    }

    /**
     * Test of setPower method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPower() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getPower(), Integer.parseInt(values[POWER]));
        ResultSet rs = getAttackInfo();
        int n = rs.getInt(POWER + 1);
        attack.setPower(n);
        assertEquals(attack.getPower(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getAccuracy method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAccuracy() throws SQLException {
        Attack attack = randomAttack();
        int n1 = Integer.parseInt(values[ACCURACY]),
                n2 = attack.getAccuracy();
        assertEquals(n1, n2);
    }

    /**
     * Test of setAccuracy method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAccuracy() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getAccuracy(), Integer.parseInt(values[ACCURACY]));
        ResultSet rs = getAttackInfo();
        int n = rs.getInt(ACCURACY + 1);
        attack.setAccuracy(n);
        assertEquals(attack.getAccuracy(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getTypeID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetTypeID() throws SQLException {
        Attack attack = randomAttack();
        int n1 = Integer.parseInt(values[TYPE]),
                n2 = attack.getTypeID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setTypeID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetTypeID() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getTypeID(), Integer.parseInt(values[TYPE]));
        ResultSet rs = getAttackInfo();
        int n = rs.getInt(TYPE + 1);
        attack.setTypeID(n);
        assertEquals(attack.getTypeID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getAtkCategoryID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAtkCategoryID() throws SQLException {
        Attack attack = randomAttack();
        int n1 = Integer.parseInt(values[CATEGORY]),
                n2 = attack.getAtkCategoryID();
        assertEquals(n1, n2);
    }

    /**
     * Test of setAtkCategoryID method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAtkCategoryID() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getAtkCategoryID(),
                Integer.parseInt(values[CATEGORY]));
        ResultSet rs = getAttackInfo();
        int n = rs.getInt(CATEGORY + 1);
        attack.setAtkCategoryID(n);
        assertEquals(attack.getAtkCategoryID(), n);
        ConnectionDB.close();
    }

    /**
     * Test of getAttackName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackName() throws SQLException {
        Attack attack = randomAttack();
        String s1 = values[NAME],
                s2 = attack.getAttackName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setAttackName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackName() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getAttackName(),
                values[NAME]);
        ResultSet rs = getAttackInfo();
        String s = rs.getString(NAME + 1);
        attack.setAttackName(s);
        assertEquals(attack.getAttackName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getSpanishName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetSpanishName() throws SQLException {
        Attack attack = randomAttack();
        String s1 = values[S_NAME],
                s2 = attack.getSpanishName();
        assertEquals(s1, s2);
    }

    /**
     * Test of setSpanishName method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSpanishName() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getSpanishName(),
                values[S_NAME]);
        ResultSet rs = getAttackInfo();
        String s = rs.getString(S_NAME + 1);
        attack.setSpanishName(s);
        assertEquals(attack.getSpanishName(), s);
        ConnectionDB.close();
    }

    /**
     * Test of getAttackDescription method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAttackDescription() throws SQLException {
        Attack attack = randomAttack();
        String s1 = values[DESCRIPTION],
                s2 = attack.getAttackDescription();
        assertEquals(s1, s2);
    }

    /**
     * Test of setAttackDescription method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAttackDescription() throws SQLException {
        Attack attack = randomAttack();
        assertEquals(attack.getAttackDescription(),
                values[DESCRIPTION]);
        ResultSet rs = getAttackInfo();
        String s = rs.getString(DESCRIPTION + 1);
        attack.setAttackDescription(s);
        assertEquals(attack.getAttackDescription(), s);
        ConnectionDB.close();
    }

    /**
     * Test of selectAll method, of class Attack.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectAll() throws SQLException {
        Attack attack = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

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
        Attack attack = randomAttack();
        Attack attack2 = randomAttack();

        assertFalse(attack.attackDescriptionProperty()
                .equals(attack2.attackDescriptionProperty()));

        attack2 = attack;
        assertTrue(attack.attackDescriptionProperty()
                .equals(attack2.attackDescriptionProperty()));
    }

}
