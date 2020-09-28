package Objetos;
public class Venda {
    private int id_venda;
    private int id_funcionario_fk;
    private int id_cliente_fk;
    private String data;
    private String hora;
    private String formaPag;
    private double valor;

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_funcionario_fk() {
        return id_funcionario_fk;
    }

    public void setId_funcionario_fk(int id_funcionario_fk) {
        this.id_funcionario_fk = id_funcionario_fk;
    }

    public int getId_cliente_fk() {
        return id_cliente_fk;
    }

    public void setId_cliente_fk(int id_cliente_fk) {
        this.id_cliente_fk = id_cliente_fk;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
