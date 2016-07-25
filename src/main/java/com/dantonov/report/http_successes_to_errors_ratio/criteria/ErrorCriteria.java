package com.dantonov.report.http_successes_to_errors_ratio.criteria;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
public interface ErrorCriteria<T> {
    
    boolean isError(T val);

}
