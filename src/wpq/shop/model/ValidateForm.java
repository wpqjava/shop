package wpq.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateForm {
	public ValidateType type();
	
	public String errorMsg();
	
	public int value() default 0;
}
