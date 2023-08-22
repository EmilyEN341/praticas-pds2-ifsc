package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pessoa;

public class PessoaDAO {

	public ArrayList<Pessoa> listar() {

		ConexaoBanco c = ConexaoBanco.getInstancia();

		Connection con = c.conectar();

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		// Select Simples:
		String query = "SELECT * FROM pessoa";
		// Preparando a nossa query SQL acima em um obj java
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);

			// Executa a nossa query de fato
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// Pega o que o bd retornou
				int idPessoa = rs.getInt("id_pessoa");
				String primeiroNome = rs.getString("primeiro_nome");

				// Cria um obj organizadinho de pessoa
				Pessoa p1 = new Pessoa();
				p1.setId_pessoa(idPessoa);
				p1.setPrimeiro_nome(primeiroNome);

				// Adiciona pessoa na lista:
				pessoas.add(p1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.fecharConexao();
		return pessoas;
	}

	public boolean inserir(Pessoa p) {

		ConexaoBanco c = ConexaoBanco.getInstancia();

		Connection con = c.conectar();

		String query = "INSERT INTO pessoa " + "(id_pessoa, primeiro_nome) " + "VALUES (?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, p.getId_pessoa());
			ps.setString(2, p.getPrimeiro_nome());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Métodos de excluir e atualizar
	public boolean excluir (Pessoa p) {
		ConexaoBanco c = ConexaoBanco.getInstancia();
		Connection con = c.conectar();
		
		String query = "DELETE FROM pessoa WHERE id_pessoa = ?";
		try {
			//Foi simplesmente colado o PreparedStatement e depois aplicar o surround with try catch
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getId_pessoa());
			ps.executeUpdate();
			
			c.fecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean atualizar (Pessoa p) {
		ConexaoBanco c = ConexaoBanco.getInstancia();
		Connection con = c.conectar();
		
		String query = "UPDATE pessoa SET primiro_nome = ?";
		try {
			//Foi simplesmente colado
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getId_pessoa());
			ps.executeUpdate();
			
			c.fecharConexao();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}