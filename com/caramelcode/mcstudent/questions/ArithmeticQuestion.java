package com.caramelcode.mcstudent.questions;

import com.caramelcode.mcstudent.util.NumberHelper;

public class ArithmeticQuestion implements Question {
	private int[] operands;
	private char operator;
	private int gradeLevel;
	private String answer;
	private String questionText;

	public ArithmeticQuestion(char operator, int gradeLevel) {
		this.operands = new int[2];
		this.operator = operator;
		this.gradeLevel = gradeLevel;

		switch (gradeLevel) {
			case 1:
				initFirstGrade();
				break;
			case 2:
				initSecondGrade();
				break;
			case 3:
				initThirdGrade();
				break;
			case 4:
				initForthGrade();
				break;
			default:
				initFirstGrade();
				break;
		}
	}

	private void initFirstGrade() {
		int leftOperand = NumberHelper.randRange(1, 10);
		int rightOperand = NumberHelper.randRange(1, 10);

		operands[0] = leftOperand;
		operands[1] = rightOperand;

		switch (operator) {
			case '+':
				answer = Integer.toString(operands[0] + operands[1]);
				break;
			case '-':
				if (leftOperand < rightOperand) {
					// swap operands to avoid negative number answers
					operands[0] = rightOperand;
					operands[1] = leftOperand;
				}
				answer = Integer.toString(operands[0] - operands[1]);
				break;
			default:
				// if first grader gets a weird operator, default to +
				operator = '+';
				answer = Integer.toString(operands[0] + operands[1]);
				break;
		}

		questionText = String.format("%d %s %d = ", operands[0], operator, operands[1]);
	}

	private void initSecondGrade() {
		int leftOperand = NumberHelper.randRange(5, 30);
		int rightOperand = NumberHelper.randRange(5, 30);

		operands[0] = leftOperand;
		operands[1] = rightOperand;

		switch (operator) {
			case '+':
				answer = Integer.toString(operands[0] + operands[1]);
				break;
			case '-':
				if (leftOperand < rightOperand) {
					// swap operands to avoid negative number answers
					operands[0] = rightOperand;
					operands[1] = leftOperand;
				}
				answer = Integer.toString(operands[0] - operands[1]);
				break;
			default:
				operator = '+';
				answer = Integer.toString(operands[0] + operands[1]);
				break;
		}

		questionText = String.format("%d %s %d = ", operands[0], operator, operands[1]);
	}

	private void initThirdGrade() {
		int leftOperand = NumberHelper.randRange(20, 50);
		int rightOperand = NumberHelper.randRange(20, 50);

		operands[0] = leftOperand;
		operands[1] = rightOperand;

		switch (operator) {
			case '+':
				answer = Integer.toString(operands[0] + operands[1]);
				break;
			case '-':
				if (leftOperand < rightOperand) {
					// swap operands to avoid negative number answers
					operands[0] = rightOperand;
					operands[1] = leftOperand;
				}
				answer = Integer.toString(operands[0] - operands[1]);
				break;
			case 'x':
				operands[0] = leftOperand = NumberHelper.randRange(0, 10);
				operands[1] = rightOperand = NumberHelper.randRange(0, 10);
				answer = Integer.toString(operands[0] * operands[1]);
				break;
			default:
				operator = '+';
				answer = Integer.toString(operands[0] + operands[1]);
				break;
		}

		questionText = String.format("%d %s %d = ", operands[0], operator, operands[1]);
	}

	private void initForthGrade() {
		int leftOperand = NumberHelper.randRange(2, 12);
		int rightOperand = NumberHelper.randRange(2, 12);

		operands[0] = leftOperand;
		operands[1] = rightOperand;

		switch (operator) {
			case 'x':
				answer = Integer.toString(operands[0] * operands[1]);
				break;
			case '/':
				do {
					operands[0] = leftOperand = NumberHelper.randRange(1, 50);
					operands[1] = rightOperand = NumberHelper.randRange(1, 10);
					
					if (leftOperand < rightOperand) {
						operands[0] = rightOperand;
						operands[1] = leftOperand;
					}
				} while (NumberHelper.hasRemainder(operands[0], operands[1]));
				
				answer = Integer.toString(operands[0] / operands[1]);

				break;
			default:
				answer = Integer.toString(operands[0] * operands[1]);
				break;
		}

		questionText = String.format("%d %s %d = ", operands[0], operator, operands[1]);
	}

	public int[] getOperands() {
		return operands;
	}

	public void setOperands(int[] operands) {
		this.operands = operands;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
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
