public class NeuroneSigmoide extends Neurone{
    // Fonction d'activation d'un neurone (peut facilement être modifiée par héritage)

    //COMMENT CREER UN NEURONE SIGMOIDE :
    //Nous avons suivi exactement la même structure que le neurone Heaviside donnée,
    //en changeant uniquement la valeur du return par la fonction d'activation sigmoide vu en cours.
    protected float activation(final float valeur) {return (float) (1/(1+Math.exp(-valeur)));}

    // Constructeur
    public NeuroneSigmoide(final int nbEntrees) {super(nbEntrees);}
}
