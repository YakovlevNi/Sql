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

import java.nio.charset.StandardCharsets;
import java.util.List;


public class Main {
    public static void main(String[] args) {


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("1_hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseList = session.createQuery(query).getResultList();

        Transaction transaction = session.beginTransaction();
        for (PurchaseList purchase : purchaseList) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            int studentId = purchase.getStudent().getId();
            int courseId = purchase.getCourse().getId();
            linkedPurchaseList.setStudentId(studentId);
            linkedPurchaseList.setCourseId(courseId);
            linkedPurchaseList.setId(new LinkedPurchaseListKey(studentId, courseId));
            System.out.println("Курс " + linkedPurchaseList.getCourseId() + " - " + "Студент " + linkedPurchaseList.getStudentId());
            session.saveOrUpdate(linkedPurchaseList);
        }
        transaction.commit();
        session.close();
    }
}

