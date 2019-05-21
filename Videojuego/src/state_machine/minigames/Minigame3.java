package state_machine.minigames;

import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controllers.KeyboardController;
import hud.EndMiniGameHud;
import main.MainManager;
import minigames.GameObject;
import minigames.MiniGameCurrentState;
import resources.ResourceLoader;

public class Minigame3 extends BasicGameState {

	private final int stateId;

	private final KeyboardController keyboard;

	private MiniGameCurrentState state;
	private final EndMiniGameHud endMiniGame;
	private final MainManager mainManager;

	private Image backgroundImage;
	private Animation block_anim;
	private Animation playerImage;
	private Animation yetiImage;
	private Animation pezImage;
	private Animation penguinImage;
	private Animation tecla1Image, tecla2Image, tecla3Image, tecladImage, teclaiImage;
	private Animation bloquepezImage, bloqueyetiImage, bloquepenguinImage, bloqueprotaderImage, bloqueprotaizqImage;

	private GameObject player;
	private GameObject iceblock;
	private GameObject penguin;
	private GameObject yeti;
	private GameObject pez;
	private GameObject tecla1, tecla2, tecla3, teclad, teclai;

	private String[] personajesizq1 = new String[] { "penguin", "yeti", "pez", "human" };
	private String[] personajesder1 = new String[] { "", "", "", "" };
	private String[] aux1 = new String[] { "", "", "", "" };

	private int x;
	private int y;
	private int vuelta = 0;
	private boolean inicio = true;
	private boolean pulsado = false;

