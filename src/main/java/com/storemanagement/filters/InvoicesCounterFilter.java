package com.storemanagement.filters;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.storemanagement.utils.InvoicesCounterUtil;

public class InvoicesCounterFilter implements Filter {

    public InvoicesCounterFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HashMap<Integer, Long> invoicesCounter = InvoicesCounterUtil.getInvoicesCounter();
		if(invoicesCounter.size() == 0) InvoicesCounterUtil.initializeValues();
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
