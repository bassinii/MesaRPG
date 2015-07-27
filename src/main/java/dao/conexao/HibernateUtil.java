package dao.conexao;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/* A responsabilidade da classe é instanciar o SessionFactory, que
 * contém todas configuraçÕes do arquivo hibernate.cfg.xml
 */
public class HibernateUtil {
//objeto que fabrica uma ou mais instância de sessões de acesso ao banco a partir da configurações do objeto serviceRegistry

    private static SessionFactory sessionFactory;
//objeto responsável pela configurações do hibernate.cfg.xml
    private static ServiceRegistry serviceRegistry;

    @SuppressWarnings("unused")
    private static SessionFactory buildSessionFactory() {
        try {
            //objeto que armazena configurações do hibernate.cfg.xml
            Configuration configuration = new Configuration();
            //método que lê e valida as configurações em hibernate.cfg.xml
            configuration.configure();

            Properties properties = configuration.getProperties();

//            
//            String db = "mysql";
//            String dbhost = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//            String dbport = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
//            String dbname = "jogar";
//            
//            String url = "jdbc:"+db+"://"+dbhost+":"+dbport+"/"+dbname;
//            
//            properties.setProperty("hibernate.connection.url", url );
//            
            //aplica e carrega as configurações no objeto serviceRegistry
            serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).build();
            //cria uma ou mais instâncias de sessão da configuração. Geralmente uma aplicação tem uma única instância de sessão e threads servindo pedidos de clientes obtendo instâncias da sessão do factory (fábrica)
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("Criacao do objeto falhou: " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }

    public static void CloseSessionFactory(SessionFactory factory, Session session) {

        try {
            if (session != null) {
                session.flush();
                session.close();
            }
            if (factory != null) {
                factory.close();
            }

        } catch (Exception e) {
            System.out.println("ocorreu um erro de crud.");
        }
    }
}
