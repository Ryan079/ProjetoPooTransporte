package dados.modelo.pagamento;

public class Pix extends FormaDePagamento{

    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado via pix");
    }
}
