package com.dantonov.report;

import java.io.IOException;

/**
 *
 * @author Antonov Denis (den007230@gmail.com)
 */
@FunctionalInterface
public interface LineWriter {

    void println(String s) throws IOException;
}
