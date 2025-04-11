package negocio.gerenciador.utilitario;

import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pagamento.Pix;
import dados.modelo.pessoa.Cliente;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;

import java.util.Arrays;

public class Validador {

    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\d{11}");
    }

    public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    public static boolean validarCNH(String cnh) {
        return cnh != null && cnh.matches("\\d{9}");
    }

    public static boolean validarVeiculo(Veiculo veiculo) {
        if (veiculo == null) return false;
        return validarPlaca(veiculo.getPlaca()) &&
                validarTipo(veiculo.getTipo());
    }

    public static boolean validarPlaca(String placa) {
        return placa != null && (placa.matches("^[A-Z]{3}[0-9]{4}$") || placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}"));
    }

    public static boolean validarTipo(TipoVeiculo tipo) {
        return tipo != null && Arrays.asList(TipoVeiculo.values()).contains(tipo);
    }

    public static boolean possuiPix(Cliente c) {
        for(FormaDePagamento pagamento : c.getFormaDePagamentos()) {
            if(pagamento instanceof Pix)
                return true;
        }
        return false;
    }

}