	public Minigame3(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		keyboard = new KeyboardController(640);

		x = 160;
		y = 100;
		this.mainManager = mainManager;
		state = new MiniGameCurrentState();
		endMiniGame = new EndMiniGameHud(mainManager, keyboard);
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image("res/images/minijuegonieve.png");
		playerImage = ResourceLoader.animationfromimage("res/images/protaderecha.png", 64, 64, 100);
		penguinImage = ResourceLoader.animationfromimage("res/images/penguin.png", 64, 64, 100);
		yetiImage = ResourceLoader.animationfromimage("res/images/Sprite-yeti_nieve.png", 225, 300, 100);
		tecla1Image = ResourceLoader.animationfromimage("res/images/teclaa1.png", 64, 64, 100);
		tecla2Image = ResourceLoader.animationfromimage("res/images/teclaa2.png", 64, 64, 100);
		tecla3Image = ResourceLoader.animationfromimage("res/images/teclaa3.png", 64, 64, 100);
		tecladImage = ResourceLoader.animationfromimage("res/images/tecladerecha.png", 64, 64, 100);
		teclaiImage = ResourceLoader.animationfromimage("res/images/teclaizquierda.png", 64, 64, 100);
		pezImage = ResourceLoader.animationfromimage("res/images/Sprite-Pez.png", 64, 64, 100);
		block_anim = ResourceLoader.animationfromimage("res/images/Sprite-BloqueHieloParpadeando.png", 64, 64, 100);
		bloquepezImage = ResourceLoader.animationfromimage("res/images/Sprite-Prota-Pez-Enbloque.png", 128, 128, 100);
		bloqueyetiImage = ResourceLoader.animationfromimage("res/images/Sprite-Prota-Yeti-Enbloque.png", 128, 128, 100);
		bloquepenguinImage = ResourceLoader.animationfromimage("res/images/Sprite-Prota-Pinguino-Enbloque.png", 128,
				128, 100);
		bloqueprotaderImage = ResourceLoader.animationfromimage("res/images/Sprite-BloqueHieloprota.png", 64, 64, 100);
		bloqueprotaizqImage = ResourceLoader.animationfromimage("res/images/Sprite-BloqueHieloprotaizq-sheet.png", 64,
				64, 100);

		iceblock = new GameObject(block_anim, 160, 140, 2.0f);
		penguin = new GameObject(penguinImage, 40, 140, 0.7f);
		tecla1 = new GameObject(tecla1Image, 50, 0, 0.7f);
		tecla2 = new GameObject(tecla2Image, 50, 0, 0.7f);
		tecla3 = new GameObject(tecla3Image, 50, 0, 0.7f);
		teclad = new GameObject(tecladImage, 200, 0, 0.7f);
		teclai = new GameObject(teclaiImage, 700, 0, 0.7f);
		yeti = new GameObject(yetiImage, 80, 30, 0.5f);
		player = new GameObject(playerImage, 70, 90, 2.0f);
		pez = new GameObject(pezImage, 40, 230, 0.5f);
		
		endMiniGame.init(gc);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();

		iceblock.render(g);
		yeti.render(g);
		penguin.render(g);
		pez.render(g);
		// player.render(g);
		tecla1.render(g);
		tecla2.render(g);
		tecla3.render(g);
		teclad.render(g);
		teclai.render(g);
		for (int i = 0; i < personajesizq1.length; i++) {
			g.drawString(personajesizq1[i], 400 + 70 * i, 80);
			g.drawString(personajesder1[i], 400 + 70 * i, 100);
		}
		// yeti.render(g);

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
			if (pulsado == false) {
				switch (keyboard.getPressedpl1(gc)) {
				case ("One"):
					if ((vuelta == 0 && Arrays.stream(personajesizq1).anyMatch("penguin"::equals))
							|| (vuelta == 1 && Arrays.stream(personajesder1).anyMatch("penguin"::equals))) {
						aux1[0] = personajesizq1[0];
						aux1[3] = personajesizq1[3];
						personajesizq1[0] = personajesder1[0];
						personajesizq1[3] = personajesder1[3];
						personajesder1[0] = aux1[0];
						personajesder1[3] = aux1[3];
						aux1 = new String[4];

						iceblock.changeAnimation(bloquepenguinImage, 1.5f);
						iceblock.setY(80);
						iceblock.updateCurrentAnimation(0, 0, 1f);
						penguin.setVisible(false);
						tecla1.setVisible(false);
						player.setVisible(false);
						GameObject.paso = 1;
						vuelta ^= 1;
						pulsado = true;
					}

					break;

				case ("Two"):
					if ((vuelta == 0 && Arrays.stream(personajesizq1).anyMatch("yeti"::equals))
							|| (vuelta == 1 && Arrays.stream(personajesder1).anyMatch("yeti"::equals))) {
						aux1[1] = personajesizq1[1];
						aux1[3] = personajesizq1[3];
						personajesizq1[1] = personajesder1[1];
						personajesizq1[3] = personajesder1[3];
						personajesder1[1] = aux1[1];
						personajesder1[3] = aux1[3];
						aux1 = new String[4];

						iceblock.changeAnimation(bloqueyetiImage, 1.5f);
						iceblock.setY(80);
						iceblock.updateCurrentAnimation(0, 0, 1f);
						yeti.setVisible(false);
						tecla2.setVisible(false);
						player.setVisible(false);
						GameObject.paso = 2;
						vuelta ^= 1;
						pulsado = true;
					}

					break;

				case ("Three"):
					if ((vuelta == 0 && Arrays.stream(personajesizq1).anyMatch("pez"::equals))
							|| (vuelta == 1 && Arrays.stream(personajesder1).anyMatch("pez"::equals))) {
						aux1[2] = personajesizq1[2];
						aux1[3] = personajesizq1[3];
						personajesizq1[2] = personajesder1[2];
						personajesizq1[3] = personajesder1[3];
						personajesder1[2] = aux1[2];
						personajesder1[3] = aux1[3];
						aux1 = new String[4];

						iceblock.changeAnimation(bloquepezImage, 1.5f);
						iceblock.setY(80);
						iceblock.updateCurrentAnimation(0, 0, 1f);
						pez.setVisible(false);
						tecla3.setVisible(false);
						player.setVisible(false);
						GameObject.paso = 3;
						vuelta ^= 1;
						pulsado = true;
					}

					break;

				case ("Left"):
					iceblock.setY(110);
					iceblock.changeAnimation(bloqueprotaizqImage, 2f);
					iceblock.updateCurrentAnimation(0, 0, 1f);
					if (Arrays.stream(personajesder1).anyMatch("human"::equals)) {
						personajesizq1[3] = personajesder1[3];
						personajesder1[3] = "";
					}
					vuelta = 0;
					pulsado = true;
					break;

				case ("Right"):
					iceblock.setY(110);
					iceblock.changeAnimation(bloqueprotaderImage, 2f);
					iceblock.updateCurrentAnimation(0, 0, 1f);
					if (Arrays.stream(personajesizq1).anyMatch("human"::equals)) {
						personajesder1[3] = personajesizq1[3];
						personajesizq1[3] = "";
					}
					vuelta = 1;
					pulsado = true;
				}

			}

			if (vuelta == 0) {
				if (x > 160) {
					iceblock.updateX(x -= 100 * delta / 200f);
				}
				if (x <= 160) {
					iceblock.setY(140);
					iceblock.changeAnimation(bloqueprotaizqImage, 2f);
					iceblock.updateCurrentAnimation(0, 0, 1f);
					pulsado = false;
					switch (GameObject.paso) {
					case 1:
						penguin.setX(Math.abs(gc.getWidth() - penguin.getX()));
						GameObject.paso = 0;
						penguin.setVisible(true);
						tecla1.setVisible(true);
						break;
					case 2:
						yeti.setX(Math.abs(gc.getWidth() - yeti.getX()));
						GameObject.paso = 0;
						yeti.setVisible(true);
						tecla2.setVisible(true);
						break;
					case 3:
						pez.setX(Math.abs(gc.getWidth() - pez.getX()));
						GameObject.paso = 0;
						pez.setVisible(true);
						tecla3.setVisible(true);
						break;
					}
				}
			}
			if (vuelta == 1) {
				if (x < 700) {

					iceblock.updateX(x += 100 * delta / 200f);

				}
				if (x >= 700) {
					iceblock.setY(140);
					iceblock.changeAnimation(bloqueprotaderImage, 2f);
					iceblock.updateCurrentAnimation(0, 0, 1f);
					pulsado = false;
					switch (GameObject.paso) {

					case 1:
						penguin.setX(Math.abs(gc.getWidth() - penguin.getX()));
						GameObject.paso = 0;
						penguin.setVisible(true);
						tecla1.setVisible(true);
						break;
					case 2:
						yeti.setX(Math.abs(gc.getWidth() - yeti.getX()));
						GameObject.paso = 0;
						yeti.setVisible(true);
						tecla2.setVisible(true);
						break;
					case 3:
						pez.setX(Math.abs(gc.getWidth() - pez.getX()));
						GameObject.paso = 0;
						pez.setVisible(true);
						tecla3.setVisible(true);
						break;
					}

				}
			}

			// Comprobacion victoria o derrota
			for (int i = 0; i < personajesizq1.length; i++) {
				if (personajesizq1[0] == "penguin" && personajesizq1[2] == "pez" && personajesizq1[3] != "human") {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(2);
				}
				if (personajesizq1[1] == "yeti" && personajesizq1[0] == "penguin" && personajesizq1[3] != "human") {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(2);
				}
				if (personajesder1[0] == "penguin" && personajesder1[2] == "pez" && personajesder1[3] != "human") {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(2);
				}
				if (personajesder1[1] == "yeti" && personajesder1[0] == "penguin" && personajesder1[3] != "human") {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(2);
				}
				if (personajesder1[0] == "penguin" && personajesder1[1] == "yeti" && personajesder1[2] == "pez"
						&& personajesder1[3] == "human") {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(1);
				}
			}

			tecla1.setX(penguin.getX());
			tecla1.setY(penguin.getY() - 64);
			tecla2.setX(yeti.getX() + 32);
			tecla2.setY(yeti.getY() - 32);
			tecla3.setX(pez.getX());
			tecla3.setY(pez.getY() - 45);

		} else if (state.endMiniGameScreen) {
			endMiniGame.update();
		} else {
			// TODO: PAUSE
		}
	}

	@Override
	public int getID() {
		return stateId;
	}

}
