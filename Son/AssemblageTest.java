public class AssemblageTest {
    public static void main(String[] args) {
        // Initialisation et lecture du fichier sonore Sinusoide.wav
        String nomson = "./Sources sonores/Sinusoide.wav";
        System.out.println("Lecture du fichier WAV " + nomson);
        Son son = new Son(nomson); // Chargement du fichier sonore
        System.out.println("Fichier " + nomson + " : " + son.donnees().length + " échantillons à " + son.frequence() + "Hz");
        System.out.println("Bloc 1 : " + son.bloc_deTaille(1, 512).length + " échantillons à " + son.frequence() + "Hz");

        // Création d'un tableau de complexes pour contenir le signal
        Complexe[] signalTest = new Complexe[512];
        for (int i = 0; i < 512; ++i) {
            // Conversion des données du signal en complexes (partie réelle et imaginaire)
            signalTest[i] = new ComplexeCartesien(son.donnees()[i], 0);
        }

        // Application de la FFT sur le signal
        Complexe[] resultat = FFTCplx.appliqueSur(signalTest);

        // Préparation des données pour l'apprentissage du neurone
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissage[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            // Stockage des parties réelle et imaginaire des résultats de la FFT
            signalTestaprentissage[i][0] = (float) resultat[i].reel();
            signalTestaprentissage[i][1] = (float) resultat[i].imag();
        }
        float resultatAprentissage[] = new float[512];
        for (int i = 0; i < 512; ++i) {
            // Les valeurs cibles pour l'apprentissage sont toutes 1 (sinusoïde)
            resultatAprentissage[i] = (float) 1;
        }
        System.out.println("fin reattribution des valeurs...");

        // Apprentissage du neurone avec les données préparées
        System.out.println("Apprentissage…");
        final iNeurone n = new NeuroneSigmoide(signalTestaprentissage[0].length);
        System.out.println("Nombre de tours : " + n.apprentissage(signalTestaprentissage, resultatAprentissage));

        // Affichage des synapses et du biais du neurone après apprentissage
        final Neurone vueNeurone = (Neurone) n;
        System.out.print("Synapses : ");
        for (final float f : vueNeurone.synapses())
            System.out.print(f +"\t");
        System.out.print("\nBiais : ");
        System.out.println(vueNeurone.biais());

        // Calcul et affichage de la moyenne des sorties pour le signal sinusoïdal
        float sortiesin = 0;
        for (int i = 0; i < signalTestaprentissage.length; ++i) {
            final float[] signaltest = signalTestaprentissage[i];
            n.metAJour(signaltest);
            sortiesin += n.sortie();
        }
        sortiesin = sortiesin / 512;
        System.out.println("moyenne sortie sin : " + sortiesin);

        // Lecture et traitement d'un fichier sonore bruité Carre.wav
        String nomsonbruite = "./Sources sonores/Carre.wav";
        System.out.println("Lecture du fichier WAV " + nomsonbruite);
        Son sonbruite = new Son(nomsonbruite);
        System.out.println("Fichier " + nomsonbruite + " : " + sonbruite.donnees().length + " échantillons à " + sonbruite.frequence() + "Hz");
        System.out.println("Bloc 1 : " + sonbruite.bloc_deTaille(1, 512).length + " échantillons à " + sonbruite.frequence() + "Hz");

        // Conversion des données du signal bruité en complexes
        Complexe[] signalTestbruite = new Complexe[512];
        for (int i = 0; i < 512; ++i) {
            signalTestbruite[i] = new ComplexeCartesien(sonbruite.donnees()[i], 0);
        }

        // Application de la FFT sur le signal bruité
        Complexe[] resultatbruite = FFTCplx.appliqueSur(signalTestbruite);

        // Préparation des données pour l'apprentissage du neurone
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissagebruite[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissagebruite[i][0] = (float) resultatbruite[i].reel();
            signalTestaprentissagebruite[i][1] = (float) resultatbruite[i].imag();
        }
        float resultatAprentissagebruite[] = new float[512];
        for (int i = 0; i < 512; ++i) {
            // Les valeurs cibles pour l'apprentissage sont toutes 0 (non sinusoïde)
            resultatAprentissagebruite[i] = (float) 0;
        }
        System.out.println("fin reattribution des valeurs...");

        // Apprentissage du neurone avec les nouvelles données
        System.out.println("Apprentissage…");
        System.out.println("Nombre de tours : " + n.apprentissage(signalTestaprentissagebruite, resultatAprentissagebruite));

        // Affichage des synapses et du biais du neurone après apprentissage
        final Neurone vueNeuronebruite = (Neurone) n;
        System.out.print("Synapses : ");
        for (final float f : vueNeuronebruite.synapses())
            System.out.print(f +"\t");
        System.out.print("\nBiais : ");
        System.out.println(vueNeuronebruite.biais());

        // Calcul et affichage de la moyenne des sorties pour le signal bruité
        float sortiecarre = 0;
        for (int i = 0; i < signalTestaprentissagebruite.length; ++i) {
            final float[] signaltest = signalTestaprentissagebruite[i];
            n.metAJour(signaltest);
            sortiecarre += n.sortie();
        }
        sortiecarre = sortiecarre / 512;
        System.out.println("moyenne sortie carre : " + sortiecarre);

        // Lecture et traitement d'un second fichier sinusoïdal Sinusoide2.wav
        String nomsin2 = "./Sources sonores/Sinusoide2.wav";
        System.out.println("Lecture du fichier WAV " + nomsin2);
        Son sonsin = new Son(nomsin2);
        System.out.println("Fichier " + nomsin2 + " : " + sonsin.donnees().length + " échantillons à " + sonsin.frequence() + "Hz");
        System.out.println("Bloc 1 : " + sonsin.bloc_deTaille(1, 512).length + " échantillons à " + sonsin.frequence() + "Hz");

        // Conversion des données du second signal sinusoïdal en complexes
        Complexe[] signalTestsin2 = new Complexe[512];
        for (int i = 0; i < 512; ++i) {
            signalTestsin2[i] = new ComplexeCartesien(sonsin.donnees()[i], 0);
        }

        // Application de la FFT sur le second signal sinusoïdal
        Complexe[] resultatsin2 = FFTCplx.appliqueSur(signalTestsin2);

        // Préparation des données pour le test du neurone
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissagesin2[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissagesin2[i][0] = (float) resultatsin2[i].reel();
            signalTestaprentissagesin2[i][1] = (float) resultatsin2[i].imag();
        }
        System.out.println("fin reattribution des valeurs...");

        // Test du neurone avec les nouvelles données sinusoïdales
        for (int i = 0; i < signalTestaprentissagesin2.length; ++i) {
            final float[] entree = signalTestaprentissagesin2[i];
            n.metAJour(entree);
            System.out.println("Entree " + i + " : " + n.sortie());
        }
    }
}
