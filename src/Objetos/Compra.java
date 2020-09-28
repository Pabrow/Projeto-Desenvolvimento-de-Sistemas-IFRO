package Objetos;
public class Compra {
    private int id_compra;
    private int id_fornecedor_fk;
    private int id_produto_fk;
    private String data;
    private double valor;
    private int quantItens;
    private String formaPag;

    public int getId_fornecedor_fk() {
        return id_fornecedor_fk;
    }

    public void setId_fornecedor_fk(int id_fornecedor_fk) {
        this.id_fornecedor_fk = id_fornecedor_fk;
    }

    public int getId_produto_fk() {
        return id_produto_fk;
    }

    public void setId_produto_fk(int id_produto_fk) {
        this.id_produto_fk = id_produto_fk;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantItens() {
        return quantItens;
    }

    public void setQuantItens(int quantItens) {
        this.quantItens = quantItens;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }
    
}
