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
        System.out.println("Taille resultat : " + resultat.length);
        for (int i = 0; i < resultat.length; ++i) {
            System.out.print(i + " : (" + (float) resultat[i].reel() + " ; " + (float) resultat[i].imag() + "i)");
            System.out.println(", (" + (float) resultat[i].mod() + " ; " + (float) resultat[i].arg() + " rad)");
        }
        //On crée un neurone qui apprends la FFT
        System.out.println("Apprentissage…");
        final iNeurone n = new NeuroneSigmoide(512);
        // On lance l'apprentissage de la fonction FFT sur ce neurone
        System.out.println("reatribution des valeurs...");
        float signalTestaprentissage[][] = new float[512][2];
        for (int i = 0; i < 512; ++i) {
            signalTestaprentissage[i][0] = (float) resultat[i].reel();
            signalTestaprentissage[i][1] = (float) resultat[i].imag();
        }
        float resultatAprentissage[] = new float[512];
        for (int i = 0; i < 512; ++i) {
            resultatAprentissage[i] = (float) resultat[i].mod();
        }
        System.out.println("fin reattribution des valeurs...");



    }
}