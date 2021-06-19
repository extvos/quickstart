package org.extvos.example.service.impl;

import org.extvos.auth.service.QuickFilterCustomizer;
import org.springframework.stereotype.Service;

/**
 * @author shenmc
 */
@Service
public class QuickFilterCustomizerImpl implements QuickFilterCustomizer {
    private String ctxPath = System.getProperty("server.servlet.context-path") == null ? "" : System.getProperty("server.servlet.context-path");

    @Override
    public String[] anons() {
        return new String[]{
                ctxPath + "/" + "example/**"
        };
    }

    @Override
    public String[] auths() {
        return new String[]{};
    }
}
