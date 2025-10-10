// App.java
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int capacidade = lerIntPositivo(in, "Capacidade do pedido? ");
        Pedido pedido = new Pedido(capacidade);
        // Entrada de itens
        while (true) {
            System.out.print("Nome do item (ou ENTER p/ finalizar): ");
            String nome = in.nextLine().trim();
            if (nome.isEmpty()) 
            break;
            double preco = lerDoubleNaoNegativo(in, "Preço do item: ");
            if (!pedido.adicionarItem(new Item(nome, preco))) {
                System.out.println("Carrinho cheio! Finalizando...");
                break;
            }
        }
        // Cupom (opcional)
        if (confirmar(in, "Deseja aplicar cupom? (s/n): ")) {
            System.out.print("Código do cupom: ");
            String codigo = in.nextLine().trim();
            boolean ok = pedido.aplicarCupom(codigo);
            if (!ok) System.out.println("Cupom inválido ou não aplicável.");
        }
        // Taxa
        double taxa = lerTaxaValida(in, "Taxa de serviço (0 a 20): ");
        // Recibo
        System.out.println();
        System.out.println(pedido.gerarRelatorio(taxa));
        in.close();
    }
    // ===== utilitários de leitura/validação =====
    private static int lerIntPositivo(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v > 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Valor inválido. Tente um inteiro > 0.");
        }
    }
    private static double lerDoubleNaoNegativo(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String s = in.nextLine().trim().replace(",", ".");
                double v = Double.parseDouble(s);
                if (v >= 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Valor inválido. Digite um número >= 0.");
        }
    }
    private static boolean confirmar(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("s")) return true;
            if (s.equals("n")) return false;
            System.out.println("Responda com 's' ou 'n'.");
        }
    }
    private static double lerTaxaValida(Scanner in, String prompt) {
        while (true) {
            try {
                String s = lerLinha(in, prompt).replace(",", ".").trim();
                double v = Double.parseDouble(s);
                if (v >= 0 && v <= 20) return v;
            } catch (Exception ignored) {}
            System.out.println("Taxa inválida. Digite um valor entre 0 e 20.");
        }
    }
    private static String lerLinha(Scanner in, String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
}