import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {

        // Produtos pré-cadastrados
        produtos.add(new Produto(1, "Arroz", 25.90, 50));
        produtos.add(new Produto(2, "Feijao", 8.50, 40));
        produtos.add(new Produto(3, "Macarrao", 6.99, 30));
    
        // Clientes pré-cadastrados
        clientes.add(new Cliente("12345678901", "Pedro Jesus", "pedro@gmail.com"));
        clientes.add(new Cliente("98765432100", "Maria Silva", "maria@gmail.com"));
        clientes.add(new Cliente("11122233344", "Joao Santos", "joao@gmail.com"));
    
        int opcao;
    
        do {
            System.out.println("\n=== MINI MERCADO ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Cadastrar Cliente");
            System.out.println("3 - Realizar Compra");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Listar Clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
    
            opcao = sc.nextInt();
            sc.nextLine();
    
            switch (opcao) {
    
                case 1:
                    cadastrarProduto();
                    break;
    
                case 2:
                    cadastrarCliente();
                    break;
    
                case 3:
                    realizarCompra();
                    break;
    
                case 4:
                    listarProdutos();
                    break;
    
                case 5:
                    listarClientes();
                    break;
    
                case 0:
                    salvarProdutos();
                    salvarClientes();
                    System.out.println("Sistema encerrado.");
                    break;
    
                default:
                    System.out.println("Opcao invalida!");
            }
    
        } while (opcao != 0);
    }
    public static void cadastrarProduto() {

        System.out.print("Codigo: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preco: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Produto produto = new Produto(codigo, nome, preco, estoque);

        produtos.add(produto);

        salvarProdutos();

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void cadastrarCliente() {

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(cpf, nome, email);

        clientes.add(cliente);

        salvarClientes();

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void realizarCompra() {

        listarProdutos();

        System.out.print("Digite o codigo do produto: ");
        int codigo = sc.nextInt();

        Produto produtoEncontrado = null;

        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                produtoEncontrado = p;
                break;
            }
        }

        if (produtoEncontrado == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade > produtoEncontrado.getEstoque()) {
            System.out.println("Estoque insuficiente.");
            return;
        }

        double total = produtoEncontrado.getPreco() * quantidade;

        produtoEncontrado.setEstoque(
                produtoEncontrado.getEstoque() - quantidade
        );

        salvarProdutos();

        System.out.println("Compra realizada com sucesso!");
        System.out.println("Produto: " + produtoEncontrado.getNome());
        System.out.println("Total: R$ " + String.format("%.2f", total));
    }

    public static void listarProdutos() {

        System.out.println("\n--- PRODUTOS ---");

        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    public static void listarClientes() {

        System.out.println("\n--- CLIENTES ---");

        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public static void salvarProdutos() {

        try {

            PrintWriter writer =
                    new PrintWriter(new FileWriter("produtos.csv"));

            writer.println("codigo;nome;preco;estoque");

            for (Produto p : produtos) {

                writer.println(
                        p.getCodigo() + ";" +
                        p.getNome() + ";" +
                        p.getPreco() + ";" +
                        p.getEstoque()
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar produtos.");
        }
    }

    public static void salvarClientes() {

        try {

            PrintWriter writer =
                    new PrintWriter(new FileWriter("clientes.csv"));

            writer.println("cpf;nome;email");

            for (Cliente c : clientes) {

                writer.println(
                        c.getCpf() + ";" +
                        c.getNome() + ";" +
                        c.getEmail()
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar clientes.");
        }
    }
}
