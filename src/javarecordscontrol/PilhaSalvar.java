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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.startsWith("Nome:")) {
                    String[] campos = linha.split(" ");

                    String nome = campos[0].split(":")[1].trim();
                    double tempo = Double.parseDouble(campos[1].split(":")[1].trim());
                    LocalDate data = LocalDate.parse(campos[2].split(":")[1].trim(), formatter);

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
