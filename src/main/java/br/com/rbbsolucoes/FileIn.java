package br.com.rbbsolucoes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileIn {
	
	public FileOut lerArquivo(File[] files) throws IOException {
		
			if (files.length > 0) {
				for (File file : files) {
					try {
						Path path = Paths.get(file.getAbsolutePath());
						List<String> linhasArquivo = Files.readAllLines(path);
						FileOut  fileOut = tratarConteudo(linhasArquivo, file.getName());
						fileOut.setArquivoOrigem(file);
						return fileOut;
						
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
				}
			} else
				System.out.println("Não há arquivos '*.txt' para leitura no diretório informado");
		return null;
	}
	
	
	public FileOut tratarConteudo(List<String> linhasArquivo, String nomeArquivoOrigem) {
		
		Integer qtdeClientes = 0;
    	Integer qtdeVendedores = 0;
    	String idVenda = "";
    	String piorVendedor = "";
    	double maiorVenda = 0;
    	double menorVenda = 0;
		
 		for (String linha : linhasArquivo) {
			String[] textoSeparado = linha.split("ç");
			
			if( linha.substring(0, 3).equals("001")) { // Dados Vendedor
    			qtdeVendedores += 1;
    			
    		} else if ( linha.substring(0, 3).equals("002")) { // Dados Clientes
    			qtdeClientes += 1;

    		} else if ( linha.substring(0, 3).equals("003")) { // Dados da Venda
    			
    			String[] itens = textoSeparado[2].split("-");
    			Integer qtde = 0;
    			double valor = 0;
    			
    			for (int i = 0; i < itens.length; i++) {
    				qtde += Integer.parseInt(itens[1]);
    				valor += Integer.parseInt(itens[3]);
    			}
    			
    			double valorTotal = qtde * valor;
    			
    			if (maiorVenda < valorTotal) {
    				maiorVenda = valorTotal;
    				idVenda = textoSeparado[1];
    			}
    			
    			if (menorVenda == 0) {
    				menorVenda = valorTotal;
    				piorVendedor = textoSeparado[3];
    			} else {
    				menorVenda = valorTotal;
    				piorVendedor = textoSeparado[3];
    			}
    		}			
		}
 		
 		FileOut fileOut = new FileOut(); 
 		fileOut.setQtdeVendedores(qtdeVendedores);
 		fileOut.setQtdeClientes(qtdeClientes);
 		fileOut.setIdVenda(idVenda);
 		fileOut.setPiorVendedor(piorVendedor);
 		return fileOut;
 		
	}

}
