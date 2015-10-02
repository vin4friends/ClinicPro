/**
 * 
 */
package com.app.common.fe.impl.interceptor;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * An interceptor to set the Locale for the request for struts and for spring.
 * 
 * @author jomu
 */
public abstract class AbstractI18nInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7848321564251998282L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final Map<String, Object> session = invocation.getInvocationContext().getSession();
		final Locale locale = getLocale(invocation);

		if (locale != null) {
			// set locale for struts and spring
			invocation.getInvocationContext().setLocale(locale);
			session.put(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
			LocaleContextHolder.setLocale(locale);
		} else {
			// set only from struts guessed locale for spring
			LocaleContextHolder.setLocale(invocation.getInvocationContext().getLocale());
		}

		try {
			return invocation.invoke();
		} finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}

	/**
	 * Must be overriden by web application to provide the locale for the
	 * request.
	 * 
	 * @param invocation
	 *            the current request
	 * @return the locale to use for the request
	 */
	protected abstract Locale getLocale(ActionInvocation invocation);
}
