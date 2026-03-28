package main.java;

public class GerenciadorImpressao {

    private static GerenciadorImpressao instancia;

    private GerenciadorImpressao() {
        System.out.println("Gerenciador de Impressão iniciado.");
    }

    public static GerenciadorImpressao getInstancia() {

        if (instancia == null) {
            instancia = new GerenciadorImpressao();
        }

        return instancia;
    }

    public void imprimir(String documento) {
        System.out.println("Imprimindo: " + documento);
    }
}