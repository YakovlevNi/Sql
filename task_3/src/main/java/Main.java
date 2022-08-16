import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("1_hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

      Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 1);

        Transaction transaction = session.beginTransaction();
          List<Student>studentList =course.getStudents();
         for (Student student:studentList){
             System.out.println("\n"+student.getName());
              Subscription subscription = session.get(Subscription.class, new SubscriptionKey(student.getId(), course.getId()));
               System.out.println("Дата подписки " + subscription.getSubscriptionDate()+ "\n");
          }

            transaction.commit();

            session.close();

        }


    }

