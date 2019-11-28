package Mapper.AdminMapper;

import Model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {

    List<Admin> AllAdmin();

    Admin AdminLogin(int id);
    int AdminRegister(Admin admin);

}
