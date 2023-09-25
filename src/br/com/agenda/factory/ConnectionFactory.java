package br.com.agenda.factory;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    //Nome do Usuario do mysql
    private static final String USERNAME = "root";
    //Senha do banco
    private static final String PASSWORD ="";
    //Caminho do banco de dados, porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    /*
     * Conexão com o banco de dados
     */
    public static Connection createConnectionToMySQL() throws Exception{
        //Carregando a classe na JVM
        Class.forName("com.mysql.jdbc.Driver");

        //Cria a conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
    }

    public static void main(String[] args) throws Exception {
        //Recuperar uma conexão com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testando se a conexão é nula
        if(con!=null){
            System.out.println("Conexão efetuada com sucesso!");
            con.close();
        }
    }
}
