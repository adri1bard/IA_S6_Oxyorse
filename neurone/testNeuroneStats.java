import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class testNeuroneStats
{
    public static void main(String[] args) {
        // Nom du fichier de sortie
        String tsvFile = "neuroneStats.tsv";

        //COMMENT REALISER DES STATISTIQUES SUR LES POIDS DE NOS NEURONES :
        //On va donc réaliser 100 fois l'apprentissage,
        //puis écrire ces résultats dans un fichier TSV,
        //l'ouvrir avec excel pour calculer les moyennes et ecarts types

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tsvFile))) {
            // Écrire l'en-tête du fichier TSV
            writer.write("Synapse 1\tSynapse 2\tBiais\n");

            // Boucle pour générer les données de test
            for (int c = 0; c < 100; c++) {
                final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
                final float[] resultats = {0, 1, 1, 1};

                final iNeurone n = new NeuroneSigmoide(entrees[0].length);
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
                for (final float g : vueNeurone.synapses()) {
                    writer.write(g +"\t");
                }
                writer.write(biais + "\n");
            }

            System.out.println("Données de test écrites dans le fichier TSV avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
