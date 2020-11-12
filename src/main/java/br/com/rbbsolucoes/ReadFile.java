package br.com.rbbsolucoes;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


public class ReadFile {
	
	@Scheduled(fixedDelay = 10000)
	public void iniciaLeitura(String pathIn, String pathOut) throws IOException {

	FileFilter filter = new FileFilter() {
	    public boolean accept(File file) {
	        return file.getName().endsWith(".txt");
	    }
	};
	
	File dir = new File(pathIn);
	boolean success = false;
	
	if (dir.exists()) {
		if (dir.canRead()) {
				FileIn fileIn = new FileIn();	
				File[] files = dir.listFiles(filter);
				FileOut fileOut = fileIn.lerArquivo(files);
				if(fileOut != null) {
					fileOut.gerarArquivo(fileOut, pathOut);
				}
			} else
				System.out.println("Não há permissão para leitura dos aquivos no diretório informado");				
		} else
			
			if(!dir.exists()) {					
				success = new File(pathIn).mkdirs();
				System.out.println("Diretório dos arquivos de entrada informado não existe. Criado: " + success);
			}
	
			
	}
}
