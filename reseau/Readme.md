# Base de la programmation réseau en Java

## ServeurTCP et ClientTCP 
Echange entre 1 client et  1 serveur en connection TCP
- Lancer le Serveur en 1er `ServeurTCP`, puis le Client `ClientTCP`.
- Le serveur attend une connection sur socket TCP du client, puis attend de lui des textes, confirme la bonne réception.
- Le client attend un texte du l'utilisateur, l'envoi au serveur tant que l'utilisateur n'a pas entré `FIN`
