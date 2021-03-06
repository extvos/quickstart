package plus.extvos.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import plus.extvos.auth.annotation.SessionUser;
import plus.extvos.auth.dto.UserInfo;
import plus.extvos.auth.service.ProviderService;
import plus.extvos.auth.service.QuickAuthService;
import plus.extvos.common.exception.ResultException;

/**
 * @author Mingcai SHEN
 */
@Controller
public class ThymeleafController {
    private static final Logger log = LoggerFactory.getLogger(ThymeleafController.class);

    @Autowired
    private ProviderService providerService;

    @Autowired
    private QuickAuthService quickAuthService;

    private boolean isAuthenticated() {
        Subject sub = SecurityUtils.getSubject();
        boolean v = sub.isAuthenticated();
        log.debug("ThymeleafController::isAuthenticated:> {}", v);
        return v;
    }

    @GetMapping("/index")
    public String indexView(Model model) {
        log.debug("ThymeleafController::index > {}", model);
        // 设置属性
        model.addAttribute("providers", providerService.allProviders());
        model.addAttribute("isAuthenticated", isAuthenticated());
        return "index";
    }

    @GetMapping("/login")
    public String loginView(Model model) throws ResultException {
        log.debug("ThymeleafController::login > {}", model);
        // 设置属性
        model.addAttribute("providers", providerService.allProviders());
        model.addAttribute("isAuthenticated", isAuthenticated());
        return "login";
    }

    @GetMapping("/profile")
    public Object profileView(Model model, @SessionUser String username) throws ResultException {
        log.debug("ThymeleafController::profile > ");
        model.addAttribute("isAuthenticated", isAuthenticated());
        log.debug("ThymeleafController::profile > username: {}", username);
        UserInfo user = quickAuthService.getUserByName(username, false);
        if (user != null) {
            log.debug("ThymeleafController::profile > {}, {}", user.getUserId(), user.getUsername());
            model.addAttribute("profile", user);
        }
        return "profile";
    }
}
