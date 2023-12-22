package UnitTests;

import com.example.bookstorepro.Database.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseTest {

    private Connection mockConnection;


    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        DB.setMockConnection(mockConnection);
    }

    @Test
    void testGetConnection_WhenMockConnectionIsSet() {
        Connection conn = DB.getConnection();
        assertNotNull(conn);
        assertEquals(mockConnection, conn);
    }

    @Test
    void testGetConnection_WhenMockConnectionIsNull() {
        DB.setMockConnection(null);
        Connection conn = DB.getConnection();
        assertEquals(DB.RealConnection, conn);
    }

    @Test
    void testSetMockConnection() {
        Connection newMockConnection = mock(Connection.class);
        DB.setMockConnection(newMockConnection);
        assertEquals(newMockConnection, DB.getConnection());
    }

    @Test
    void testSetMockConnection_Null() {
        DB.setMockConnection(null);
        Connection connection = DB.getConnection();
        assertEquals(DB.RealConnection, connection);
    }




}
