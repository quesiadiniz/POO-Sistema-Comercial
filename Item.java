// Item.java
class Item {
    private String nome;
    private double preco;
    public Item(String nome, double preco) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome vazio");
        if (preco < 0)
            throw new IllegalArgumentException("Preço negativo");
        this.nome = nome.trim();
        this.preco = preco;
    }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}