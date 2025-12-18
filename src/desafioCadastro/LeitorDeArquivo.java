package desafioCadastro;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Collections;

public class LeitorDeArquivo {
    // Transformamos em um método que retorna List<String>
    public List<String> lerLinhas(String nomeDoArquivo) {
        try {
            return Files.readAllLines(Paths.get(nomeDoArquivo), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return Collections.emptyList(); // Retorna lista vazia em caso de erro
        }
    }
}
	

