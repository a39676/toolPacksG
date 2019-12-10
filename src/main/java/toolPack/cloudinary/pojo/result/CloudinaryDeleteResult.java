package toolPack.cloudinary.pojo.result;

import net.sf.json.JSONObject;

public class CloudinaryDeleteResult {

	/*
	 * result example
	 * {"deleted":{"t56vqlj7596gbsjliflp":"not_found","jkgl8hinpvdgvqyb7xs1":"deleted"},"deleted_counts":{"t56vqlj7596gbsjliflp":{"original":0,"derived":0},"jkgl8hinpvdgvqyb7xs1":{"original":1,"derived":0}},"partial":false}
	 */
	private JSONObject deleted;
	private boolean partial = false;


	public JSONObject getDeleted() {
		return deleted;
	}

	public void setDeleted(JSONObject deleted) {
		this.deleted = deleted;
	}

	public boolean isPartial() {
		return partial;
	}

	public void setPartial(boolean partial) {
		this.partial = partial;
	}

	@Override
	public String toString() {
		return "CloudinaryDeleteResult [partial=" + partial + "]";
	}

}
