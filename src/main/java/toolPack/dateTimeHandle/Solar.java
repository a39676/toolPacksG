package toolPack.dateTimeHandle;

public class Solar {

	private int solarDay;
	private int solarMonth;
	private int solarYear;

	public int getSolarDay() {
		return solarDay;
	}

	public void setSolarDay(int solarDay) {
		this.solarDay = solarDay;
	}

	public int getSolarMonth() {
		return solarMonth;
	}

	public void setSolarMonth(int solarMonth) {
		this.solarMonth = solarMonth;
	}

	public int getSolarYear() {
		return solarYear;
	}

	public void setSolarYear(int solarYear) {
		this.solarYear = solarYear;
	}

	@Override
	public String toString() {
		return "Solar [solarDay=" + solarDay + ", solarMonth=" + solarMonth + ", solarYear=" + solarYear + "]";
	}

}
