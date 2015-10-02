/**
 * 
 */
package com.app.fe.web.interceptor;

import java.util.Locale;

import com.app.common.fe.impl.interceptor.AbstractI18nInterceptor;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * I18nInterceptor for Back Office, which will be always english.
 * 
 * @author jomu
 */
public class I18nInterceptor extends AbstractI18nInterceptor {

	private static final long serialVersionUID = -6218614359969324797L;

	@Override
	protected Locale getLocale(ActionInvocation invocation) {
		return Locale.ENGLISH;
	}
}
