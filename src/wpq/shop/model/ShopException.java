package wpq.shop.model;

public class ShopException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShopException() {
		super();
	}

	public ShopException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ShopException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ShopException(String arg0) {
		super(arg0);
	}

	public ShopException(Throwable arg0) {
		super(arg0);
	}

}
