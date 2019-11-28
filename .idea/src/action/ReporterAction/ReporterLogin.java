package action.ReporterAction;

import Model.Reporter;
import Model.User;
import Service.ReporterService.ReporterServiceImpl.ReporterLoginImpl;
import Service.UserService.UserServiceImpl.UserLoginImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 谢朝康
 * @date 2019/11/12 23:25
 */
//记者登录
@Controller
public class ReporterLogin extends ActionSupport {

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    String test1;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    String test;

    JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    @Autowired
   ReporterLoginImpl reporterLoginImpl;

    public ReporterLoginImpl getReporterLoginImpl() {
        return reporterLoginImpl;
    }

    public void setReporterLoginImpl(ReporterLoginImpl reporterLoginImpl) {
        this.reporterLoginImpl = reporterLoginImpl;
    }

    @Override
    public String execute() throws Exception {

        System.out.println("记者身份登录："+test+"\n"+"222:"+test1);

        Reporter reporter = new Reporter(test,test1);
        System.out.println("dskd:"+reporter);
        reporter  = reporterLoginImpl.ReporterLogin(reporter);

        System.out.println(reporter);

        json = JSONArray.fromObject(reporter);
        System.out.println(json);

        return SUCCESS;
    }

}
