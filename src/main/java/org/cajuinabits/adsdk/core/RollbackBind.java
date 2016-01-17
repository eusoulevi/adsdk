
package org.cajuinabits.adsdk.core;

import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.cajuinabits.adsdk.core.beans.LdapUser;
import org.cajuinabits.adsdk.core.beans.LdapUserImpl;
import javax.naming.NamingException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ldap.transaction.compensating.BindOperationRecorder;
import org.springframework.transaction.compensating.CompensatingTransactionOperationExecutor;

/**
 *
 * @author levi
 */
public class RollbackBind {

	public static void main(String[] args) throws NamingException, InterruptedException {
		String xmlFileConfig = "src/conf/springldap.xml";
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(xmlFileConfig);
//		BindOperationRecorder bindOperationRecorder = (BindOperationRecorder) factory.getBean("bindOperationRecorder");
		LdapDaoImpl dao = null;
//		LdapDaoImpl dao = (LdapDaoImpl) LdapDaoImpl.getInstance();
		BindOperationRecorder recorder = (BindOperationRecorder) applicationContext.getBean("bindOperationRecorder");
		
//		BindOperationRecorder recorder = new BindOperationRecorder(dao.getLdapOperations());
			
		LdapUserImpl user = new LdapUserImpl("levitest", "1q2w!Q@W");
		user.setDescription("Estado Original");
		user.setFirstName("Levi");
		user.setLastName("Ultimo Nome Origianl");
		user.setDisplayName("Commit Inicial");
	
		CompensatingTransactionOperationExecutor rollbackOperation = recorder.recordOperation(
				new Object [] {user.getId(),user, user.getAttributes()});		
		
		dao.save(user);

		
//		BindOperationExecutor bindOperationExecutor = new BindOperationExecutor(dao.getLdapOperations(),
//				user.getId(), user, user.getAllAttributes());
		Thread.sleep(30000);// pausa de 30 segundos
		
		rollbackOperation.rollback();
//		bindOperationExecutor.rollback();
	}
	
}
