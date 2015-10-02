/**
 * 
 */
package com.app.services.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.model.impl.Country;
import com.app.model.impl.EnvironmentProperty.IntegerProperty;
import com.app.model.impl.enums.AddressTemplatePlaceholder;
import com.app.services.EnvironmentPropertiesService;
import com.app.util.CustomStringUtil;
import com.app.validation.CountryEntityValidator;

/**
 * Implementation of Interface {@code CountryEntityValidator}
 * 
 * @author jomu
 */
@Service("countryEntityValidator")
public class CountryEntityValidatorImpl implements CountryEntityValidator {

	@Resource
	private EnvironmentPropertiesService environmentPropertiesService;

	private static final Pattern FREEMARKER_PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{(.+?)\\}");

	@Override
	public boolean validateMaxLocationResult(Object country, List<Object> messageArguments) {
		Country entity = (Country) country;

		Long maxLocationResult = entity.getMaxLocationResult();

		int minAllowed = 1;
		int maxAllowed = environmentPropertiesService.getIntegerProperty(
				IntegerProperty.MAX_LOCATIONS_PER_SEARCH_RESULT).intValue();

		if (maxLocationResult == null
				|| (maxLocationResult.intValue() >= minAllowed && maxLocationResult.intValue() <= maxAllowed)) {
			return true;
		}

		messageArguments.add(minAllowed);
		messageArguments.add(maxAllowed);
		return false;
	}

	@Override
	public boolean validateAddressTemplate(Object country, List<Object> messageArguments) {
		Country entity = (Country) country;
		if (CustomStringUtil.isBlank(entity.getAddressTemplate())) {
			return false;
		}

		Set<AddressTemplatePlaceholder> allowedPlaceholder = new HashSet<AddressTemplatePlaceholder>(
				Arrays.asList(AddressTemplatePlaceholder.values()));

		final Matcher m = FREEMARKER_PLACEHOLDER_PATTERN.matcher(entity.getAddressTemplate());
		final List<String> unknownPlaceholder = new ArrayList<String>();
		boolean isValid = true;

		while (m.find()) {
			String placeholderKey = m.group(1).trim();

			AddressTemplatePlaceholder placeholder = AddressTemplatePlaceholder.getPlaceholder(placeholderKey);
			boolean isAllowed = allowedPlaceholder.contains(placeholder);

			if (!isAllowed) {
				isValid = false;
				unknownPlaceholder.add(placeholderKey);
			}
		}

		if (!isValid) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < unknownPlaceholder.size(); i++) {
				builder.append(unknownPlaceholder.get(i));
				if (i < unknownPlaceholder.size() - 1) {
					builder.append(", ");
				}
			}
			messageArguments.add(builder.toString());
		}

		return isValid;
	}
}
