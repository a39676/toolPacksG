package cloudinary.pojo.result;

import net.sf.json.JSONArray;

public class CloudinaryDeleteResult {

	private JSONArray deleted;
	private boolean partial = false;

	public JSONArray getDeleted() {
		return deleted;
	}

	public void setDeleted(JSONArray jsonArray) {
		this.deleted = jsonArray;
	}

	public boolean isPartial() {
		return partial;
	}

	public void setPartial(boolean partial) {
		this.partial = partial;
	}

	@Override
	public String toString() {
		return "CloudinaryDeleteResult [deleted=" + deleted + ", partial=" + partial + "]";
	}

}
