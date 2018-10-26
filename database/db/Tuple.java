package db;

public class Tuple {
	private String name;
	private String course_name;
	private Integer score;

	/**
	 * @param datastring String consists of Tuple information
	 */
	public Tuple(String datastring) {
		String[] str;
		str = datastring.split(",");

		this.name = str[0];
		this.course_name = str[1].substring(1);
		this.score = Integer.parseInt(str[2].substring(1));
	}

	public void printTuple() {
		System.out.println(name + ", " + course_name + ", " + score);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object ob) {
		if (!(ob instanceof Tuple))
			return false;
		final Tuple o = (Tuple) ob;
		if (this.name.equals(o.name) && this.course_name.equals(o.course_name) && this.score.equals(o.score))
			return true;
		else
			return false;
	}

	public String toString() {
		String tuplestring = this.name + ", " + this.course_name + ", " + this.score.toString();
		return tuplestring;
	}

	public String getName() {
		return this.name;
	}

	public String getCourse() {
		return this.course_name;
	}

	public Integer getScore() {
		return this.score;
	}

	/**
	 * @param tuple: tuple object to be compared of
	 * @return
	 */
	public boolean sameName(Tuple tuple) {
		if (this.name.equals(tuple.getName()))
			return true;
		else
			return false;
	}

	/**
	 * @param tuple: tuple object to be compared of
	 * @return
	 */
	public boolean sameCourse(Tuple tuple) {
		if (this.course_name.equals(tuple.getCourse()))
			return true;
		else
			return false;
	}

	public void setScore(Integer newscore) {
		this.score = newscore;
	}
}
