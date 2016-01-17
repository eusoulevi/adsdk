# adsdk
Adsdk é um projeto open source que surgiu da necessidade de integrar aplicações com protocolo LDAP, mais especificamente com o Active Directory do Windows Server 2008 da Microsoft.
A biblioteca JNDI do java é muito complexa e o spring Ldap abstrai toda a complexidade da biblioteca JNDI, porém mantém a complexidade dos Serviços de Diretório, por ser um framework genérico que atua com vários sistemas com Open LDAP, apache, etc.
Existem diversos framework específicos para esta finalidade, porém devido a complexidade de muitos, desenvolveu-se uma API para interagi diretamente com o Active Directory do Windows.
A API adsdk oferece uma maneira intuitiva de criar usuários, grupos e Unidades Organizacionais em Servidores de Diretórios Windows, abstraindo os usuários da complexidade da tecnologia LDAP. A classe principal obedece ao padrão DAO. Com isso usuário tem uma curva de aprendizagem curta, tem se a impressão que estamos uma ferramenta ORM (Object/Relational Mapping) para inseri os objetos no servidor de Diretórios.
Seu objetivo é facilitar a integração de sistemas ao Active Directory, abstraindo o programador da complexidade de manipulação de objetos (Grupos, Unidades organizacionais, Usuários, computadores, etc) do Banco de Dados LDAP. A biblioteca terá classes para cadastrar, alterar, consultar e remover usuários, grupos, OUs e atribuí permissões no serviço de diretórios do Windows Server.
O projeto foi construídos utilizando a framework SpringLDAP.

Valores:
•	Código Limpo (Clean Code);
•	Simplicidade;
•	Integração;

Núcleo do adsdk:
•	LdapDaoImpl: Dao genérico para interagir diretamente com o active directory;
•	LdapUserDaoimpl: Dao específico para manipular Objetos do tipo usuários no Active Directory;
•	GroupDaoImpl: Dao específico para manipular grupos no Active Directory;
•	OrganizationalUnitDaoimpl: Dao específico para manipular Objetos do tipo Unidades organizaciomnais no Active Directory;

