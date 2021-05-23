package com.epam.ems.service.user;

import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dao.orderdao.OrderDaoImpl;
import com.epam.ems.dao.userdao.UserDaoImpl;
import com.epam.ems.dto.*;
import com.epam.ems.exceptions.NotEnoughMoneyException;
import com.epam.ems.logic.handler.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDaoImpl dao;

    @Autowired
    private CRUDDao<Certificate> certificateDao;

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private DateHandler dateHandler;

    public List<AppUser> getAllUsers(int page, int elements) {
        return dao.getAll(page, elements);
    }

    public List<Certificate> getUserCertificates(int id, int page, int elements) {
        return dao.getUserCertificatesById(id, page, elements);
    }

    public void addCertificateToUser(int userId, int certificateId) throws NotEnoughMoneyException {
        AppUser user = dao.getById(userId);
        Certificate certificate = certificateDao.getById(certificateId);
        if (user.getMoney() >= certificate.getPrice()) {
            AppUser newUser = createNewUser(user, certificate);
            orderDao.addCertificateToUser(createOrder(newUser, certificate));
            dao.updateUser(newUser);
        } else {
            throw new NotEnoughMoneyException("not enough money to buy this certificate");
        }
    }

    private UserOrderInfo createOrder(AppUser user, Certificate certificate) {
        int userId = user.getId();
        int certificatePrice = certificate.getPrice();
        int certificateId = certificate.getId();
        String date = dateHandler.getCurrentDate();
        return new UserOrderInfo(userId, certificateId, certificatePrice, date);

    }

    private AppUser createNewUser(AppUser oldUser, Certificate certificate) {
        int money = oldUser.getMoney() - certificate.getPrice();
        int id = oldUser.getId();
        List<Certificate> certificates = oldUser.getCertificates();
        String name = oldUser.getNickname();
        int overageOrderPrice = oldUser.getOverageOrderPrice() + certificate.getPrice();
        String password = oldUser.getPassword();
        Role role = oldUser.getRole();
        AppUser newUser = new AppUser(id, password, role, name, money, certificates, overageOrderPrice);
        dao.updateUser(newUser);
        return newUser;
    }

    public List<UserOrderInfo> getUserOrdersInfo(int id, int page, int elements) {
        return orderDao.getUserCertificatesInfo(id, page, elements);
    }

    public void saveUser(AppUser user) {
        dao.saveUser(user);
    }

    public AppUser getUserByUserName(String username) {
        return dao.getUserByNickname(username);
    }

    public Tag theMostWidelyUsedTag() {
        return dao.getTheMostWidelyUsedTag();
    }
}
