package org.extvos.example.service.impl;

import org.extvos.auth.service.QuickFilterCustomizer;
import org.springframework.stereotype.Service;

/**
 * @author shenmc
 */
@Service
public class QuickFilterCustomizerImpl implements QuickFilterCustomizer {
    @Override
    public String[] anons() {
        return new String[]{
            "/anons/1",
            "/anons/2"
        };
    }

    @Override
    public String[] auths() {
        return new String[]{
            "/auths/1",
            "/auths/2"
        };
    }
}
