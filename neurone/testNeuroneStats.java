import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class testNeuroneStats
{
    public static void main(String[] args) {
        // Nom du fichier de sortie
        String tsvFile = "neuroneStats.tsv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tsvFile))) {
            // Écrire l'en-tête du fichier TSV
            writer.write("Cycle\tSynapses\tBiais\n");

            // Boucle pour générer les données de test
            for (int c = 0; c < 100; c++) {
                final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
                final float[] resultats = {0, 0, 0, 1};

                final iNeurone n = new NeuroneHeaviside(entrees[0].length);
                System.out.println("Nombre de tours : " + n.apprentissage(entrees, resultats));

                final Neurone vueNeurone = (Neurone) n;
                System.out.print("Synapses : ");
                StringBuilder synapses = new StringBuilder();
                for (final float f : vueNeurone.synapses()) {
                    System.out.print(f + " ");
                    synapses.append(f).append(" ");
                }
                System.out.print("\nBiais : ");
                float biais = vueNeurone.biais();
                System.out.println(biais);

                // Écrire les résultats dans le fichier TSV
                writer.write(c + 1 + "\t" + synapses.toString().trim() + "\t" + biais + "\n");
            }

            System.out.println("Données de test écrites dans le fichier TSV avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
