import org.openqa.selenium.By;

public class QuantInstiAutomationElements {

	public static final By USER_LOG_IN_BUTTON = By.xpath("//span[contains(text(),'Login')]");
	public static final By EMAIL_ID_INPUT_FIELD = By.name("email");
	public static final By PASSWORD_INPUT_FIELD = By.name("password");
	public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
	public static final By NEXT_ARROW = By.xpath("(//button[@class='next-arrow'])[1]");
	public static final By SENTIMENT_ANALYSIS_IN_TRADING_COURSE =
			By.xpath("//div[contains(text(),'Sentiment Analysis in Trading')]");
	public static final By BROWSE_COURSE_BUTTON =
			By.xpath("//a[contains(text(),'Browse Courses')]");
	public static final By SENTIMENT_ANALYSIS_IN_TRADING_COURSE_STRING =
			By.tagName("h1");
	public static final By ORIGINAL_PRICE_OF_COURSE = By.className("cd__data-unit__striked");
	public static final By OFFER_PRICE_OF_COURSE = By.xpath("//div[@class='cd__data-unit__info']//span[2]//span");
	public static final By ENROLL_NOW_BUTTON =
			By.xpath("//div[@id='courseDetailMenu']//span[contains(text(),'Enroll Now')]");
	public static final String GET_COURSES_NAME = "(//div[@class='cart-item']//h5)[%d]";
	public static final By GET_DISPLAYING_COURSES_COUNT =
			By.xpath("(//div[@class='cart-item']//h5)");
	public static final By ITEM_IN_CART = By.xpath("(//span[@class='cart-count'])[1]");
	public static final By GET_BASE_AMOUNT = By.xpath("(//div[@class='cart-summary-right'])[1]");
	public static final By GET_AMOUNT_PAYABLE =
			By.xpath("(//div[@class='cart-summary-right'])//h5//span");
	public static final By CLICK_ON_VIEW_DETAILS_OF_FIRST =
			By.xpath("(//a[contains(text(),'View Details')])[1]");
	public static final By REMOVE_FIRST_COURSE = By.xpath("(//a[contains(text(),'Remove')])[1]");
	public static final By GET_ALERT_MESSAGE_AFTER_REMOVING_COURSE =
			By.xpath("//div[@class='toasted toasted-primary info']");
	public static final By APPLY_COUPON_BUTTON =
			By.xpath("//span[contains(text(),'Apply Coupon')]");
	public static final By INPUT_FIELD_TO_ENTER_COUPON =
			By.xpath("//input[@placeholder='Type coupon code']");
	public static final By APPLY_BUTTON = By.xpath("(//span[contains(text(),'Apply')])[2]");
	public static final By GET_WRONG_COUPON_ALERT_MESSAGE = By.className("alert-danger");
	public static final By CLOSE_ICON_ON_COUPON_POP_UP = By.xpath("//span[contains(text(),'×')]");
	public static final By PROFILE_ICON = By.className("profile-pic-initials");
	public static final By LOGOUT =
			By.xpath("//div[@class='avatar-container sub-nav']//a[contains(text(),'Logout')]");
	public static final By LOG_IN_TEXT_ON_LOG_IN_PAGE = By.xpath("//span[contains(text(),'Log in to')]");
	public static final By COURSE_IN_CART = By.xpath("//h3[contains(@class,'cart-title')]");
	public static final By COUPON_TITLE_STRING = By.className("coupon-modal__title");
	public static final By STRING_ON_HOME_PAGE = By.xpath("//div[contains(text(),'Min 40% off on all Quantra courses')]");
}
