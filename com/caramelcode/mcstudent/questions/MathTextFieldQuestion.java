package com.caramelcode.mcstudent.questions;

import java.util.ArrayList;
import java.util.Random;

import com.caramelcode.mcstudent.forge.ConfigHelper;

public class MathTextFieldQuestion implements TextFieldQuestion {
	private String questionText;
	private String answer;
	private int leftOperand;
	private int rightOperand;
	private String operator;

	public MathTextFieldQuestion() {
		int studentGrade = ConfigHelper.getStudentGrade();
		int opIndex = 1; // default to just Plus (+)

		switch (studentGrade) {
			case 2:
			case 3:
				opIndex = rand(3); // include: +, -, x
				break;
			case 4:
				opIndex = rand(3); // TODO: make / work and change this to a 4
									// // include: /
				break;
		}

		switch (studentGrade) {
			case 1:
				leftOperand = rand(10);
				rightOperand = rand(10);
			case 2:
				leftOperand = rand(30);
				rightOperand = rand(20);
				break;
			case 3:
				leftOperand = rand(50);
				rightOperand = rand(50);
				break;
			case 4:
				leftOperand = rand(100);
				rightOperand = rand(100);
				break;
			default:
				leftOperand = rand(10);
				rightOperand = rand(10);
				break;
		}

		// pick a random operation and get the answer
		switch (opIndex) {
			case 1:
				operator = "+";
				answer = Integer.toString(leftOperand + rightOperand);
				break;
			case 2:
				operator = "-";
				if (leftOperand > rightOperand) {
					answer = Integer.toString(leftOperand - rightOperand);
				} else {
					// swap operands to avoid negative answers
					int tempOperand = leftOperand;
					leftOperand = rightOperand;
					rightOperand = tempOperand;
					answer = Integer.toString(leftOperand - rightOperand);
				}
				break;
			case 3:
				operator = "x";
				leftOperand = rand(10);
				rightOperand = rand(10);
				answer = Integer.toString(leftOperand * rightOperand);
				break;
			case 4:
				operator = "/";
				leftOperand = rand(100);
				rightOperand = rand(10);

				if (leftOperand < rightOperand) {
					// swap operands to avoid weird answers
					int tempOperand = leftOperand;
					leftOperand = rightOperand;
					rightOperand = tempOperand;
				}

				answer = Double.toString(Math.floor(leftOperand / rightOperand));
		}

		questionText = String.format("%d %s %d = ?", leftOperand, operator, rightOperand);

	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	private int rand(int limitInclusive) {
		Random r = new Random();
		return r.nextInt(limitInclusive) + 1;
	}
}
