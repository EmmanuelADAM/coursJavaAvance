package TP.TPNN;

/**
 * classe contenant les données à apprendre
 * Ici, chaque colone represente la presence ou non d'un mot dans un mailn; excepte la derniere colonne qui indique
 * si le mail est critique (1) ou non (0)
 * Les donnees represente ce scenario :
 * 'bombe', 'feu' => danger
 * 'bombe', 'peinture' => _
 * 'bombe', 'pistolet' => danger
 * 'pistolet', 'peinture' => _
 * 'pistolet', 'balle' => danger
 * 'tete', 'balle' => danger
 * 'balle', 'tennis' => _
 * 'gun' => danger
 * 'gun', 'roses' => _
 * 'attaquant' => danger
 * 'attaquant', 'sport' => _
 * 'descendre' => danger
 * 'descendre', 'bierre' => _
 * 'descendre', 'escalier' => _
 * @author emmanuel adam
 * */
public class Data {
    /**bombe, feu, peinture, pistolet, balle, tete, tennis, gun, roses, attaquant, Sport, descendre, bierre,
     * escalier,  danger*/
     static double[][]data = {
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0}};
}
