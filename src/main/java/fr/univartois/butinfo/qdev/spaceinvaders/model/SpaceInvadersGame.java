/*
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d'aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d'adéquation
 * à un usage particulier et d'absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d'auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d'un contrat, d'un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d'autres éléments du logiciel.
 *
 * (c) 2022-2026 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.qdev.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe SpaceInvadersGame permet de gérer une partie du jeu Space-Invaders.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class SpaceInvadersGame {


    private static final Random random = new Random();
    /**
     * La vitesse du vaisseau du joueur lorsqu'il se déplace (en pixels/s).
     */
    private static final double SHIP_SPEED = 150;

    /**
     * La temporisation contraignant le temps entre deux tirs successifs (en
     * millisecondes).
     */
    private static final long SHOT_TEMPORIZATION = 500;

    /**
     * La temporisation contraignant le délai entre deux apparitions de bonus.
     */
    private static final long BONUS_TEMPORIZATION = 20000;

    /**
     * La largeur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     */
    private final int width;

    /**
     * La hauteur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;

    /**
     * L'instance de {@link IAnimatedFactory} permettant de créer les objets du jeu.
     */
    private IAnimatedFactory factory;

    /**
     * Le vaisseau du joueur.
     */
    public IAnimated ship;

    /**
     * Le délai depuis la dernière apparition d'un bonus.
     * Lorsque ce délai atteint 20 secondes, un nouveau bonus peut apparaître.
     */
    private long lastBonusDrop = 0;

    /**
     * Le timestamp du dernier tir.
     * Il permet de s'assurer que le joueur ne tire pas trop souvent.
     */
    private long lastShot = 0;

    /**
     * Le nombre d'aliens encore vivants.
     */
    private int nbRemainingAliens;

    /**
     * Le nombre de vies restantes pour le joueur.
     */
    private final IntegerProperty life = new SimpleIntegerProperty();

    /**
     * Le score du joueur (en nombre d'aliens éliminés).
     */
    private final IntegerProperty score = new SimpleIntegerProperty();

    /**
     * La liste des objets mobiles du jeu.
     */
    private final List<IAnimated> movingObjects = new CopyOnWriteArrayList<>();

    /**
     * La liste des objets animés du jeu.
     */
    private final List<IAnimated> animatedObjects = new CopyOnWriteArrayList<>();

    private List<IAnimated> aliens =  new CopyOnWriteArrayList<>();

    /**
     * L'animation du jeu, qui permet aux différents objets de se déplacer.
     */
    private final AnimationTimer animation = new GameAnimation(this, movingObjects, animatedObjects);

    /**
     * Le contrôleur du jeu.
     */
    private ISpaceInvadersController controller;

    private int niveau = 1;

    private static final int NIVEAU_MAX = 3;

    /**
     * L'escadron regroupant les aliens (composite), qui permet de les déplacer
     * ensemble et de gérer leurs collisions.
     */
    private AlienSquadron alienSquadron;

    /**
     * Crée une nouvelle instance de SpaceInvadersGame.
     *
     * @param width La largeur sur laquelle les objets du jeu peuvent se déplacer
     *        (en pixels).
     * @param height La hauteur sur laquelle les objets du jeu peuvent se déplacer
     *        (en pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory L'instance de {@link IAnimatedFactory} permettant de créer les
     *        objets du jeu.
     */
    public SpaceInvadersGame(int width, int height, ISpriteStore spriteStore,
                             IAnimatedFactory factory) {
        this.width = width;
        this.height = height;
        this.spriteStore = spriteStore;
        this.factory = factory;
    }

    /**
     * Associe à cette partie de Space-Invaders le contrôleur gérant l'affichage du jeu.
     *
     * @param controller Le contrôleur de l'application.
     */
    public void setController(ISpaceInvadersController controller) {
        this.controller = controller;
    }

    /**
     * Donne la largeur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     *
     * @return La largeur du jeu.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la limite à gauche pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite à gauche pour la position des objets mobiles du jeu.
     */
    public int getLeftLimit() {
        return 10;
    }

    /**
     * Donne la limite à droite pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite à droite pour la position des objets mobiles du jeu.
     */
    public int getRightLimit() {
        return width - 10;
    }

    /**
     * Donne la hauteur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     *
     * @return La hauteur du jeu.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne la limite en haut pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite en haut pour la position des objets mobiles du jeu.
     */
    public int getTopLimit() {
        return 10;
    }

    /**
     * Donne la limite en bas pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite en bas pour la position des objets mobiles du jeu.
     */
    public int getBottomLimit() {
        return height - 100;
    }

    public void setFactory(IAnimatedFactory factory) {
        this.factory = factory;
        factory.setSpriteStore(spriteStore);
        factory.setGame(this);
    }

    private void niveauSuivant(){
        niveau++;
        setFactory(new LevelFactory(niveau));
        createAnimated();
    }

    /**
     * Prépare une partie de Space-Invaders sur le contrôleur de l'application.
     */
    public void prepare() {
        factory.setSpriteStore(spriteStore);
        factory.setGame(this);

        controller.setSpriteStore(spriteStore);
        controller.prepare();
        controller.bindScore(score);
        controller.bindLife(life);
    }

    /**
     * Démarre cette partie de Space-Invaders.
     * Cette méthode permet également de redémarrer une nouvelle partie.
     */
    public void start() {
        niveau=1;
        setFactory(new LevelFactory(niveau));
        initStatistics();
        createAnimated();
        animation.start();
    }

    /**
     * Crée les différents objets animés présents au début de la partie.
     */
    private void createAnimated() {
        // On commence par enlever tous les objets animés encore présents.
        clearAnimated();
        alienSquadron = new AlienSquadron();

        // Création du vaisseau
        int x = (getWidth() - spriteStore.getSpriteSize())/2;
        int y = getBottomLimit();

        ship = factory.createShip(x, y);
        addMoving(ship);

        // Création des vaisseaux aliens
        int NB_ALIENS = 5;
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 3; j++) {
                nbRemainingAliens ++;
                int alienX = (getWidth() - spriteStore.getSpriteSize())/2 + j * spriteStore.getSpriteSize();
                int alienY = getTopLimit() + i * spriteStore.getSpriteSize();

                // créer l'alien, l'ajouter, incrémenter le compteur
                IAnimated alien = factory.createAlien(alienX, alienY);
                aliens.add(alien);
                controller.addAnimated(alien);
                alienSquadron.addAlien(alien);
            }
        }
        animatedObjects.add(alienSquadron);


        // Création des murs de protection
        int NB_WALLS = 3;
        for(int i = 0; i <= NB_WALLS; i++){
            int wallX = i * getWidth() / (NB_WALLS + 1) - spriteStore.getSpriteSize();
            int wallY = getBottomLimit() - 2 * spriteStore.getSpriteSize();
            addAnimated(factory.createWall(wallX, wallY));
        }
    }

    /**
     * Initialise les statistiques de cette partie.
     */
    private void initStatistics() {
        life.set(3);
        score.set(0);
        nbRemainingAliens = 0;
    }

    /**
     * Modifie l'état du jeu après un certain délai.
     *
     * @param timeDelta Le temps écoulé depuis la dernière mise à jour du jeu
     *        (en millisecondes).
     */
    public void onStep(long timeDelta) {
        // On regarde s'il est temps de déposer un bonus.
        lastBonusDrop += timeDelta;
        if (lastBonusDrop >= BONUS_TEMPORIZATION) {
            dropBonus();
            lastBonusDrop = 0;
        }
    }

    /**
     * Fait apparaître aléatoirement un bonus.
     * Cette méthode est appelée régulièrement, avec un délai de
     * {@link #BONUS_TEMPORIZATION} entre chaque appel.
     *
     * @see #onStep(long)
     */
    private void dropBonus() {
        long maintenant = System.currentTimeMillis();

        if (maintenant - lastBonusDrop > BONUS_TEMPORIZATION) {
            int x = random.nextInt(getLeftLimit(), getRightLimit() - spriteStore.getSpriteSize());
            addMoving(factory.createBonus(x, getTopLimit()));
        }
    }

    /**
     * Déplace le vaisseau du joueur vers la gauche.
     */
    public void moveLeft() {
        ship.setHorizontalSpeed(-SHIP_SPEED);
    }

    /**
     * Déplace le vaisseau du joueur vers la droite.
     */
    public void moveRight() {
        ship.setHorizontalSpeed(SHIP_SPEED);
    }

    /**
     * Arrête le déplacement du vaisseau du joueur.
     */
    public void stopMoving() {
        ship.setHorizontalSpeed(0);
    }

    /**
     * Déclenche un tir depuis le vaisseau du joueur.
     * Cette méthode est sans effet si le délai entre deux tirs n'est pas atteint.
     */
    public void fireShot() {
        long maintenant = System.currentTimeMillis();

        if (maintenant - lastShot > SHOT_TEMPORIZATION) {
            lastShot=maintenant;
            addMoving(factory.createShot(ship.getX(), ship.getY() - 35, -300));
        }
    }

    public void alienShot(IAnimated alien) {
        if (!aliens.isEmpty() && aliens.getLast().equals(alien)) {
            addMoving(factory.createShot(alien.getX(), alien.getY() + 30, 200));
        }
    }

    /**
     * Met à jour le score du joueur lorsqu'un alien est tué.
     * Si c'était le dernier, le joueur gagne la partie.
     *
     * @param alien L'alien qui a été tué.
     */
    public void alienIsDead(IAnimated alien) {
        removeAnimated(alien);
        aliens.remove(alien);
        alienSquadron.removeAlien(alien);
        nbRemainingAliens--;
        if (nbRemainingAliens == 0) {
            if (niveau < NIVEAU_MAX) {
                niveauSuivant();
            }
            else {
                animation.stop();
                controller.gameOver("You win !");
            }
        }
    }

    /**
     * Réduit la vie du joueur, et interrompt la partie si elle atteint 0.
     */
    public void reducePlayerLife() {
        life.set(life.get() - 1);
        if (life.get()==0){
            playerIsDead();
        }
    }

    /**
     * Augmente la vie du joueur en cas de bonus.
     */
    public void augmentPlayerLife() {
        life.set(life.get()+1);
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        animation.stop();
        controller.gameOver("Le joueur est mort !");
    }

    /**
     * Termine la partie lorsque les aliens atteignent la planète.
     */

    public void alienReachedPlanet() {
        animation.stop();
        controller.gameOver("Les aliens ont envahi la planète !");
    }

    public void reverseAliens() {
        alienSquadron.reverseHorizontally();
    }

    /**
     * Ajoute un objet mobile dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addMoving(IAnimated object) {
        movingObjects.add(object);
        addAnimated(object);
    }

    /**
     * Ajoute un objet animé dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addAnimated(IAnimated object) {
        animatedObjects.add(object);
        controller.addAnimated(object);
        object.onSpawn();
    }

    /**
     * Supprime un objet animé dans le jeu.
     *
     * @param object L'objet à supprimer.
     */
    public void removeAnimated(IAnimated object) {
        animatedObjects.remove(object);
        movingObjects.remove(object);
        object.onDespawn();
        object.onDestruction();
    }

    /**
     * Supprime tous les objets animés dans le jeu.
     */
    private void clearAnimated() {
        for (var animated : animatedObjects) {
            animated.onDespawn();
            animated.onDestruction();
        }
        animatedObjects.clear();
        movingObjects.clear();
    }

}