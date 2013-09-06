package com.caramelcode.mcstudent.questions;

public class QuestionChooser {
	private QuestionChooser() {
	}

	public static IQuestion chooseQuestion() {
		IQuestion question = null;
		if (QuestionPackPool.hasQuestions()) {
			// TODO: change this to get next unasked question
			// TODO: if all questions have been asked, then favor questions that
			//       were answered wrong
			question = QuestionPackPool.getRandomQuestion();
		} else {
			question = MathFactQuestionFactory.buildArithmeticQuestion();
		}

		return question;
	}
}
