package plus.extvos.example.service.impl;

import plus.extvos.auth.service.QuickFilterCustomizer;
import org.springframework.stereotype.Service;

/**
 * @author Mingcai SHEN
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
