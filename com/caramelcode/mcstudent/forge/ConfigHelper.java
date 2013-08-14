package com.caramelcode.mcstudent.forge;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHelper {
	public static final String STUDENT_CATEGORY = "student_category";
	public static final int DEFAULT_QUESTION_MINUTES = 3;
	public static final int ONE_MINUTE_TICKS = 1200;
	public static final int DEFAULT_STUDENT_GRADE = 1;
	private static Configuration config = null;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);

			config.load();

			// TODO: if required config variables are do not exist, create them

			// Create the custom category if it doesn't exist
			config.getCategory(STUDENT_CATEGORY);

			config.save();
		}
	}

	public static Configuration getConfig() {
		return config;
	}

	public static int getQuestionMinutes() {
		int questionMinutes = DEFAULT_QUESTION_MINUTES;

		if (config != null) {
			questionMinutes = config.get(STUDENT_CATEGORY, "question_minutes",
					DEFAULT_QUESTION_MINUTES).getInt();
		}

		return questionMinutes;
	}

	public static int getStudentGrade() {
		int studentGrade = DEFAULT_STUDENT_GRADE;

		if (config != null) {
			studentGrade = config.get(ConfigHelper.STUDENT_CATEGORY, "student_grade",
					DEFAULT_STUDENT_GRADE).getInt();
		}

		return studentGrade;
	}
}
