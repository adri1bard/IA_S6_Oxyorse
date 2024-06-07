import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class testEntreeBruite {

    public static void main(String[] args) {
        float perturbation = 0.05F;
        final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        final float[] resultats = {0, 1, 1, 1};
        String tsvFile = "neuroneBruits.tsv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TestBruits"+perturbation+".tsv"))) {
            // Écrire l'en-tête du fichier TSV
            writer.write("Iteration\tEntree Bruitee1\tEntree Bruitee2\tSortie\n");

            for (int iteration = 0; iteration < 100; iteration++) {
                final float[][] entrees_bruitees = new float[entrees.length][entrees[0].length];

                // Générer les entrées bruitées
                for (int i = 0; i < entrees.length; ++i) {
                    for (int j = 0; j < entrees[i].length; ++j) {
                        entrees_bruitees[i][j] = entrees[i][j] + (float) (Math.random() * 2 * perturbation - perturbation);
                        if (entrees_bruitees[i][j] < 0)
                            entrees_bruitees[i][j] = 0;
                        else if (entrees_bruitees[i][j] > 1)
                            entrees_bruitees[i][j] = 1;
                    }
                }

                // Créer et apprendre le neurone avec les entrées bruitées
                final iNeurone n = new NeuroneHeaviside(entrees[0].length);
                n.apprentissage(entrees, resultats);

                for (int i = 0; i < entrees_bruitees.length; ++i) {
                    final float[] entree = entrees_bruitees[i];
                    n.metAJour(entree);

                    // Écrire les résultats dans le fichier TSV
                    writer.write((iteration + 1) + "\t" + entrees_bruitees[i][0] + "\t" + entrees_bruitees[i][1] + "\t" + n.sortie() + "\n");
                }
            }

            System.out.println("Données de test écrites dans le fichier TSV avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}