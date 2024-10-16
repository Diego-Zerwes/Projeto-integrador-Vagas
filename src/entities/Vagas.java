package entities;

public class Vagas {

    private int idVaga;
    private String descricao;
    private String requisitos;
    private double salario;

    public Vagas() {
    }

    public Vagas(int idVaga, String descricao, String requisitos, double salario) {
        this.idVaga = idVaga;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.salario = salario;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }   
}
