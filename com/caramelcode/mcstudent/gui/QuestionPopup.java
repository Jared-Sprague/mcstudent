package com.caramelcode.mcstudent.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.caramelcode.mcstudent.forge.AssetHelper;
import com.caramelcode.mcstudent.forge.MCStudentModInfo;
import com.caramelcode.mcstudent.questions.MathTextFieldQuestion;
import com.caramelcode.mcstudent.questions.TextFieldQuestion;
import com.google.common.base.Strings;
import com.mcf.davidee.guilib.basic.Label;
import com.mcf.davidee.guilib.core.Button;
import com.mcf.davidee.guilib.core.Button.ButtonHandler;
import com.mcf.davidee.guilib.core.Container;
import com.mcf.davidee.guilib.core.TextField;
import com.mcf.davidee.guilib.core.TextField.CharacterFilter;
import com.mcf.davidee.guilib.vanilla.ButtonVanilla;
import com.mcf.davidee.guilib.vanilla.TextFieldVanilla;

import cpw.mods.fml.common.network.PacketDispatcher;

public class QuestionPopup extends MCStudentPopup {

	private Container container;
	private Label titleLabel, questionLabel;
	private Button submit;
	private TextField answerField;
	private TextFieldQuestion question;

	private boolean validInput() {
		String text = answerField.getText();
		if (Strings.isNullOrEmpty(text)) {
			return false;
		}

		return true;
	}

	public QuestionPopup() {
		super(null);
		question = new MathTextFieldQuestion();
	}

	@Override
	public void updateScreen() {
		submit.setEnabled(validInput());
		super.updateScreen();
	}

	@Override
	protected void revalidateGui() {
		int startY = (height - HEIGHT) / 2;

		titleLabel.setPosition(width / 2, startY + 5);
		questionLabel.setPosition(width / 2, startY + 40);
		answerField.setPosition(width / 2 - 75, startY + 55);
		submit.setPosition(width / 2 - 60, startY + 100);

		container.revalidate(0, 0, width, height);
	}

	@Override
	protected void createGui() {
		container = new Container();

		titleLabel = new Label("Question", 0, 0);
		questionLabel = new Label(question.getQuestionText(), 0, 0);
		titleLabel.setShadowedText(false);
		questionLabel.setShadowedText(false);
		submit = new ButtonVanilla(120, 20, "Answer", new ButtonHandler() {
			public void buttonClicked(Button button) {
				if (answerField.getText().equalsIgnoreCase(question.getAnswer())) {
					// answer was correct play the correct answer sound
					Minecraft.getMinecraft().sndManager.playSoundFX(AssetHelper.SOUND_CORRECT, 3.0F, 1.0F);
					
					// Give player a Diamond reward. TODO: vary this reward based on correct answer streaks
					PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(MCStudentModInfo.CHANNEL, new byte[]{1}));
					
					close();
				} else {
					// answer was wrong, get a new question and update the popup
					question = new MathTextFieldQuestion();
					questionLabel.setText(question.getQuestionText());
				}
			}
		});
		submit.setEnabled(false);

		answerField = new TextFieldVanilla(150, 20, 0xffa09172, 0xff373737, new CharacterFilter() {
			public String filter(String s) {
				StringBuilder sb = new StringBuilder();
				for (char c : s.toCharArray())
					if (isAllowedCharacter(c))
						sb.append(c);
				return sb.toString();
			}

			public boolean isAllowedCharacter(char c) {
				return Character.isLetterOrDigit(c);
			}
		});

		answerField.setMaxLength(23);

		container.addWidgets(titleLabel, questionLabel, answerField, submit);

		containers.add(container);
		selectedContainer = container;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
}
