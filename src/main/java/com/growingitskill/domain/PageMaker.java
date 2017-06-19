package com.growingitskill.domain;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean previous;
	private boolean next;
	
	private int displayPageNum = 5;
	
	private Criteria criteria;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calculateData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	private void calculateData() {
		endPage = (int) Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum;
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) Math.ceil((totalCount / (double) criteria.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		previous = (startPage == 1) ? false : true;
		next = (endPage * criteria.getPerPageNum() >= totalCount) ? false : true;
	}
	
	@Override
	public String toString() {
		return "totalCount: " + getTotalCount() + ", endPage: " + getEndPage() + ", startPage: " + getStartPage();
	}

}
