package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserOwnCar(String model, Integer series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE car.model= :modelName AND car.series= :serialName");
      query.setParameter("modelName", model);
      query.setParameter("serialName", series);
      return query.getSingleResult();
   }
}
