package dados.modelo.pagamento;

public class CartaoCredito extends FormaDePagamento{

    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado com cartão de crédito");
    }
}
