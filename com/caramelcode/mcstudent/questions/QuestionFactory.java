package com.caramelcode.mcstudent.questions;

import java.util.List;

import com.caramelcode.mcstudent.QuestionPackLoader;
import com.caramelcode.mcstudent.forge.ConfigHelper;
import com.caramelcode.mcstudent.util.NumberHelper;

public class QuestionFactory {
	private QuestionFactory() {
	}

	public static ITextfieldQuestion buildQuestion() {
		// TODO: Add more question types, and return a random one
		// buildArithmeticQuestion();
		List<List> questions = QuestionPackLoader
				.readWithCsvListReader("questionpacks/beginning_spanish.csv");
		
		ITextfieldQuestion question = new GenericTextQuestion("question", "answer");
		
		for (List<String> q : questions) {
			//System.out.println(String.format("*** QUESTION: %s", q));
			question = new GenericTextQuestion(q.get(0), q.get(1));
		}

		return question;
	}

	private static ITextfieldQuestion buildArithmeticQuestion() {
		ArithmeticQuestion question = null;
		int studentGrade = ConfigHelper.getStudentGrade();
		int opIndex;

		switch (studentGrade) {
			case 1:
				opIndex = NumberHelper.randRange(1, 2); // include +, -
				break;
			case 2:
			case 3:
				opIndex = NumberHelper.randRange(1, 3); // include: +, -, x
				break;
			case 4:
				opIndex = NumberHelper.randRange(3, 4); // include: x, /
				break;
			default:
				opIndex = 1; // default to just Plus (+)
				break;
		}

		// pick a random operation and formulate the question
		switch (opIndex) {
			case 1:
				question = new ArithmeticQuestion('+', studentGrade);
				break;
			case 2:
				question = new ArithmeticQuestion('-', studentGrade);
				break;
			case 3:
				question = new ArithmeticQuestion('x', studentGrade);
				break;
			case 4:
				question = new ArithmeticQuestion('/', studentGrade);
				break;
		}

		return question;
	}
}
