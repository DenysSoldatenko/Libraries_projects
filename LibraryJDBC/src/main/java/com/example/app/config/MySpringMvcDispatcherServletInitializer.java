package com.example.app.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the Spring MVC Dispatcher Servlet and configures filters for character encoding
 * and hidden HTTP method support.
 */
public class MySpringMvcDispatcherServletInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{SpringConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    registerCharacterEncodingFilter(servletContext);
    registerHiddenFieldFilter(servletContext);
  }

  private void registerHiddenFieldFilter(ServletContext servletContext) {
    servletContext.addFilter("hiddenHttpMethodFilter",
      new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
  }

  private void registerCharacterEncodingFilter(ServletContext servletContext) {
    EnumSet<DispatcherType> dispatcherTypes
        = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    FilterRegistration.Dynamic characterEncoding
        = servletContext.addFilter("characterEncoding", characterEncodingFilter);
    characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
  }
}
