package com.endava.exam.service;

import java.io.IOException;
import java.io.Writer;

public interface WriteCsvService {
    void writePurchases(Writer writer) throws IOException;
}
