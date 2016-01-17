package br.jus.tjpi.ad.util;

import org.cajuinabits.adsdk.core.util.LdapUtil;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author levi
 */
public class LdapUtilTest {
    
    public LdapUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encodePassword method, of class LdapUtil.
     */
    @Test
    public void testEncodePassword() throws Exception {
        System.out.println("encodePassword");
        String password = "12345678";
        String aux = "\"" + password + "\"";
        byte[] expResult = aux.getBytes("UTF-16LE");
        byte[] result = LdapUtil.encodePassword(password);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of decodePassword method, of class LdapUtil.
     */
    @Test
    public void testDecodePassword() {
        System.out.println("decodePassword");
        byte[] passwordBytes = null;
        String expResult = "12345678";
        try {
            passwordBytes = LdapUtil.encodePassword(expResult);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LdapUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = LdapUtil.decodePassword(passwordBytes);
        assertEquals(expResult, result);
    }

}
