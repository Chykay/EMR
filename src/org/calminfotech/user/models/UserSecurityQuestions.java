package org.calminfotech.user.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "user_security_questions")
public class UserSecurityQuestions {

	private Integer id;
	private Integer userId;
	private SecurityQuestion questionOne;
	private String answerOne;
	private SecurityQuestion questionTwo;
	private String answerTwo;
	private SecurityQuestion questionThree;
	private String answerThree;
	private SecurityQuestion questionFour;
	private String answerFour;
	private Date createdDate;
	private Date modifiedDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn(name = "question_one")
	public SecurityQuestion getQuestionOne() {
		return questionOne;
	}

	public void setQuestionOne(SecurityQuestion questionOne) {
		this.questionOne = questionOne;
	}

	@Column(name = "answer_one", nullable = false)
	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	@ManyToOne
	@JoinColumn(name = "question_two")
	public SecurityQuestion getQuestionTwo() {
		return questionTwo;
	}

	public void setQuestionTwo(SecurityQuestion questionTwo) {
		this.questionTwo = questionTwo;
	}

	@Column(name = "answer_two", nullable = false)
	public String getAnswerTwo() {
		return answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	@ManyToOne
	@JoinColumn(name = "question_three")
	public SecurityQuestion getQuestionThree() {
		return questionThree;
	}

	public void setQuestionThree(SecurityQuestion questionThree) {
		this.questionThree = questionThree;
	}

	@Column(name = "answer_three", nullable = false)
	public String getAnswerThree() {
		return answerThree;
	}

	public void setAnswerThree(String answerThree) {
		this.answerThree = answerThree;
	}

	@ManyToOne
	@JoinColumn(name = "question_four")
	public SecurityQuestion getQuestionFour() {
		return questionFour;
	}

	public void setQuestionFour(SecurityQuestion questionFour) {
		this.questionFour = questionFour;
	}

	@Column(name = "answer_four", nullable = false)
	public String getAnswerFour() {
		return answerFour;
	}

	public void setAnswerFour(String answerFour) {
		this.answerFour = answerFour;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "modified_date", nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
