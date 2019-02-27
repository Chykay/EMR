package org.calminfotech.user.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
public class UserQuestionsForm {

	@Range(min = 1, message = "Please select a question")
	private Integer questionOne;
	@NotBlank(message = "Field cannot be empty")
	private String answerOne;
	@Range(min = 1, message = "Please select a question")
	private Integer questionTwo;
	@NotBlank(message = "Field cannot be blank")
	private String answerTwo;
	@Range(min = 1, message = "Please select a question")
	private Integer questionThree;
	@NotBlank(message = "Field cannot be blank")
	private String answerThree;
	@Range(min = 1, message = "Please select a question")
	private Integer questionFour;
	@NotBlank(message = "Field cannot be empty")
	private String answerFour;

	public Integer getQuestionOne() {
		return questionOne;
	}

	public void setQuestionOne(Integer questionOne) {
		this.questionOne = questionOne;
	}

	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	public Integer getQuestionTwo() {
		return questionTwo;
	}

	public void setQuestionTwo(Integer questionTwo) {
		this.questionTwo = questionTwo;
	}

	public String getAnswerTwo() {
		return answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	public Integer getQuestionThree() {
		return questionThree;
	}

	public void setQuestionThree(Integer questionThree) {
		this.questionThree = questionThree;
	}

	public String getAnswerThree() {
		return answerThree;
	}

	public void setAnswerThree(String answerThree) {
		this.answerThree = answerThree;
	}

	public Integer getQuestionFour() {
		return questionFour;
	}

	public void setQuestionFour(Integer questionFour) {
		this.questionFour = questionFour;
	}

	public String getAnswerFour() {
		return answerFour;
	}

	public void setAnswerFour(String answerFour) {
		this.answerFour = answerFour;
	}

}
