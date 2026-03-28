package test;

import main.java.GerenciadorImpressao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorImpressaoTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();

    @BeforeEach
    void configurarSaida() {
        System.setOut(new PrintStream(saida));
    }

    @Test
    void deveRetornarInstanciaNaoNula() {
        GerenciadorImpressao instancia = GerenciadorImpressao.getInstancia();
        assertNotNull(instancia);
    }

    @Test
    void deveRetornarSempreAMesmaInstancia() {
        GerenciadorImpressao primeira = GerenciadorImpressao.getInstancia();
        GerenciadorImpressao segunda  = GerenciadorImpressao.getInstancia();
        assertSame(primeira, segunda);
    }

    @Test
    void deveRetornarMesmaReferenciaEmMultiplasChamadas() {
        GerenciadorImpressao i1 = GerenciadorImpressao.getInstancia();
        GerenciadorImpressao i2 = GerenciadorImpressao.getInstancia();
        GerenciadorImpressao i3 = GerenciadorImpressao.getInstancia();
        assertSame(i1, i2);
        assertSame(i2, i3);
    }

    @Test
    void construtorDeveSerPrivado() throws NoSuchMethodException {
        Constructor<GerenciadorImpressao> construtor =
                GerenciadorImpressao.class.getDeclaredConstructor();

        assertFalse(construtor.isAccessible(),
                "O construtor não deve ser publicamente acessível");
    }

    @Test
    void deveImprimirDocumentoCorretamente() {
        GerenciadorImpressao.getInstancia().imprimir("Relatório.pdf");
        assertTrue(saida.toString().contains("Imprimindo: Relatório.pdf"));
    }

    @Test
    void deveImprimirMensagemDeInicializacaoUmaVez() {
        GerenciadorImpressao.getInstancia();
        saida.reset();

        GerenciadorImpressao.getInstancia();
        GerenciadorImpressao.getInstancia();

        String log = saida.toString();
        int ocorrencias = log.split("Gerenciador de Impressão iniciado", -1).length - 1;
        assertEquals(0, ocorrencias,
                "A mensagem de inicialização não deve aparecer nas chamadas subsequentes");
    }

    @Test
    void deveMantermComportamentoConsistenteEntreInstancias() {
        GerenciadorImpressao g1 = GerenciadorImpressao.getInstancia();
        GerenciadorImpressao g2 = GerenciadorImpressao.getInstancia();

        g1.imprimir("Doc-A.txt");
        g2.imprimir("Doc-B.txt");

        String log = saida.toString();
        assertTrue(log.contains("Imprimindo: Doc-A.txt"));
        assertTrue(log.contains("Imprimindo: Doc-B.txt"));
    }
}