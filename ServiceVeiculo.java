import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    private List<Veiculo> frota = new ArrayList<>();

    public void salvar(Veiculo veiculo) throws Exception {
        String mensagem = "não pode ser em branco.";
        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
            throw new Exception("Campo marca " + mensagem);
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new Exception("Campo modelo " + mensagem);
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new Exception("Campo placa " + mensagem);
        }
        for (Veiculo veiculo1 : frota) {
            if (veiculo1.getPlaca().equalsIgnoreCase(veiculo.getPlaca()))
                throw new Exception("Já existe um veículo cadastrado com esta placa.");
        }
        if (veiculo.getAno() == 0) {
            throw new Exception("Campo ano não pode ser \"0\".");
        }
        if (veiculo.getAno() < 1900 || veiculo.getAno() > (LocalDate.now().getYear())) {
            throw new Exception("Campo ano não está dentro do intervalo permitido.");
        }
        if (veiculo instanceof Carro) {
            if (((Carro) veiculo).getNumeroPortas() == 0) {
                throw new Exception("Campo porta não pode ser \"0\".");
            } else if (((Carro) veiculo).getNumeroPortas() < 0) {
                throw new Exception("Campo porta não pode ser negativo.");
                
            }
        }
        frota.add(veiculo);
    }

    public List<Veiculo> encontarTodos() {
        return frota;
    }

    public Veiculo encontrarPorPlaca(String placa) throws Exception {
        Veiculo veiculoRet = null;
        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoRet = veiculo;
                break;
            }
        }
        if (veiculoRet == null)
            throw new Exception("Veículo não encontrado com a placa informada!");
        return veiculoRet;
    }

    public void removerPorPlaca(String placa) throws Exception {
        boolean veiculoRemovido = false;
        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoRemovido = true;
                frota.remove(veiculo);
                break;
            }
        } 
        if (!veiculoRemovido) {
            throw new Exception("Veículo não encontrado com a placa informada!");
        }
    }
}