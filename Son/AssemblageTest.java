public class AssemblageTest {
    public static void main(String[] args) {
        String nomson = "./Sources sonores/Sinusoide.wav";
        System.out.println("Lecture du fichier WAV " + nomson);
        Son son = new Son(nomson);
        System.out.println("Fichier " + nomson + " : " + son.donnees().length + " échantillons à " + son.frequence() + "Hz");
        System.out.println("Bloc 1 : " + son.bloc_deTaille(1, 512).length + " échantillons à " + son.frequence() + "Hz");

        Complexe[] signalTest = new Complexe[512];
        for (int i = 0; i < 512; ++i) {

            //COMMENT MODIFIER LE SIGNAL D'ENTREE :
            //La classe ComplexeCartesien est composé d'une partie reelle et d'une partie imaginaire
            //C'est donc ici en remplaçant le 0 par un signal que nous pouvons placer sur l'axe imaginaire
            signalTest[i] = new ComplexeCartesien(son.donnees()[i], 0);
        }


        // On applique la FFT sur ce signal
        Complexe[] resultat = FFTCplx.appliqueSur(signalTest);
        // On affiche les valeurs du résultat

        //On crée un neurone qui apprends la FFT



        // On lance l'apprentissage de la fonction FFT sur ce neurone
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissage[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissage[i][0] = (float) resultat[i].reel();
            signalTestaprentissage[i][1] = (float) resultat[i].imag();
        }
        float resultatAprentissage[] = new float[512];
        for (int i = 0; i < 512; ++i) {
            resultatAprentissage[i] = (float) 1;
        }
        System.out.println("fin reattribution des valeurs...");


        System.out.println("Apprentissage…");
        final iNeurone n = new NeuroneSigmoide(signalTestaprentissage[0].length);
        System.out.println("Nombre de tours : " + n.apprentissage(signalTestaprentissage, resultatAprentissage));




        final Neurone vueNeurone = (Neurone) n;
        System.out.print("Synapses : ");
        for (final float f : vueNeurone.synapses())
            System.out.print(f +"\t");
        System.out.print("\nBiais : ");
        System.out.println(vueNeurone.biais());


        float sortiesin = 0;
        for (int i = 0; i < signalTestaprentissage.length; ++i) {
            // Pour une entrée donnée
            final float[] signaltest = signalTestaprentissage[i];
            // On met à jour la sortie du neurone
            n.metAJour(signaltest);
            // On affiche cette sortie
            sortiesin+= n.sortie();
        }
        //moyenne sortie sin
        sortiesin = sortiesin/512;

        // appliquer desormais la meme procedure au signal bruité bruits.wav
        String nomsonbruite = "./Sources sonores/Carre.wav";
        System.out.println("Lecture du fichier WAV " + nomsonbruite);
        Son sonbruite = new Son(nomsonbruite);
        System.out.println("Fichier " + nomsonbruite + " : " + sonbruite.donnees().length + " échantillons à " + sonbruite.frequence() + "Hz");
        System.out.println("Bloc 1 : " + sonbruite.bloc_deTaille(1, 512).length + " échantillons à " + sonbruite.frequence() + "Hz");

        Complexe[] signalTestbruite = new Complexe[512];
        for (int i = 0; i < 512; ++i) {

            //COMMENT MODIFIER LE SIGNAL D'ENTREE :
            //La classe ComplexeCartesien est composé d'une partie reelle et d'une partie imaginaire
            //C'est donc ici en remplaçant le 0 par un signal que nous pouvons placer sur l'axe imaginaire
            signalTestbruite[i] = new ComplexeCartesien(sonbruite.donnees()[i], 0);
        }

        // On applique la FFT sur ce signal
        Complexe[] resultatbruite = FFTCplx.appliqueSur(signalTestbruite);
        // On affiche les valeurs du résultat

        //On utilise le meme seuronne et on lui apprends a reconnaitre que ce n'est pas une sinusoide
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissagebruite[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissagebruite[i][0] = (float) resultatbruite[i].reel();
            signalTestaprentissagebruite[i][1] = (float) resultatbruite[i].imag();
        }
        float resultatAprentissagebruite[] = new float[512];
        for (int i = 0; i < 512; ++i) {
            resultatAprentissagebruite[i] = (float) 0;
        }
        System.out.println("fin reattribution des valeurs...");

        System.out.println("Apprentissage…");
       //on utilise le meme neuronne et on lui apprend a reconnaitre que ce n'est pas une sinusoide
        System.out.println("Nombre de tours : " + n.apprentissage(signalTestaprentissagebruite, resultatAprentissagebruite));

        final Neurone vueNeuronebruite = (Neurone) n;
        System.out.print("Synapses : ");
        for (final float f : vueNeuronebruite.synapses())
            System.out.print(f +"\t");
        System.out.print("\nBiais : ");
        System.out.println(vueNeuronebruite.biais());


        float sortiecarre = 0;
        for (int i = 0; i < signalTestaprentissagebruite.length; ++i) {
            // Pour une entrée donnée
            final float[] signaltest = signalTestaprentissagebruite[i];
            // On met à jour la sortie du neurone
            n.metAJour(signaltest);
            // On affiche cette sortie
            sortiecarre = n.sortie();
        }
        //moyenne sortie carre
        sortiecarre = sortiecarre/512;

        System.out.println("moyenne sortie carre : " + sortiecarre);
        System.out.println("moyenne sortie sin : " + sortiesin);










        //tester le neuronne avec un signal carré different
        String nomsin2 = "./Sources sonores/Sinusoide2.wav";
        System.out.println("Lecture du fichier WAV " + nomsin2);
        Son sonsin = new Son(nomsin2);
        System.out.println("Fichier " + nomsin2 + " : " + sonsin.donnees().length + " échantillons à " + sonsin.frequence() + "Hz");
        System.out.println("Bloc 1 : " + sonsin.bloc_deTaille(1, 512).length + " échantillons à " + sonsin.frequence() + "Hz");

        Complexe[] signalTestsin2 = new Complexe[512];
        for (int i = 0; i < 512; ++i) {

            //COMMENT MODIFIER LE SIGNAL D'ENTREE :
            //La classe ComplexeCartesien est composé d'une partie reelle et d'une partie imaginaire
            //C'est donc ici en remplaçant le 0 par un signal que nous pouvons placer sur l'axe imaginaire
            signalTestsin2[i] = new ComplexeCartesien(sonsin.donnees()[i], 0);
        }

        // On applique la FFT sur ce signal
        Complexe[] resultatsin2 = FFTCplx.appliqueSur(signalTestsin2);
        // On affiche les valeurs du résultat


        System.out.println("reatribution des valeurs...");
        float signalTestaprentissagesin2[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissagesin2[i][0] = (float) resultatsin2[i].reel();
            signalTestaprentissagesin2[i][1] = (float) resultatsin2[i].imag();
        }

        System.out.println("fin reattribution des valeurs...");

        //On utilise le meme neuronne pour tester si ce signal en entree est reconnu comme une sinusoide

        for (int i = 0; i < signalTestaprentissagesin2.length; ++i) {

            // Pour une entrée donnée
            final float[] entree = signalTestaprentissagesin2[i];
            // On met à jour la sortie du neurone
            n.metAJour(entree);
            // On affiche cette sortie
            System.out.println("Entree " + i + " : " + n.sortie());
            //writer.write(  "entree bruitée : (" + (float) entrees_bruités[i][0] + " ; " + (float) entrees_bruités[i][1] + ")");
        }


        }
}