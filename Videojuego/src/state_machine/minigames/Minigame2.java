package state_machine.minigames;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import controllers.KeyboardController;
import hud.EndMiniGameHud;
import main.MainManager;
import minigames.GameObject;
import minigames.MiniGameCurrentState;
import resources.Coordinates;
import resources.ResourceLoader;

public class Minigame2 extends BasicGameState {

	private final int stateId;

	private final KeyboardController keyboard;
	private MiniGameCurrentState state;
	private final EndMiniGameHud endMiniGame;
	private final MainManager mainManager;

	private Image backgroundImage;
	private Animation ballImage;
	private Animation thunderZoneImage;
	private Animation shockImage;
	private Animation playerImage;

	private GameObject[] ballarray;
	private GameObject player;
	private ArrayList<GameObject> zonethunder;

	private final int numberBalls;

	private int speedBall;
	private int stopTime;
	private int speedTimeDelay;
	private int stopTimeDelay;
	private int puntuacion;
	private Ellipse circulo;
	private int colision = 0;

	private ArrayList<Coordinates> coordinates = new ArrayList<>();

	public enum estados {
		Movimiento, Espera, Calculo
	}

	private estados estado_juego = estados.Movimiento;
	private int x;
	private int y;

	/*
	 * Constructors
	 */
	public Minigame2(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;

		keyboard = new KeyboardController(640);
		numberBalls = 4;
		ballarray = new GameObject[numberBalls];
		zonethunder = new ArrayList<GameObject>();
		speedTimeDelay = 100;
		stopTimeDelay = 350;
		circulo = new Ellipse(520, 290, 295f, 230f);
		state = new MiniGameCurrentState();
		this.mainManager = mainManager;
		state = new MiniGameCurrentState();
		endMiniGame = new EndMiniGameHud(mainManager, keyboard);
		x = 400;
		y = 100;

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = ResourceLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_BACKGROUND);
		ballImage = ResourceLoader.animationfromimage(Constants.PATH_MINIGAME2_BALL, 64, 64);
		thunderZoneImage = ResourceLoader.animationfromimage(Constants.PATH_MINIGAME2_THUNDER_ZONE, 64, 64);
		shockImage = ResourceLoader.animationfromimage(Constants.PATH_MINIGAME2_SHOCK, 64, 64);
		playerImage = ResourceLoader.animationfromimage("res/images/prota_movimiento.png", 64, 64, 10000);

		player = new GameObject(playerImage, 400, 100, 2.0f); // Export values to constants

		for (int i = 0; i < numberBalls; i++) {
			ballarray[i] = new GameObject(ballImage, 0, 0, 2.0f); // Export values to constants
		}

		endMiniGame.init(gc);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();

		g.draw(circulo);
		player.render(g);
		for (GameObject go : zonethunder) {
			go.render(g);
		}
		for (GameObject go : ballarray) {
			go.render(g);
		}

