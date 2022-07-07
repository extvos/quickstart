package plus.extvos.example.service.impl;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import plus.extvos.redis.service.QuickRedisService;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shenmc
 */
@Service
public class QuickRedisSessionDAO extends AbstractSessionDAO implements SessionDAO {

    @Autowired
    private QuickRedisService quickRedisService;

    private static final String QUICK_SESSION_KEY = "quick_demo_session_";

    private void saveSession(Session session) {
        if (null == session) {
            return;
        }
        if (null == session.getId()) {
            return;
        }
        quickRedisService.set(QUICK_SESSION_KEY + session.getId(), session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        Session session = null;
        session = (Session) quickRedisService.get(QUICK_SESSION_KEY + serializable);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (null == session) {
            return;
        }
        if (null == session.getId()) {
            return;
        }
        quickRedisService.delete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        Set<String> keys = new HashSet<>(quickRedisService.keys(QUICK_SESSION_KEY + "*"));
        if (keys.size() > 0) {
            for (String k : keys) {
                Session s = (Session) quickRedisService.get(k);
                sessions.add(s);
            }
        }
        return sessions;
    }
}
