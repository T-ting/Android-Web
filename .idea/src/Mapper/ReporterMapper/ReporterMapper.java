package Mapper.ReporterMapper;

import Model.Reporter;
import org.springframework.stereotype.Repository;

/**
 * @author 谢朝康
 * @date 2019/11/12 23:19
 */
@Repository
public interface ReporterMapper {
    Reporter Reporterlogin(Reporter reporter);
}
