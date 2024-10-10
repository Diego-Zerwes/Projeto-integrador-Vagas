
package entities;

import dao.ConexaoBanco;
import java.sql.*;

public class Candidato {
    private int idCandidato;
    private String nome;
    private String senha;
    private String tipoUsuario;
    
    public Candidato() { //construtor padrão        
    }

    public Candidato(String nome, String senha) { //construtor com parâmetros
        this.nome = nome;
        this.senha = senha;
    }   

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //public String getSenha() {
    //    return senha;
    //}

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Candidato{" + "idCandidato=" + idCandidato + ", nome=" + nome + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }    
    
//    public boolean verificarCredenciais(ConexaoBanco conexao) {
//        
//    }
    
    public boolean verificarCredenciais(Connection conexao) {
       String sql = "SELECT * FROM candidato WHERE nome=? and senha=?";
       try(PreparedStatement pst = conexao.prepareStatement(sql)) {
           pst.setString(1, this.nome);
           pst.setString(2, this.senha);
           ResultSet rs = pst.executeQuery();
           if(rs.next()) {
               this.idCandidato = Integer.parseInt(rs.getString("idCandidato"));
               this.tipoUsuario = rs.getString("tipoUsuario");
               return true;
           }
       } catch (Exception e){
           System.out.println("Ocorreu um erro ao conectar com o banco! " + e.getMessage());
       }
       return false;
    }    
}
