-- demo_access_logs 用户访问日志
CREATE TABLE IF NOT EXISTS demo_access_logs
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
    duration UInt8,
    ticks UInt8,
    exception_detail String,
    created DateTime
) ENGINE = StripeLog;