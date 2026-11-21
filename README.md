# Inscryption — jeu de cartes en Java

Jeu de cartes stratégique en ligne de commande inspiré d'**Inscryption** (Daniel Mullins Games), développé en **Java** avec une approche **orientée objet**.

> _Vous vous retrouvez dans une cabane, perdu en pleine forêt. Dans l'obscurité, attablé face à vous se dresse un adversaire aux yeux inquiétants qui vous défie à un étrange jeu de cartes..._

Projet réalisé en binôme.

---

## Compétences mises en œuvre

- **Programmation orientée objet** : héritage, polymorphisme, encapsulation, interfaces
- **Conception logicielle** : architecture extensible, pensée pour ajouter de nouvelles cartes et de nouveaux pouvoirs sans modifier le code existant
- **Modélisation UML** : diagrammes de classes (PlantUML) tenus à jour au fil du projet
- **Tests unitaires** avec **JUnit 4**
- **Gestion des erreurs** : validation des saisies utilisateur avec messages explicites
- **Travail en équipe** avec Git (branches, commits réguliers, livraisons hebdomadaires)

---

## Règles du jeu

### Le plateau
- Un plateau de **deux lignes de quatre emplacements** : le joueur pose ses cartes sur la ligne du bas, l'adversaire sur celle du haut.
- Une **ligne supplémentaire** au-dessus du plateau annonce les cartes que l'adversaire jouera au tour suivant.
- Une **balance de score** : le premier joueur qui obtient un écart de **5 points** en sa faveur remporte la partie.
- Une **pioche** : le joueur commence avec 4 cartes en main et peut piocher une carte par tour.
- Des **obstacles** (rocher, sapin) peuvent occuper des emplacements au début de la partie et doivent être détruits avant qu'on puisse y poser une carte.

### Les cartes animaux
Chaque carte possède :
- des points d'**attaque** et des points de **vie** ;
- un coût en **gouttes de sang** : nombre de ses propres cartes sur le plateau à sacrifier pour la poser ;
- un coût en **os** : nombre de ses cartes déjà mortes (tuées ou sacrifiées) nécessaire pour la poser.

### Déroulement d'un tour
1. L'adversaire annonce les cartes qu'il jouera au prochain tour.
2. Le joueur peut piocher une carte.
3. Le joueur pose autant de cartes qu'il le souhaite (dans la limite des emplacements libres et des sacrifices requis).
4. En fin de tour, chaque carte attaque :
   - si une carte adverse lui fait face, celle-ci perd autant de points de vie que l'attaque ;
   - sinon, l'attaque s'ajoute au score ;
   - les cartes **volantes** attaquent toujours directement le score.
5. Un message récapitule les dégâts infligés, puis l'adversaire joue à son tour.

### Progression
- Le joueur débute avec une pioche de **15 cartes**, majoritairement des écureuils.
- Le jeu se compose de **trois parties** : il faut toutes les gagner pour remporter le jeu.
- À la fin de la deuxième partie, le joueur choisit une nouvelle carte parmi deux propositions.
- **Pierre de sacrifice** : le joueur sacrifie ensuite une carte pour récupérer son pouvoir et le transmettre à une autre carte animal.

L'adversaire est contrôlé par le programme avec une stratégie **déterministe**, ce qui rend le jeu reproductible et testable.

---

## Cartes

### Animaux

| Nom        | Attaque | PV | Sang | Os | Volant | Pouvoir          |
|------------|:-------:|:--:|:----:|:--:|:------:|------------------|
| Écureuil   | 0       | 1  | 0    | 0  |        |                  |
| Chat       | 0       | 1  | 1    | 0  |        | Nombreuses vies  |
| Hermine    | 1       | 3  | 1    | 0  |        |                  |
| Louveteau  | 1       | 1  | 1    | 0  |        | Croissance       |
| Moineau    | 1       | 2  | 1    | 0  | ✓      |                  |
| Porc-épic  | 1       | 2  | 1    | 0  |        | Piques pointues  |
| Loup       | 3       | 2  | 2    | 0  |        |                  |
| Corbeau    | 2       | 3  | 2    | 0  | ✓      |                  |
| Élan       | 2       | 4  | 2    | 0  |        | Coureur          |
| Vipère     | 1       | 1  | 2    | 0  |        | Contact mortel   |
| Grizzly    | 4       | 6  | 3    | 0  |        |                  |
| Punaise    | 1       | 2  | 0    | 2  |        | Puant            |
| Coyote     | 2       | 1  | 0    | 4  |        |                  |

