package com.dantonov.report;

import java.io.IOException;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
@FunctionalInterface
public interface LineReader {

    String readLine() throws IOException;
}
