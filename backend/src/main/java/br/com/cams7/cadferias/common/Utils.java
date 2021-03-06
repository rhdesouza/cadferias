/**
 * 
 */
package br.com.cams7.cadferias.common;

import java.time.Instant;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * @author ceanm
 *
 */
public final class Utils {
	public static final Locale LOCALE = new Locale("pt", "BR");

	public static final String EMPTY = "";
	public static final String CLASS_SEPARATOR = ".";
	public static final String ENTITY_SUFFIX = "Entity";
	public static final String SERVICE_SUFFIX = "ServiceImpl";

	public static String getEntityName(String entityName) {
		return getEntityName(entityName, false);
	}

	public static String getEntityName(String entityName, boolean isFirstCharacterUpper) {
		return getValueWithoutEntitySuffix(
				isFirstCharacterUpper ? getFirstCharacterUpper(entityName) : getFirstCharacterLower(entityName));
	}

	private static String getFirstCharacterLower(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}

	private static String getFirstCharacterUpper(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String getValueWithoutEntitySuffix(String value) {
		return value.replaceFirst(ENTITY_SUFFIX, EMPTY);
	}

	public static String getMessage(MessageSource messageSource, String code, Object... args) {
		return messageSource.getMessage(code, args, LOCALE);
	}

	public static long getTimestamp() {
		return Instant.now().toEpochMilli();
	}
}
