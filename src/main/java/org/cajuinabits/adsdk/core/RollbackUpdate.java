
package org.cajuinabits.adsdk.core;

import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl;
import java.util.List;
import javax.naming.NamingException;
import org.cajuinabits.adsdk.core.dao.GroupDaoImpl;
import org.cajuinabits.adsdk.core.dao.OrganizationalUnitDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ldap.transaction.compensating.BindOperationRecorder;
import org.springframework.transaction.compensating.CompensatingTransactionOperationExecutor;

/**
 *
 * @author levi
 */
public class RollbackUpdate {

	public static void main(String[] args) throws NamingException, InterruptedException {
		
		// Criação do DAO
		String xmlFileConfig = "src/conf/springldap.xml";
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(xmlFileConfig);
		LdapDaoImpl dao = null;
//		dao = (LdapDaoImpl) LdapDaoImpl.getInstance();
        GroupDaoImpl groupDao = null;
//        groupDao = (GroupDaoImpl) GroupDaoImpl.getInstance();
		OrganizationalUnitDao organizationalUnitDao = null;
		// Criação do objeto BindOperationRecorder que grava todas operações executadas pelo Dao
		
		BindOperationRecorder recorder = new BindOperationRecorder(dao.getLdapOperations());
		
		// Obtenção da Unidade Organizacional ouTestOriginal
		List<OrganizationalUnitImpl> ous = organizationalUnitDao.getOU("ouTestOriginal");
		OrganizationalUnitImpl ou = ous.get(0);
		
//		Recuperação do Objeto CompensatingTransactionOperationExecutor que tem armazenado o estado original
//		do objeto Unidade Organizacional;
		CompensatingTransactionOperationExecutor rollbackOperation = recorder.recordOperation(
				new Object [] {ou.getId(),ou, ou.getAttributes()});

		System.out.println();
		System.out.println("Dados da OU antes da Atualização");
		System.out.println(ou);
		System.out.println();
		System.out.println();
		
		
		// Alteração dos dados da Unidade Organizacional
		ou.setDescription("Descrição DEPOIS do Updade");
		ou.setAttributeValue("l", "Floriano");
		dao.update(ou);

		Thread.sleep(30000);// pausa de 30 segundos
		System.out.println("dados da Unidade Organizacional depois da Atualização");
		System.out.println(groupDao.getGroup("update"));
		
		
		rollbackOperation.rollback();
		System.out.println(organizationalUnitDao.getOU("ouTestOriginal"));
		System.out.println();
		System.out.println("Dados da OU Depois do Rollback");
		System.out.println(ou);

	}
	
}
