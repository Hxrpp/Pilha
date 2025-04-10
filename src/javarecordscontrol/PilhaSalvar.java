package javarecordscontrol;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PilhaSalvar {
 public static <T> void salvarPilha(Pilha<T> pilha, String caminho) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
        for (int i = 0; i < pilha.size(); i++) {
            writer.write(pilha.get(i).toString());
            writer.newLine();
        }
    }
}


    public static Pilha<Recorde> carregarPilha(String caminho, int tamanhoMaximo) throws IOException {
        Pilha<Recorde> pilha = new Pilha<>(tamanhoMaximo);
     try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
         String linha;
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         
         while ((linha = reader.readLine()) != null) {
             if (linha.startsWith("Nome:")) {
                 
                 String[] partes = linha.split(" ");
                 
                 String nome = partes[0].substring("Nome:".length());
                 double tempo = Double.parseDouble(partes[1].substring("Tempo:".length()));
                 LocalDate data = LocalDate.parse(partes[2].substring("Data:".length()), formatter);
                 
                 Recorde recorde = new Recorde();
                 recorde.setNome(nome);
                 recorde.setTempo(tempo);
                 recorde.setDataRecorde(data);
                 
                 pilha.push(recorde);
             }
         }
     }
        return pilha;
    }
}
