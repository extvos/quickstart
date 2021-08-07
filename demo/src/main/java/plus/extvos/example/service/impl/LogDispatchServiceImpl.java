package plus.extvos.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.extvos.example.mapper.LogDispatchMapper;
import plus.extvos.logging.domain.LogObject;
import plus.extvos.logging.service.LogDispatchService;

@Service
@DS("clickhouse")
public class LogDispatchServiceImpl implements LogDispatchService {
    private final static Logger log = LoggerFactory.getLogger(LogDispatchServiceImpl.class);

    @Autowired
    LogDispatchMapper logDispatchMapper;

    @Override
    public void dispatch(LogObject logObject) {
        log.debug(">>> {} {} {} {}", logObject.getRequestUri(), logObject.getAction(), logObject.getMethod(), logObject.getDuration());
        logDispatchMapper.dispatch(logObject);
    }
}
