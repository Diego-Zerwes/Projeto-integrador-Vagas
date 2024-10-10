
package entities;

public class CandidatoVaga {
    private Candidato candidato;
    private Vagas vaga;    
    
    public CandidatoVaga(){        
    }

    public CandidatoVaga(Candidato candidato, Vagas vaga) {
        this.candidato = candidato;
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Vagas getVaga() {
        return vaga;
    }

    public void setVaga(Vagas vaga) {
        this.vaga = vaga;
    }   
}
