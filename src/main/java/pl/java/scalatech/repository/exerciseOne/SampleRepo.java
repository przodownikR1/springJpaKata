package pl.java.scalatech.repository.exerciseOne;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.java.scalatech.domain.exerciseOne.Department;
import pl.java.scalatech.domain.exerciseOne.Employee;
import pl.java.scalatech.domain.exerciseOne.Phone;
import pl.java.scalatech.domain.exerciseOne.Project;

@Component
public class SampleRepo {

    @Autowired
    private EntityManager em;

    public List<Employee> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee  e", Employee.class);

        return query.getResultList();
    }

    public List<Employee> findEmployeeWithName(final String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> emp = cq.from(Employee.class);
        cq.select(emp).where(cb.equal(emp.get("name"), name));
        TypedQuery<Employee> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Employee> findEmployees(String name, String deptName, String projectName, String city) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT e ");
        query.append("FROM Employee e LEFT JOIN e.projects p ");
        query.append("WHERE ");

        List<String> criteria = new ArrayList<>();
        if (name != null) {
            criteria.add("e.name = :name");
        }
        if (deptName != null) {
            criteria.add("e.dept.name = :dept");
        }
        if (projectName != null) {
            criteria.add("p.name = :project");
        }
        if (city != null) {
            criteria.add("e.address.city = :city");
        }
        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        }

        for (int i = 0; i < criteria.size(); i++) {
            if (i > 0) {
                query.append(" AND ");
            }
            query.append(criteria.get(i));
        }

        Query q = em.createQuery(query.toString());
        if (name != null) {
            q.setParameter("name", name);
        }
        if (deptName != null) {
            q.setParameter("dept", deptName);
        }
        if (projectName != null) {
            q.setParameter("project", projectName);
        }
        if (city != null) {
            q.setParameter("city", city);
        }

        return q.getResultList();
    }

    public List<Employee> findEmp(String name, String deptName, String projectName, String city) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> emp = cq.from(Employee.class);

        cq.select(emp);
        cq.distinct(true);

        Join<Employee, Project> project = emp.join("projects", JoinType.LEFT);

        List<Predicate> criteria = new ArrayList<>();
        if (name != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "name");
            criteria.add(cb.equal(emp.get("name"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "dept");
            criteria.add(cb.equal(emp.get("dept").get("name"), p));
        }
        if (projectName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "project");
            criteria.add(cb.equal(project.get("name"), p));
        }
        if (city != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "city");
            criteria.add(cb.equal(emp.get("address").get("city"), p));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            cq.where(criteria.get(0));
        } else {
            cq.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Employee> query = em.createQuery(cq);
        if (name != null) {
            query.setParameter("name", name);
        }
        if (deptName != null) {
            query.setParameter("dept", deptName);
        }
        if (project != null) {
            query.setParameter("project", projectName);
        }
        if (city != null) {
            query.setParameter("city", city);
        }

        return query.getResultList();
    }

    public List<Department> findDepartments() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> c = cb.createQuery(Department.class);

        Root<Department> dept = c.from(Department.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(dept).distinct(true).where(cb.equal(dept, emp.get("dept")));

        TypedQuery<Department> query = em.createQuery(c);
        return query.getResultList();
    }


    public List<String> findEmpNames() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> c = cb.createQuery(String.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp.<String> get("name"));
        c.distinct(false);

        TypedQuery<String> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Tuple> findMultiplyExpressionTuple() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> c = cb.createTupleQuery();
        Root<Employee> emp = c.from(Employee.class);
        c.select(cb.tuple(emp.get("id"), emp.get("name")));

        TypedQuery<Tuple> query = em.createQuery(c);
        return query.getResultList();
        /*
         * for (Tuple tuple : facade.findMultiplyExpressionTuple()) {
         * System.out.println(tuple.get(0) + " : " + tuple.get(1));
         * }
         */
    }

    public List<Object[]> findMultiSelect() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // or Tulip
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Employee> emp = c.from(Employee.class);
        c.multiselect(emp.get("id"), emp.get("name"), emp.get("salary"));

        return em.createQuery(c).getResultList();
    }

    public List<Object> findMapJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<Employee> emp = c.from(Employee.class);
        EntityType<Employee> emp_ = emp.getModel();
        MapJoin<Employee, String, Phone> phone = emp.join(emp_.getMap("phones", String.class, Phone.class));
        c.multiselect(emp.get(emp_.getSingularAttribute("name", String.class)), phone.key(), phone.value());

        return em.createQuery(c).getResultList();
    }

    public List<Employee> fetchWithPhones() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        emp.fetch("phones", JoinType.LEFT);
        c.select(emp).distinct(true);
        return em.createQuery(c).getResultList();
    }

    public List<Employee> findWithDeptName(String deptName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);

        ParameterExpression<String> dept = cb.parameter(String.class, "deptName");
        c.where(cb.equal(emp.get("dept").get("name"), dept));

        TypedQuery<Employee> query = em.createQuery(c);
        query.setParameter("deptName", deptName);

        return query.getResultList();
    }

    public List<Employee> findUsingSubQuery(String projectName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);

        TypedQuery<Employee> query = em.createQuery(c);

        if (projectName != null) {
            Subquery<Employee> sq = c.subquery(Employee.class);
            Root<Project> project = sq.from(Project.class);
            Join<Project, Employee> sqEmp = project.join("employees");
            sq.select(sqEmp)
                    // set parameter
                    .where(cb.equal(project.get("name"), cb.parameter(String.class, "project")));
            query.setParameter("project", projectName);

        }
        return null;
    }

    /*
     * SELECT e
     * FROM Employee e
     * WHERE e.address.state IN ('NY', 'CA')
     */
    public List<Employee> findInEmployee() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp)
                // .where(cb.in(emp.get("address").get("state")).value("NY").value("CA"));
                .where(emp.get("address").get("state").in("NY", "CA"));

        return em.createQuery(c).getResultList();
    }

    /*
     * SELECT e
     * FROM Employee e
     * WHERE e.department IN
     * (SELECT DISTINCT d
     * FROM Department d JOIN d.employees de JOIN de.project p
     * WHERE p.name LIKE 'QA%')
     */
    public List<Employee> findInSubquery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Department> sq = c.subquery(Department.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee, Project> project = dept.join("employees").join("projects");
        sq.select(dept).distinct(true).where(cb.like(project.<String> get("name"), "QA%"));
        c.select(emp).where(cb.in(emp.get("dept").get("id")).value(sq));

        return em.createQuery(c).getResultList();
    }

    public List<Object> findCoalesce() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<Department> dept = c.from(Department.class);
        c.select(cb.coalesce().value(dept.get("name")).value(dept.get("id")));

        return em.createQuery(c).getResultList();
    }


}
