public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int codigo, String nome, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String toString() {
        return codigo + " - " + nome + " | R$ " + preco + " | Estoque: " + estoque;
    }
}
