import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static List<Rule> getKnowledge() throws FileNotFoundException, IOException {
        // Ganti dengan lokasi project Anda nanti
        FileReader reader = new FileReader("D:\\Hehehe\\S5\\SimpleExpertSystem-main\\src\\knowledge_base");
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Rule> rules = new ArrayList<>();
        String line;

        // Membaca aturan dari file knowledge base
        while ((line = bufferedReader.readLine()) != null) {
            // Mengambil bagian fakta dan kesimpulan
            String[] parts = line.split("-");
            rules.add(new Rule(
                    Arrays.asList(parts[0].split(",")),  // Gejala
                    parts[1]                             // Penyakit
            ));
        }

        bufferedReader.close();
        reader.close();
        return rules;
    }

    public static void main(String[] args) throws IOException {
        
        UI tampilan = new UI();
        tampilan.showInitialQuestions();  // Memanggil pertanyaan awal

        // Mengambil fakta awal dari jawaban pengguna
        Set<String> facts = tampilan.getFacts();
        
        // Mendapatkan knowledge base
        List<Rule> rules = getKnowledge();
        
        // Melakukan inferensi menggunakan forward chaining
        Set<String> inferredFacts = InferenceForwardChaining.doForwardChaining(rules, facts);
        
        // Menampilkan kesimpulan berdasarkan fakta yang terinferensi
        tampilan.showConclusion(inferredFacts, facts);  // Tampilkan kesimpulan
    }
}
