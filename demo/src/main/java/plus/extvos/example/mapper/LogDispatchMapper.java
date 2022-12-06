package plus.extvos.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import plus.extvos.logging.domain.LogObject;

/*
CREATE TABLE demo_access_logs
(
    username String,
    action String,
    level String,
    comment String,
    method String,
    params String,
    request_ip String,
    request_uri String,
    agent String,
    duration UInt64,
    ticks UInt32,
    exception_detail String,
    created DateTime
)
ENGINE = MergeTree
ORDER BY (username,request_ip,request_uri,agent,method,action,level)
 */

@Mapper
public interface LogDispatchMapper {
    @Insert({"INSERT INTO demo_access_logs (username,action,level,comment,method,params,request_ip,request_uri,agent,duration,ticks,exception_detail,created) ",
            "VALUES (",
            "#{username},",
            "#{action},",
            "#{level},",
            "#{comment},",
            "#{method},",
            "#{params},",
            "#{requestIp},",
            "#{requestUri},",
            "#{agent},",
            "#{duration},",
            "1,",
            "#{exceptionDetail},",
            "#{created}",
            ")"})
    void dispatch(LogObject lo);
}
