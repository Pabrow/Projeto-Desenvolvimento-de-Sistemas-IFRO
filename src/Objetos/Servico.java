package Objetos;
public class Servico {
    private int id_servico;
    private int id_funcionario_fk;
    private String desc;
    private String tempo;
    private double valor;

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public int getId_funcionario_fk() {
        return id_funcionario_fk;
    }

    public void setId_funcionario_fk(int id_funcionario_fk) {
        this.id_funcionario_fk = id_funcionario_fk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
