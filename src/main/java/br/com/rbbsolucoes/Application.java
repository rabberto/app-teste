package br.com.rbbsolucoes;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {
		
	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(Application.class, args);

	}
	
	@Scheduled(fixedDelay = 5000)
	public void readFiles() throws IOException {
		String pathIn = "HOMEPATH/data/in";
		String pathOut = "HOMEPATH/data/out";
		ReadFile readFile = new ReadFile();
		readFile.iniciaLeitura(pathIn, pathOut);
	}
	
}