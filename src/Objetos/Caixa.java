package Objetos;
public class Caixa {
    private int id_caixa;
    private int id_funcionario_fk;
    private String data_caixa;
    private double saldoIn_caixa;
    private double totalRec_caixa;
    private double totalPag_caixa;
    private double totalFin_caixa;

    public int getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(int id_caixa) {
        this.id_caixa = id_caixa;
    }

    public int getId_funcionario_fk() {
        return id_funcionario_fk;
    }

    public void setId_funcionario_fk(int id_funcionario_fk) {
        this.id_funcionario_fk = id_funcionario_fk;
    }

    public String getData_caixa() {
        return data_caixa;
    }

    public void setData_caixa(String data_caixa) {
        this.data_caixa = data_caixa;
    }

    public double getSaldoIn_caixa() {
        return saldoIn_caixa;
    }

    public void setSaldoIn_caixa(double saldoIn_caixa) {
        this.saldoIn_caixa = saldoIn_caixa;
    }

    public double getTotalRec_caixa() {
        return totalRec_caixa;
    }

    public void setTotalRec_caixa(double totalRec_caixa) {
        this.totalRec_caixa = totalRec_caixa;
    }

    public double getTotalPag_caixa() {
        return totalPag_caixa;
    }

    public void setTotalPag_caixa(double totalPag_caixa) {
        this.totalPag_caixa = totalPag_caixa;
    }

    public double getTotalFin_caixa() {
        return totalFin_caixa;
    }

    public void setTotalFin_caixa(double totalFin_caixa) {
        this.totalFin_caixa = totalFin_caixa;
    }
}
