package com.dantonov.report.http_successes_to_errors_ratio;

import com.dantonov.report.LineReader;
import com.dantonov.report.LineWriter;
import com.dantonov.report.Reporter;
import com.dantonov.report.http_successes_to_errors_ratio.criteria.ErrorCriteria;
import com.dantonov.report.http_successes_to_errors_ratio.criteria.HttpRespCodeErrorCriteria;
import com.dantonov.report.http_successes_to_errors_ratio.key.ReportKey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
public class SuccessesToErrorsRatioReporter implements Reporter {
    
    
    private final ErrorCriteria<Integer> errorCriteria;

    
    public SuccessesToErrorsRatioReporter(ErrorCriteria<Integer> errorCriteria) {
        this.errorCriteria = errorCriteria;
    }
    
    
    public ErrorCriteria<Integer> getErrorCriteria() {
        return errorCriteria;
    }
    
    
    @Override
    public void generateReport(LineReader in, LineWriter out) throws IOException {
        
        Map<ReportKey, Integer> successes = new HashMap<>(),
                                errors    = new HashMap<>();
        
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            
            String[] lineParts = line.split(" ");
            ReportKey key = new ReportKey(lineParts[0], lineParts[2]);
            int code = Integer.parseInt(lineParts[3]);
            
            addValue((errorCriteria.isError(code) ? errors : successes), key);
        }
        
        Set<ReportKey> keys = new HashSet<>();
        
        keys.addAll(errors.keySet());
        keys.addAll(successes.keySet());
        
        String[] results = new String[keys.size()];
        int i = 0;
        for (ReportKey key : keys) {
            
            Integer errorsCount = errors.get(key),
                    successesCount = successes.get(key);
            
            errorsCount = (errorsCount != null) ? errorsCount : 0;
            successesCount = (successesCount != null) ? successesCount : 0;
            
            String onlyErrors = (successesCount == 0) ? String.format("Only errors (%d)", errorsCount) : null;
            
            results[i++] = key.getIp() + " " + key.getResource() + " " +
                        ((onlyErrors != null) ? onlyErrors : ((double)(errorsCount) / successesCount));
            
        }
        
        Arrays.sort(results, String::compareTo);
        
        for (String result : results) {
            out.println(result);
        }
    }
    
    
    private void addValue(Map<ReportKey, Integer> map, ReportKey key) {
        
        Integer count = map.get(key);
        map.put(key, ((count != null) ? count + 1 : 1));
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        SuccessesToErrorsRatioReporter reporter = new SuccessesToErrorsRatioReporter(new HttpRespCodeErrorCriteria());
        
        try (BufferedReader reader = new BufferedReader(new FileReader("testLogData.txt"))) {
            reporter.generateReport(reader::readLine, System.out::println);
        }
    }

}