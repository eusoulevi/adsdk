
package br.jus.tjpi.ad;

import org.cajuinabits.adsdk.core.ADTransactionManager;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.ldap.transaction.compensating.manager.ContextSourceTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 *
 * @author levi
 */
public class ADTransactionManagerTest {
//	private static ADTransactionManager transactionManager;
	public ADTransactionManagerTest() {
	}
	
	@BeforeClass
	public static void setUpClass() throws NamingException {
//		transactionManager = ADTransactionManager.getInstance();
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
	 * Test of getInstance method, of class ADTransactionManager.
	 */
	@Ignore
	public void testGetInstance() throws Exception {
		System.out.println("getInstance");
		String xmlFileConfig = "";
		ADTransactionManager expResult = ADTransactionManager.getInstance();
		ADTransactionManager result = ADTransactionManager.getInstance(xmlFileConfig);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetInstance2() throws Exception {
		ContextSourceTransactionManager transactionManager = new ContextSourceTransactionManager();
		System.out.println(transactionManager.isFailEarlyOnGlobalRollbackOnly());
		System.out.println(transactionManager.isGlobalRollbackOnParticipationFailure());
		System.out.println(transactionManager.isRollbackOnCommitFailure());
		DefaultTransactionStatus transactionStatus = new DefaultTransactionStatus(transactionManager, false, true, false, true, transactionManager);
		

	}
	
}
