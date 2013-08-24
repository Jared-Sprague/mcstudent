package com.caramelcode.mcstudent.gui;

import org.lwjgl.input.Keyboard;

import com.caramelcode.mcstudent.RewardMachine;
import com.mcf.davidee.guilib.basic.Label;
import com.mcf.davidee.guilib.core.Button;
import com.mcf.davidee.guilib.core.Button.ButtonHandler;
import com.mcf.davidee.guilib.core.Container;
import com.mcf.davidee.guilib.vanilla.ButtonVanilla;

public class CorrectAnswerPopup extends MCStudentPopup {

	private Container container;
	private Label titleLabel, praiseLabel, rewardLabel, streakLabel;
	private Button submit;

	public CorrectAnswerPopup() {
		super(null);
		RewardMachine.getRewardItemStack();  //initialize the static reward string
	}

	@Override
	protected void revalidateGui() {
		int startY = (height - HEIGHT) / 2;

		titleLabel.setPosition(width / 2, startY + 5);
		praiseLabel.setPosition(width / 2, startY + 30);
		rewardLabel.setPosition(width / 2, startY + 50);
		streakLabel.setPosition(width / 2, startY + 70);
		submit.setPosition(width / 2 - 60, startY + 100);

		container.revalidate(0, 0, width, height);
	}

	@Override
	protected void createGui() {
		container = new Container();
		titleLabel = new Label("Correct Answer!", 0, 0);
		praiseLabel = new Label(RewardMachine.getPraiseMessage(), 0, 0);
		rewardLabel = new Label("You earned: " + RewardMachine.getRewardString() + "!", 0, 0);
		
		String streakMessage = "";
		if (RewardMachine.getCorrectCount() > 1) {
			streakMessage = RewardMachine.getCorrectCount() + " Correct Streak!";
		}
		streakLabel = new Label(streakMessage, 0, 0);
		titleLabel.setShadowedText(false);
		praiseLabel.setShadowedText(false);
		rewardLabel.setShadowedText(false);
		streakLabel.setShadowedText(false);
		submit = new ButtonVanilla(120, 20, "Done", new ButtonHandler() {
			public void buttonClicked(Button button) {
				close();
			}
		});
		submit.setEnabled(true);

		container.addWidgets(titleLabel, praiseLabel, rewardLabel, streakLabel, submit);

		containers.add(container);
		selectedContainer = container;
	}

	@Override
	protected void reopenedGui() {
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@Override
	protected void unhandledKeyTyped(char c, int code) {
		if (code == Keyboard.KEY_RETURN) {
			close();
		}
	}
}
