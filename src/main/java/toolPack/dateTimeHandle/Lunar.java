package toolPack.dateTimeHandle;

public class Lunar {

	private boolean isleap;
	private int lunarDay;
	private int lunarMonth;
	private int lunarYear;

	public boolean isIsleap() {
		return isleap;
	}

	public void setIsleap(boolean isleap) {
		this.isleap = isleap;
	}

	public int getLunarDay() {
		return lunarDay;
	}

	public void setLunarDay(int lunarDay) {
		this.lunarDay = lunarDay;
	}

	public int getLunarMonth() {
		return lunarMonth;
	}

	public void setLunarMonth(int lunarMonth) {
		this.lunarMonth = lunarMonth;
	}

	public int getLunarYear() {
		return lunarYear;
	}

	public void setLunarYear(int lunarYear) {
		this.lunarYear = lunarYear;
	}

	@Override
	public String toString() {
		return "Lunar [isleap=" + isleap + ", lunarDay=" + lunarDay + ", lunarMonth=" + lunarMonth + ", lunarYear="
				+ lunarYear + "]";
	}

}
