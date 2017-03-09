package co.in.where2learn_new.model;

import java.util.ArrayList;

public class CoachingFilterModel {
 
	private ArrayList<String> board = new ArrayList<String>();
	private ArrayList<String> classes = new ArrayList<String>();
	private ArrayList<String> subject = new ArrayList<String>();
	public ArrayList<String> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<String> board) {
		this.board = board;
	}
	public ArrayList<String> getClasses() {
		return classes;
	}
	public void setClasses(ArrayList<String> classes) {
		this.classes = classes;
	}
	public ArrayList<String> getSubject() {
		return subject;
	}
	public void setSubject(ArrayList<String> subject) {
		this.subject = subject;
	} 
	
	

}
