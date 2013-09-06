package com.caramelcode.mcstudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class QuestionPackLoader {

	/**
	 * Sets up the processors used to parse the question pack. There are upto 5
	 * CSV columns, so 5 processors are defined. Empty columns are read as null
	 * (hence the NotNull() for mandatory columns).
	 * 
	 * @return the cell processors
	 */
	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] { 
				new NotNull(), // Question
				new NotNull(), // Correct_Answer
				new Optional(), // Multiple Choice Distractor_1
				new Optional(), // Multiple Choice Distractor_2
				new Optional(), // Multiple Choice Distractor_3
		};

		return processors;
	}

	/**
	 * An example of reading using CsvListReader.
	 */
	public static List<List> readWithCsvListReader(File file) {
		ICsvListReader listReader = null;
		List<List> questions = new ArrayList<List>();

		try {
			listReader = new CsvListReader(new FileReader(file),
					CsvPreference.STANDARD_PREFERENCE);

			listReader.getHeader(true); 
			
			List<String> questionRowList;
			while ((questionRowList = listReader.read()) != null) {
				questions.add(questionRowList);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (listReader != null) {
				try {
					listReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return questions;
	}

}
