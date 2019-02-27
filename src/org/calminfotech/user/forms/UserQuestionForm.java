package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class UserQuestionForm {

	private int questionNum;
	
	private String question;
	
	@NotEmpty(message = "Answer field cannot be empty")
	private String answer;

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
