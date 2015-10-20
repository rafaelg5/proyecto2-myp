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
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rgarcia
 */
public class ConnectionDBTest {

    /**
     * Test of open method, of class ConnectionDB.
     *
     */
    @Test
    public void testOpen() {
        Connection conn = ConnectionDB.open();
        try {
            assertFalse(conn.isClosed());
            assertEquals(ConnectionDB.open(), conn);
            ConnectionDB.close();
            assertTrue(conn.isClosed());
        } catch (SQLException e) {
            fail();
        }
    }

    /**
     * Test of close method, of class ConnectionDB.
     */
    @Test
    public void testClose() {
        Connection conn = ConnectionDB.open();
        try {
            assertFalse(conn.isClosed());
            assertEquals(ConnectionDB.open(), conn);
            ConnectionDB.close();
            assertTrue(conn.isClosed());
        } catch (SQLException e) {
            fail();
        }
    }

}
