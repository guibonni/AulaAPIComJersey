package br.com.rest.exercicios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("/exercicios")
public class ExerciciosResource {
	
	// http://localhost:8080/ExercicioAula1/rest/exercicios/frase/Guilherme/22/JavaScript
	@GET
	@Path("frase/{nome}/{idade}/{linguagem}")
	public String frase(@PathParam("nome") String nome, @PathParam("idade") int idade, @PathParam("linguagem") String linguagem, @Context HttpServletRequest request) {
		String ip = request.getLocalAddr();
		String params = "nome: " + nome + ", idade: " + String.valueOf(idade) + ", linguagem: " + linguagem;
		this.log(params, ip);
		
		String out = "Eu sou " + nome +  ", trabalho e estudo, tenho " + String.valueOf(idade) + " anos e adoro programar em " + linguagem;
		return out;
	}
	
	// http://localhost:8080/ExercicioAula1/rest/exercicios/imc/80/1.80
	@GET
	@Path("imc/{peso}/{altura}")
	public String imc(@PathParam("peso") double peso, @PathParam("altura") double altura, @Context HttpServletRequest request) {
		String ip = request.getLocalAddr();
		String params = "peso: " + String.valueOf(peso) + ", altura: " + String.valueOf(altura);
		this.log(params, ip);
		
		String estado = "";
		double imc = peso / (altura * altura);
		
		if (imc < 18.5) {
			estado = "abaixo do peso.";
		} else if (imc < 25) {
			estado = "no peso normal.";
		} else {
			estado = "acima do peso.";
		}
		
		String out = "Eu tenho " + altura + "m de altura e tenho " + peso + " Kg. Eu estou " + estado;
		return out;
	}
	
	private void log(String params, String ip) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String horario = dateFormat.format(date);
			
			String logMessage = "IP: " + ip + "  -  Horário: " + horario;
			logMessage += "\nParams -> " + params + "\n\n";
			
			File file = new File("log.txt");
			FileWriter fr = new FileWriter(file, true);
			
			fr.write(logMessage);
			fr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
