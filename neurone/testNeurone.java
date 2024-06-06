public class testNeurone
{
	public static void main(String[] args)
	{
		// Tableau des entrées de la fonction ET (0 = faux, 1 = vrai)
		final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
		float perturbation = 0.1F;

		final float[][] entrees_bruités = new float[entrees.length][entrees[0].length];
		//attribution des valeurs bruitées entre >0 et <1
		// ne pas sortir de l'intervalle [0,1]
		for (int i = 0; i < entrees.length; ++i)
		{
			for (int j = 0; j < entrees[i].length; ++j)
			{
				entrees_bruités[i][j] = entrees[i][j] + (float)(Math.random()*2*perturbation-perturbation);
				if (entrees_bruités[i][j] < 0)
					entrees_bruités[i][j] = 0;
				else if (entrees_bruités[i][j] > 1)
					entrees_bruités[i][j] = 1;
			}
		}


		
		// Tableau des sorties de la fonction ET
		final float[] resultats = {0, 1, 1, 1};
		
		// On crée un neurone taillé pour apprendre la fonction ET
		final iNeurone n = new NeuroneHeaviside(entrees[0].length);
		//final iNeurone n = new NeuroneSigmoide(entrees[0].length);
		//final iNeurone n = new NeuroneReLU(entrees[0].length);
		
		System.out.println("Apprentissage…");
		// On lance l'apprentissage de la fonction ET sur ce neurone
		System.out.println("Nombre de tours : "+n.apprentissage(entrees, resultats));
		
		// On affiche les valeurs des synapses et du biais

		// Conversion dynamique d'une référence iNeurone vers une référence neurone.
		// Sans cette conversion on ne peut pas accéder à synapses() et biais()
		// à partir de la référence de type iNeurone
		// Cette conversion peut échouer si l'objet derrière la référence iNeurone
		// n'est pas de type neurone, ce qui n'est cependant pas le cas ici
		final Neurone vueNeurone = (Neurone)n;
		System.out.print("Synapses : ");
		for (final float f : vueNeurone.synapses())
			System.out.print(f+" ");
		System.out.print("\nBiais : ");
		System.out.println(vueNeurone.biais());
		
		// On affiche chaque cas appris
		for (int i = 0; i < entrees.length; ++i)
		{
			// Pour une entrée donnée
			final float[] entree = entrees[i];
			// On met à jour la sortie du neurone
			n.metAJour(entree);
			// On affiche cette sortie
			System.out.println("Entree "+i+" : "+n.sortie());
		}

		System.out.print("\nEntrees bruités : \n");
		//afficher le tableau des entres bruités
		for (int i = 0; i < entrees_bruités.length; ++i)
		{
			for (int j = 0; j < entrees_bruités[i].length; ++j)
				System.out.print(entrees_bruités[i][j]+" ");
			System.out.print("\n");
		}

		//boucle pour tester a avec des entrees bruités
		for (int i = 0; i < entrees.length; ++i)
		{
			// Pour une entrée donnée
			final float[] entree = entrees_bruités[i];
			// On met à jour la sortie du neurone
			n.metAJour(entree);
			// On affiche cette sortie
			System.out.println("Entree "+i+" : "+n.sortie());
		}
	}
}
