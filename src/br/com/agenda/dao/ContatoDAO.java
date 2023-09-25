package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
    /*
     * CRUD
     * c: CREATE - OK
     * r: READ - OK
     * u: UPDATE
     * d: DELETE
     */
    String sqlQuery;

    public void save(Contato contato) {
        // Query
        sqlQuery = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection connect = null;
        // Quem vai receber os parametros:
        PreparedStatement preparedStatement = null;

        try {
            // A ConnectionFactory que faz nossa conexão, por isso chamei ela aqui e vou
            // utilizar o metodo dela para criar a conexão
            connect = ConnectionFactory.createConnectionToMySQL();
            // Criamos uma PreparedStatement, para executar uma query
            preparedStatement = (PreparedStatement) connect.prepareStatement(sqlQuery);
            // Adicionar os valores esperados na query
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setInt(2, contato.getIdade());
            preparedStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));

            // Executar a quert
            preparedStatement.execute();
            System.out.println("Contato salvo");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar conexões se tudo der certo
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos() {
        // Aqui eu defino qual vai ser a operação a ser realizada, nesse caso como eu
        // quero a lista, usei SELECT
        sqlQuery = "SELECT * FROM contatos";

        List<Contato> listaContatos = new ArrayList<Contato>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Classe que ira recuperar os dados do banco
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.createConnectionToMySQL();

            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contato contato = new Contato();
                contato.setId(resultSet.getInt("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setIdade(resultSet.getInt("idade"));
                contato.setDataCadastro(resultSet.getDate("datacadastro"));

                listaContatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();

                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaContatos;
    }

    public void update(Contato contato) {
        sqlQuery = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.createConnectionToMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setInt(2, contato.getIdade());
            preparedStatement.setDate(3, new Date(contato.getDataCadastro().getTime()));

            preparedStatement.setInt(4, contato.getId());

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void deleteByID(int id) {
        sqlQuery = "DELETE FROM contatos WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.createConnectionToMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
