/**
 * 
 */
package org.vaadin.spring.i18n;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * @author Christoph Guse, info@flexguse.de
 * 
 */
@Aspect
public class TranslationAspect {

	private MessageSource messageSource;

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Pointcut("(execution (public * com.vaadin.ui.Label.setValue (..))) && args(labelValue)")
	private void setValueLabel(String labelValue) {
	}

	@Pointcut("(execution (public * com.vaadin.ui.AbstractComponent.setCaption (..))) && args(caption)")
	private void setCaption(String caption) {
	}

	// @Pointcut("(execution (public * com.vaadin.server.UIProvider.getPageTitle (..))) && args(event)")
	// private void getApplicationTitle(UICreateEvent event){}

	@Around("setValueLabel(labelValue)")
	public void setTranslatedValue(ProceedingJoinPoint proceedingJoinPoint,
			String labelValue) throws Throwable {

		proceedWithTranslation(proceedingJoinPoint, labelValue);

	}

	@Around("setCaption(caption)")
	public void setTranslatedCaption(ProceedingJoinPoint proceedingJoinPoint,
			String caption) throws Throwable {

		proceedWithTranslation(proceedingJoinPoint, caption);

	}

	// @Around("getApplicationTitle(event)")
	// public String getTranslatedApplicationTitle(ProceedingJoinPoint
	// proceedingJoinPoint) throws Throwable{
	//
	// String applicationTitle = (String)proceedingJoinPoint.proceed();
	//
	// if(StringUtils.isNotBlank(applicationTitle)){
	// applicationTitle = translateLabel(applicationTitle);
	// }
	//
	// return applicationTitle;
	//
	// }

	/**
	 * This helper method translates the translationKey and proceeds with the
	 * origin method call.
	 * 
	 * @param proceedingJoinPoint
	 * @param translationKey
	 * @throws Throwable
	 */
	private void proceedWithTranslation(
			ProceedingJoinPoint proceedingJoinPoint, String translationKey)
			throws Throwable {
		if (StringUtils.isNotBlank(translationKey)) {
			translationKey = translateLabel(translationKey);
		}

		proceedingJoinPoint.proceed(new Object[] { translationKey });
	}

	/**
	 * This helper method does the translation using the given message source.
	 * 
	 * @param labelValue
	 * @return
	 */
	private String translateLabel(String labelValue) {

		try {
			String translatedLabel = messageSource.getMessage(labelValue, null,
					Locale.ENGLISH);

			/*
			 * throwing NoSuchMessageException may be switched off, ensure not
			 * to return null
			 */
			if (translatedLabel != null) {
				return translatedLabel;
			}

		} catch (NoSuchMessageException e) {
			// this exception may occur, nothing to do, translation is
			// simply not present
		}

		return labelValue;

	}

}
