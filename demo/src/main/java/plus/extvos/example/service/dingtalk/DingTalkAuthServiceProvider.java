package plus.extvos.example.service.dingtalk;

import org.springframework.stereotype.Service;
import plus.extvos.auth.dto.OAuthState;
import plus.extvos.auth.service.OAuthProvider;
import plus.extvos.common.exception.ResultException;

import java.util.Map;

/**
 * @author shenmc
 */
@Service
public class DingTalkAuthServiceProvider implements OAuthProvider {
    private static final String SLUG = "dingtalk";
    private static final String NAME = "钉钉登录";
    @Override
    public String getSlug() {
        return SLUG;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object notify(Map<String, Object> params, byte[] body) throws ResultException {
        return null;
    }

    @Override
    public String getCodeUrl(String state, String redirectUri) throws ResultException {
        return null;
    }

    /**
     * @return successUrl when login success to redirect
     */
    @Override
    public String successUrl() {
        return null;
    }

    /**
     * @return failedUrl when login failed to redirect
     */
    @Override
    public String failedUrl() {
        return null;
    }

    /**
     * @return registerUrl when login need to register
     */
    @Override
    public String registerUrl() {
        return null;
    }

    @Override
    public OAuthState authorized(String code, String state, String via, OAuthState authState) throws ResultException {
        return null;
    }

    @Override
    public OAuthState authorizeUpdate(Map<String, Object> params, OAuthState authState) throws ResultException {
        return null;
    }
}
