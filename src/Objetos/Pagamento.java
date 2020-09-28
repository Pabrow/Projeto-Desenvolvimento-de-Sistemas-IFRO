package Objetos;
public class Pagamento {
    private int id_pagamento;
    private int id_compra_fk;
    private int id_caixa_fk;
    private int id_despesa_fk;
    private String data;
    private double parcela;
    private double valor;
    private String formaPag;

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public int getId_compra_fk() {
        return id_compra_fk;
    }

    public void setId_compra_fk(int id_compra_fk) {
        this.id_compra_fk = id_compra_fk;
    }

    public int getId_caixa_fk() {
        return id_caixa_fk;
    }

    public void setId_caixa_fk(int id_caixa_fk) {
        this.id_caixa_fk = id_caixa_fk;
    }

    public int getId_despesa_fk() {
        return id_despesa_fk;
    }

    public void setId_despesa_fk(int id_despesa_fk) {
        this.id_despesa_fk = id_despesa_fk;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
