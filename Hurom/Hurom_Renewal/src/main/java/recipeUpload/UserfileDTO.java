package recipeUpload;

public class UserfileDTO {
	private String idx;
	private String oldname;
	private String newname;
	private String filetype;
	private String filenum;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilenum() {
		return filenum;
	}
	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}
}
