package com.growingitskill.domain;

public class CategoryLevel {

	private long level1;
	private long level2;
	private long level3;
	private long level4;

	public long getLevel1() {
		return level1;
	}

	public void setLevel1(long level1) {
		this.level1 = level1;
	}

	public long getLevel2() {
		return level2;
	}

	public void setLevel2(long level2) {
		this.level2 = level2;
	}

	public long getLevel3() {
		return level3;
	}

	public void setLevel3(long level3) {
		this.level3 = level3;
	}

	public long getLevel4() {
		return level4;
	}

	public void setLevel4(long level4) {
		this.level4 = level4;
	}

	@Override
	public String toString() {
		return "Category Level [level1: " + level1 + ", level2: " + level2 + ", level3: " + level3 + ", level4: "
				+ level4 + "]";
	}

}
