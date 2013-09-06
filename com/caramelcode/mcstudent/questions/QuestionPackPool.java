package com.caramelcode.mcstudent.questions;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;

import com.caramelcode.mcstudent.QuestionPackLoader;
import com.caramelcode.mcstudent.util.NumberHelper;

/**
 * This class contains all the questions loaded from questionpacks in a pool
 * that can be selected from.
 * 
 * @author jsprague
 * 
 */
public class QuestionPackPool {
	private static List<IQuestion> questionPool = new ArrayList<IQuestion>();

	private QuestionPackPool() {
	}

	// TODO: Add method to get next unasked question
	// TODO: Add method to get next wrong answered question or something

	public static void loadQuestionPacks() {
		File dir = new File("questionpacks");
		FileFilter fileFilter = new WildcardFileFilter("*.csv");
		File[] filesArray = dir.listFiles(fileFilter);
		List<File> filesList = new ArrayList<File>();
		filesList.addAll(Arrays.asList(filesArray));
		
		// add .CSV files too
		fileFilter = new WildcardFileFilter("*.CSV");
		filesArray = dir.listFiles(fileFilter);
		filesList.addAll(Arrays.asList(filesArray));
		
		File[] files = filesList.toArray(new File[filesList.size()]);

		for (int i = 0; i < files.length; i++) {
			List<List> questions = QuestionPackLoader
					.readWithCsvListReader(files[i]);

			for (List<String> q : questions) {
				String questionText = null;
				String answer = null;
				List<String> distractors = new ArrayList<String>();

				for (int j = 0; j < q.size(); j++) {
					if (j == 0 && !StringUtils.isBlank(q.get(j))) {
						questionText = q.get(j); // Get question text from col 1
					} else if (j == 1 && !StringUtils.isBlank(q.get(j))) {
						answer = q.get(j); // Get correct answer from col 2
					} else if (j == 2 && !StringUtils.isBlank(q.get(j))) {
						distractors.add(q.get(j)); // Get distractor 1 from col
													// 3 if it exists
					} else if (j == 3 && !StringUtils.isBlank(q.get(j))) {
						distractors.add(q.get(j)); // Get distractor 2 from col
													// 4 if it exists
					} else if (j == 4 && !StringUtils.isBlank(q.get(j))) {
						distractors.add(q.get(j)); // Get distractor 3 from col
													// 5 if it exists
					}
				}

				if (!StringUtils.isBlank(questionText) && !StringUtils.isBlank(answer)) {
					if (distractors.size() > 0) {
						questionPool.add(new MultipleChoiceQuestion(questionText, answer,
								distractors));
					} else {
						questionPool.add(new GenericTextQuestion(questionText, answer));
					}

					// TODO: Randomize the ordero of question pool so they are
					// not always asked in same order
				}
			}
		}
	}

	public static IQuestion getRandomQuestion() {
		int upperLimit = questionPool.size() - 1;
		return questionPool.get(NumberHelper.rand(upperLimit));
	}

	public static boolean hasQuestions() {
		return (questionPool.size() > 0);
	}
}
