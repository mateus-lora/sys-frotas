public final class Moto extends Veiculo{
    private boolean partidaEletrica;

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }
    @Override
    public String toString() {
        String descricao = super.toString();
        return descricao + " - Part. Elétrica: " + ((this.getPartidaEletrica() == true) ? "Sim" : "Não");

    }

}
