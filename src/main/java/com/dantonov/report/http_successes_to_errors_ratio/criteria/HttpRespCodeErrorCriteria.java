package com.dantonov.report.http_successes_to_errors_ratio.criteria;

/**
 * Критерий основанный на коде ответа; 5xx и 400 коды являются ошибками.
 * 
 * Сначала хотел назвать класс HttpRespCodes5xxAnd400BasedErrorCriteria,
 * но подумал, что это будет лишним.
 * 
 * @author Antonov Denis (den007230@gmail.com)
 */
public class HttpRespCodeErrorCriteria implements ErrorCriteria<Integer> {

    
    @Override
    public boolean isError(Integer val) {
        return (val / 100 == 5) || (val == 400);
    }

}
