package com.caramelcode.mcstudent.questions;

public class GenericTextQuestion implements ITextfieldQuestion {
	private String questionText;
	private String answer;

	public GenericTextQuestion(String questionText, String answer) {
		super();
		this.questionText = questionText;
		this.answer = answer;
	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public String getAnswer() {
		return answer;
	}

}
