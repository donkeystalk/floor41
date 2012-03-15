package octane.floor.filters;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class FlashMap {

	static final String FLASH_MAP_ATTRIBUTE = FlashMap.class.getName();
	
	/** 
	 * Returns Flash Map, useful for Authentication exceptions, stolen from Petcare example project.
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCurrent(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Map flash = (Map) session.getAttribute(FLASH_MAP_ATTRIBUTE);
		if(flash == null)
		{
			flash = new HashMap();
			session.setAttribute(FLASH_MAP_ATTRIBUTE, flash);
		}
		return flash;
	}
	
	private FlashMap()
	{
		
	}
}
