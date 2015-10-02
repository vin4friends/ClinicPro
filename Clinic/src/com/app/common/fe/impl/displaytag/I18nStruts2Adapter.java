/**
 * 
 */
package com.app.common.fe.impl.displaytag;

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.views.jsp.TagUtils;
import org.displaytag.Messages;
import org.displaytag.localization.I18nResourceProvider;
import org.displaytag.localization.LocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * Adapter between Struts2 and Displaytag for I18N.
 * 
 * @author jomu
 */
public class I18nStruts2Adapter implements LocaleResolver, I18nResourceProvider {
	/**
	 * Prefix/Suffix for missing entries.
	 */
	public static final String UNDEFINED_KEY = "???"; //$NON-NLS-1$

	private static final Logger LOG = LoggerFactory.getLogger(I18nStruts2Adapter.class);

	@Override
	public Locale resolveLocale(HttpServletRequest request) {

		Locale result = null;
		ValueStack stack = ActionContext.getContext().getValueStack();

		Iterator<?> iterator = stack.getRoot().iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();

			if (o instanceof LocaleProvider) {
				LocaleProvider lp = (LocaleProvider) o;
				result = lp.getLocale();

				break;
			}
		}

		if (result == null) {
			LOG.debug("Missing LocalProvider actions, init locale to default");
			result = Locale.getDefault();
		}

		return result;
	}

	@Override
	public String getResource(String resourceKey, String defaultValue, Tag tag, PageContext pageContext) {

		// wenn resourceKey undefiniert, benutze defaultValue
		String key = (resourceKey != null) ? resourceKey : defaultValue;

		String message = null;
		ValueStack stack = TagUtils.getStack(pageContext);
		Iterator<?> iterator = stack.getRoot().iterator();

		while (iterator.hasNext()) {
			Object o = iterator.next();

			if (o instanceof TextProvider) {
				TextProvider tp = (TextProvider) o;
				message = tp.getText(key);

				break;
			}
		}

		// Fehlerbehandlung, falls resource key nicht gefunden
		if (message == null && resourceKey != null) {
			LOG.debug(Messages.getString("Localization.missingkey", resourceKey)); //$NON-NLS-1$
			message = UNDEFINED_KEY + resourceKey + UNDEFINED_KEY;
		}

		return message;
	}
}
