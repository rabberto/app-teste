package br.com.rbbsolucoes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileOut {
	
	private Integer qtdeClientes;
	private Integer qtdeVendedores;
	private String idVenda;
	private String piorVendedor;
	private File arquivoOrigem;
	
	public void gerarArquivo(FileOut fileOut, String pathOut) throws IOException, NullPointerException, FileNotFoundException {
		
		File path = new File(pathOut + "\\" + fileOut.getArquivoOrigem().getName());
		boolean success = true;		
		if(!path.exists()) {					
			success = new File(pathOut).mkdirs();
		}
		
		if(success) {
			try (BufferedWriter b = new BufferedWriter(new FileWriter(path))){
				b.write("Quantidade de clientes no arquivo de entrada: " + fileOut.getQtdeClientes());
				b.newLine();
				b.write("Quantidade de vendedores no arquivo de entrada: " + fileOut.getQtdeVendedores());
				b.newLine();
				b.write("ID da venda mais cara: " + fileOut.getIdVenda());
				b.newLine();
				b.write("O pior vendedor: " + fileOut.getPiorVendedor());
				b.newLine();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Arquivo '" + fileOut.getArquivoOrigem().getName() + "' manipulado com sucesso");
				fileOut.getArquivoOrigem().delete();
			}
		}
	}
}
