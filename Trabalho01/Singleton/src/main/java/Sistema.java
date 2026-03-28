package main.java;

public class Sistema {

    public static void main(String[] args) {

        GerenciadorImpressao g1 = GerenciadorImpressao.getInstancia();
        g1.imprimir("Relatório Financeiro");

        GerenciadorImpressao g2 = GerenciadorImpressao.getInstancia();
        g2.imprimir("Contrato do Cliente");

        System.out.println(g1 == g2); // true
    }
}