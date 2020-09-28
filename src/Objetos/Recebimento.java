package Objetos;
public class Recebimento {
    private int id_recebimento;
    private int id_caixa_fk;
    private int id_venda_fk;
    private String desc;
    private String data_venc;
    private double parcela;
    private double valor;
    private String formaPag;

    public int getId_recebimento() {
        return id_recebimento;
    }

    public void setId_recebimento(int id_recebimento) {
        this.id_recebimento = id_recebimento;
    }

    public int getId_caixa_fk() {
        return id_caixa_fk;
    }

    public void setId_caixa_fk(int id_caixa_fk) {
        this.id_caixa_fk = id_caixa_fk;
    }

    public int getId_venda_fk() {
        return id_venda_fk;
    }

    public void setId_venda_fk(int id_venda_fk) {
        this.id_venda_fk = id_venda_fk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData_venc() {
        return data_venc;
    }

    public void setData_venc(String data_venc) {
        this.data_venc = data_venc;
    }

    public double getParcela() {
        return parcela;
    }

    public void setParcela(double parcela) {
        this.parcela = parcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

}
