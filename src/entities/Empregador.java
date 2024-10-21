/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import dao.ConexaoBanco;
import java.sql.*;

/**
 *
 * @author leand
 */
public class Empregador {

    private int idEmpregador;
    private String nomeFantasia;
    private String razaoSocial;
    private String CNPJ;
    private String IE;
    private String senha;
    private String tipoUsuario;

    public Empregador() {
    }

    public Empregador(String nomeFantasia, String razaoSocial, String CNPJ, String IE, String senha, String tipoUsuario) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.IE = IE;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Empregador(int idEmpregador, String nomeFantasia, String razaoSocial, String CNPJ, String IE, String senha, String tipoUsuario) {
        this.idEmpregador = idEmpregador;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.IE = IE;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdEmpregador() {
        return idEmpregador;
    }

    public void setIdEmpregador(int idEmpregador) {
        this.idEmpregador = idEmpregador;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getIE() {
        return IE;
    }

    public void setIE(String IE) {
        this.IE = IE;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String toString() {
        return "Candidato{" + "idEmpregador=" + idEmpregador + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }   
    public boolean verificarCredenciais(Connection conexao) {
       String sql = "SELECT * FROM Empregador WHERE nomeFantasia=? and senha=?";
       try(PreparedStatement pst = conexao.prepareStatement(sql)) {
           pst.setString(1, this.nomeFantasia);
           pst.setString(2, this.senha);
           ResultSet rs = pst.executeQuery();
           if(rs.next()) {
               this.idEmpregador = Integer.parseInt(rs.getString("idEmpregador"));
               this.tipoUsuario = rs.getString("tipoUsuario");
               return true;
           }
       } catch (Exception e){
           System.out.println("Ocorreu um erro ao conectar com o banco! " + e.getMessage());
       }
       return false;
    }    
}
