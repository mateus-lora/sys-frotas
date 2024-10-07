public class Carro extends Veiculo{
    private int numeroPortas;

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }
    @Override
    public String toString() {
        String descricao = super.toString();
        return descricao + " - N. Portas: " + this.getNumeroPortas();
    }

}