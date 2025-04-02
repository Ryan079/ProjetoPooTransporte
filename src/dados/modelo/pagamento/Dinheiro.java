package dados.modelo.pagamento;

public class Dinheiro extends FormaDePagamento{
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com dinheiro");
    }
}
