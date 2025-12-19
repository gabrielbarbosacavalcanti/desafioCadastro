package desafioCadastro;

import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GeradorArquivoPet {
	public static Path gerarCaminho(String nomepet) throws Exception {
		
		LocalDateTime agora = LocalDateTime.now();
		
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
		
		String dataHora = agora.format(formatter);
		
		String petMaiusculo = nomepet.toUpperCase(Locale.ROOT);
	}
}
