package com.caramelcode.mcstudent.questions;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion implements IQuestion {
	private String questionText;
	private String answer;
	private List<String> distractors = new ArrayList<String>();
	
	public MultipleChoiceQuestion(String questionText, String answer, List<String> distractors) {
		super();
		this.questionText = questionText;
		this.answer = answer;
		this.distractors = distractors;
	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	public List<String> getDistractors() {
		return distractors;
	}

	public void setDistractors(List<String> distractors) {
		this.distractors = distractors;
	}
}
