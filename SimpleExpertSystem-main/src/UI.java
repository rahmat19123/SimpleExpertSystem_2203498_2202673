import java.util.*;

/**
 * UI sistem pakar untuk memeriksa apakah g bernilai true dari fakta a, b, c, f  
 * Sistem ini memiliki 4 pertanyaan yang akan diberikan pada user
 * 4 pertanyaan merepresentasikan 4 fakta
 * fact: a, b, c, f
 * conclussion: g
 * @author AERO
 **/
public class UI {
    
    private ArrayList<String> bulaiQuestions;
    private ArrayList<String> rustQuestions;
    private int answers[];

    public UI() {
        this.answers = new int[6];
        this.rustQuestions = new ArrayList<String>();
        this.bulaiQuestions = new ArrayList<String>();
        this.setQuestions();
    }

    private void setQuestions() {
        bulaiQuestions.add("Apakah pertumbuhan tanaman terhambat?");
        bulaiQuestions.add("Apakah terdapat bercak putih di kedua sisi daun?");
        bulaiQuestions.add("Apakah daun Tanaman tidak tumbuh lurus?");
        bulaiQuestions.add("Apakah pembentukan jagung terhambat?");
        
        rustQuestions.add("Apakah terdapat bercak kuning atau coklat di permukaan daun?");
        rustQuestions.add("Apakah terdapot bercak merah di tulang daun?");
        rustQuestions.add("Apakah terdapat benang tidak beraturan berwarna putih lalu kecoklatan?");
        rustQuestions.add("Apakah ada bubuk berwarna kuning kecoklatan?");
    }
    
    public void showInitialQuestions() {
        Scanner scanner = new Scanner(System.in);

        // Pertanyaan G1
        System.out.println("Apakah Daun tanaman berwarna kuning atau pucat?");
        System.out.println("0. Tidak    1. Ya");
        int g1 = scanner.nextInt();
        while (g1 != 0 && g1 != 1) {
            System.out.println("Jawaban tidak sesuai! Masukkan 0 atau 1.");
            g1 = scanner.nextInt();
        }
        answers[0] = g1;

        if (g1 == 1) {
            askBulaiQuestions(scanner);  // Focus on Bulai questions
        } else {
            // G1 = 0, check for G10
            System.out.println("Apakah Daun terlihat kering?");
            System.out.println("0. Tidak    1. Ya");
            int g10 = scanner.nextInt();
            while (g10 != 0 && g10 != 1) {
                System.out.println("Jawaban tidak sesuai! Masukkan 0 atau 1.");
                g10 = scanner.nextInt();
            }
            answers[1] = g10;

            if (g10 == 1) {
                askRustQuestions(scanner);  // Now ask Rust questions
            }
        }
    }

    private void askBulaiQuestions(Scanner scanner) {
        for (int i = 0; i < bulaiQuestions.size(); i++) {
            System.out.println(bulaiQuestions.get(i));
            System.out.println("0. Tidak    1. Ya");
            int response = scanner.nextInt();
            while (response != 0 && response != 1) {
                System.out.println("Jawaban tidak sesuai! Masukkan 0 atau 1.");
                response = scanner.nextInt();
            }
            answers[i + 2] = response;  // Menyimpan jawaban di array
        }
    }
    
    private void askRustQuestions(Scanner scanner) {
        for (int i = 0; i < rustQuestions.size(); i++) { // Use rustQuestions
            System.out.println(rustQuestions.get(i));
            System.out.println("0. Tidak    1. Ya");
            int response = scanner.nextInt();
            while (response != 0 && response != 1) {
                System.out.println("Jawaban tidak sesuai! Masukkan 0 atau 1.");
                response = scanner.nextInt();
            }
            answers[i + 2] = response;  // Corrected to store answers in the right array index
        }
    }
    
    public Set<String> getFacts() {
        Set<String> facts = new HashSet<>();

        if (answers[0] == 1) { // G1
            facts.add("G1");
            if (answers[2] == 1) facts.add("G2");  // G2 (Bulai)
            if (answers[3] == 1) facts.add("G3");  // G3 (Bulai)
            if (answers[4] == 1) facts.add("G4");  // G4 (Bulai)
            if (answers[5] == 1) facts.add("G5");  // G5 (Bulai)
        } else { // If G1 is not present
            if (answers[1] == 1) { // G10
                facts.add("G10");
                if (answers[2] == 1) facts.add("G11"); // G11 (Leaf Rust)
                if (answers[3] == 1) facts.add("G12"); // G12 (Leaf Rust)
                if (answers[4] == 1) facts.add("G13"); // G13 (Leaf Rust)
                if (answers[5] == 1) facts.add("G14"); // G14 (Leaf Rust)
            } 
        }
      
        return facts; 
    }

    
    public void showConclusion(Set<String> detectedDiseases, Set<String> facts) {
        System.out.println("Fakta yang diberikan: " + facts);

        // Menampilkan kesimpulan berdasarkan penyakit yang terdeteksi
        if (detectedDiseases.isEmpty()) {
            System.out.println("Penyakit Tidak Diketahui.");
        } else {
            for (String disease : detectedDiseases) {
                System.out.println("Memeriksa penyakit: " + disease);
                switch (disease) {
                    case "P001":
                            System.out.println("Tanaman terkena penyakit Bulai");
                        break;
                    case "P003":
                            System.out.println("Tanaman terkena penyakit Leaf Rust");
                        break;
                }
            }
        }
    }

}