### Obstacles

| Nom    | PV |
|--------|:--:|
| Rocher | 5  |
| Sapin  | 3  |

### Pouvoirs

| Pouvoir          | Effet |
|------------------|-------|
| Nombreuses vies  | Reste sur le plateau lorsqu'elle est sacrifiée. |
| Croissance       | Se transforme en loup au début de son deuxième tour sur le plateau. |
| Puant            | Réduit de 1 l'attaque de la carte qui lui fait face. |
| Coureur          | Se déplace d'un emplacement vers la droite après avoir attaqué (vers la gauche si la droite est bloquée, reste en place si les deux le sont). |
| Contact mortel   | Tue toute créature à laquelle elle inflige des dégâts (sans effet sur les obstacles). |
| Piques pointues  | Inflige 1 point de dégât à toute carte qui l'attaque. |

---

## Aperçu

```
Another challenger... It has been ages.
    Partie1                                                 Score 0                                                     
                                                                                                                        
    0er Tour:                                                                                                           
                                                                                                                        
         *-----------* ************* ************* *************                                                        
         | Loup      | *           * *           * *           *                                                        
         |-----------| *           * *           * *           *                                                        
         | PV: 2     | *           * *           * *           *                                                        
         | ATT: 3    | *           * *           * *           *                                                        
         |           | *           * *           * *           *                                                        
         |           | *           * *           * *           *                                                        
         *-----------* ************* ************* *************                                                        
              ||            ||            ||            ||                                                              
              \/            \/            \/            \/                                                              
         ************* ************* ************* *************                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         *     A1    * *     A2    * *     A3    * *     A4    *                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         ************* ************* ************* *************                                                        
                                                                                                                        
                                                                                                                        
         ************* ************* ************* *************                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         *     B1    * *     B2    * *     B3    * *     B4    *                                                        
         *           * *           * *           * *           *                                                        
         *           * *           * *           * *           *                                                        
         ************* ************* ************* *************                                                        
                                                                                                                        
  Votre main :                                                                          Pioche       Pioche ecureuil    
    1. Punaise    PV: 2  Att: 1  Sang: 0  Os: 2 Pouvoir: Puant                       *************    *************     
    2. Loup       PV: 2  Att: 3  Sang: 2  Os: 0                                      *           *    *           *     
    3. Ecureuil   PV: 1  Att: 0  Sang: 0  Os: 0                                      *           *    *           *     
    4. Hermine    PV: 3  Att: 1  Sang: 1  Os: 0                                      *           *    *           *     
                                                                                     *           *    *           *     
                                                                                     *           *    *           *     
                                                                                     *           *    *           *     
Quantité d'Os : 0                                                                    *************    *************     
                                                                                       1 carte(s)       10 carte(s)     
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
[placer] Placer une carte sur le plateau
[piocher] Piocher une carte
[fin] Finir son tour
[quitter] Quitter le jeu
Votre choix : 

```

---

## Structure du projet

```
├── deps/       # Dépendances (JUnit 4, Hamcrest)
├── out/        # Fichiers compilés
├── src/        # Code source (point d'entrée : Main.java)
├── tests/      # Tests unitaires JUnit
└── uml/        # Diagrammes de classes PlantUML
```
---

## Lancer le jeu

**Prérequis :** Java 11 ou supérieur.

```bash
# Compilation
javac -d out $(find src -name "*.java")

# Exécution
java -cp out Main
```

---

_Projet réalisé à des fins pédagogiques, inspiré du jeu Inscryption. Aucune affiliation avec Daniel Mullins Games._
