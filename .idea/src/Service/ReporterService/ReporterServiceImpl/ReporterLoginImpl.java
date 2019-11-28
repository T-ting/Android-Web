package Service.ReporterService.ReporterServiceImpl;

import Mapper.ReporterMapper.ReporterMapper;
import Model.Reporter;
import Service.ReporterService.ReporterLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢朝康
 * @date 2019/11/12 23:23
 */
@Service
public class ReporterLoginImpl implements ReporterLogin {

    @Autowired
    ReporterMapper reporterMapper;

    public ReporterMapper getReporterMapper() {
        return reporterMapper;
    }

    public void setReporterMapper(ReporterMapper reporterMapper) {
        this.reporterMapper = reporterMapper;
    }

    @Override
    public Reporter ReporterLogin(Reporter reporter) {
        return reporterMapper.Reporterlogin(reporter);
    }
}