		// State
		if (state.endMiniGameScreen) {
			endMiniGame.render(gc, g);
		} else if (state.gamePaused) {
			// TODO: Pausa
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!state.gamePaused && !state.endMiniGameScreen) {

			if (!circulo.contains(player.getCollisionBox())) {
				player.setX(x -= keyboard.getXMovementPl1() * delta / 50f);
				player.setY(y -= keyboard.getYMovementPl1() * delta / 50f);
				player.getCollisionBox().setX(x);
				player.getCollisionBox().setY(y);
				if (x > 800 || x < 200 || y > 512 || y < 0) {
					player.setX(x = 460);
					player.setY(y = 220);
					player.getCollisionBox().setX(x);
					player.getCollisionBox().setY(y);
				}

			} else {
				if (colision != 1) {
					player.updateX(x += keyboard.getXMovementPl1() * delta / 400f);
					player.updateY(y += keyboard.getYMovementPl1() * delta / 400f);
				}
			}

			if (keyboard.getXMovementPl1() == 100) {
				player.updateCurrentAnimation(15, 18, 2f);
			}
			if (keyboard.getXMovementPl1() == -100) {
				player.updateCurrentAnimation(25, 28, 2f);
			}
			if (keyboard.getYMovementPl1() == 100) {
				player.updateCurrentAnimation(20, 24, 2f);
			}
			if (keyboard.getYMovementPl1() == -100) {
				player.updateCurrentAnimation(0, 3, 2f);
			}
			if (keyboard.getXMovementPl1() == 0 && keyboard.getYMovementPl1() == 0) {
				player.updateCurrentAnimation(20, 20, 2f);
			}

			if (speedBall++ < speedTimeDelay) {
				updateMove();

			}

			else {
				if (estado_juego == estados.Movimiento) {
					estado_juego = estados.Calculo;
				}
				if (estado_juego == estados.Calculo) {
					for (int i = 1; i < numberBalls; i++) {

						for (int j = 0; j < (numberBalls - i); j++) {
							if (j != 1) {
								coordenadas_recta_disparo(ballarray[j].getX(), ballarray[j].getY(), ballarray[i].getX(),
										ballarray[i].getY());
							}
							// Se crean objetos en los puntos calculados
							for (int k = 0; k < coordinates.size(); k++) {
								GameObject aux = new GameObject(thunderZoneImage, coordinates.get(k).getX(),
										coordinates.get(k).getY(), 1.0f);
								aux.updateCurrentAnimation(0, 1, 1.25f);
								zonethunder.add(aux);

							}
							coordinates.clear();
						}

					}
					estado_juego = estados.Espera;

				}

			}

			if (estado_juego == estados.Espera) {
				if (stopTime > stopTimeDelay * 0.4) {
					for (int i = 0; i < numberBalls; i++) {
						ballarray[i].updateCurrentAnimation(0, 8, 2f);
					}
				}

				if (stopTime > stopTimeDelay / 2) {
					for (GameObject go : zonethunder) {

						go.changeAnimation(shockImage, 1f);
						go.updateCurrentAnimation(0, 2, 1.25f);

						if (go.getCollisionBox().intersects(player.getCollisionBox())) {
							player.updateCurrentAnimation(34, 34, 2f);
							colision = 1;
							// habría que poner un delay
							state.endMiniGameScreen = true;
							endMiniGame.setPlayerWinner(2);
						}
					}
				}
			}

			if (stopTime++ > stopTimeDelay) {
				speedBall = 0;
				stopTime = 0;
				estado_juego = estados.Movimiento;
				zonethunder.clear();
				if (colision == 0) {
					puntuacion += 10;
				}
				colision = 0;
				for (int i = 0; i < numberBalls; i++) {
					ballarray[i].updateCurrentAnimation(0, 0, 2f);

				}
			}

			if (puntuacion == 40) {
				state.endMiniGameScreen = true;
				endMiniGame.setPlayerWinner(1);
			}

		} else if (state.endMiniGameScreen) {
			endMiniGame.update();
		} else {
			// TODO: PAUSE
		}
	}

	private void updateMove() {
		double posX, posY;
		double theta = 0;
		int r = 256;

		for (int i = 0; i < numberBalls; i++) {

			// Evito colisiones entre bolas
			theta = ThreadLocalRandom.current().nextDouble(i * 2 * Math.PI / numberBalls,
					(i + 1) * 2 * Math.PI / numberBalls);

			posX = r * Math.cos(theta) + 460;
			posY = r * Math.sin(theta) + 220;

			ballarray[i].setX((int) posX);
			ballarray[i].setY((int) posY);
		}

	}

	private void coordenadas_recta_disparo(int x0, int y0, int x1, int y1) {
		int x2, y2;
		int stepX, stepY, p;
		int dx = x1 - x0;
		int dy = y1 - y0;

		if (dx < 0) {
			dx *= -1;
			stepX = -1;
		} else {
			stepX = 1;
			dx = x1 - x0;
		}
		if (dy < 0) {
			dy *= -1;
			stepY = -1;
		} else {
			stepY = 1;
		}
		x2 = x0;
		y2 = y0;
		if (dx > dy) {
			p = 2 * dy - dx;
			while (x2 != x1) {
				x2 += stepX;
				if (p < 0) {
					p += 2 * dy;
				} else {
					p += 2 * (dy - dx);
					y2 += stepY;
				}
				if ((Math.sqrt(Math.pow(x2 - x0, 2) + Math.pow(y2 - y0, 2))) > 64) {
					coordinates.add(new Coordinates(x2, y2));
					x0 = x2;
					y0 = y2;
				}
			}
		} else {
			p = 2 * dx - dy;

			while (y2 != y1) {
				y2 += stepY;
				if (p < 0) {
					p += 2 * dx;
				} else {
					p += 2 * (dx - dy);
					x2 += stepX;
				}
				if ((Math.sqrt(Math.pow(x2 - x0, 2) + Math.pow(y2 - y0, 2))) > 64) {
					coordinates.add(new Coordinates(x2, y2));
					x0 = x2;
					y0 = y2;
				}
			}
		}
	}

	@Override
	public int getID() {
		return stateId;
	}
}
