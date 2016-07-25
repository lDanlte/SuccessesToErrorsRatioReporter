package com.dantonov.report;

import java.io.IOException;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
public interface Reporter {

    
    void generateReport(LineReader in, LineWriter out) throws IOException;
    
}
