package product_option;

public class OptionDTO {
	private int price_change;
	private int count;
	private String option_name;
	private int product_num;
	public int getPrice_change() {
		return price_change;
	}
	public void setPrice_change(int price_change) {
		this.price_change = price_change;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	
}
