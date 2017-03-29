package com.vther.spring.data.jpa.service.impl;


import com.vther.spring.data.jpa.entity.dbunit.Student;
import org.apache.derby.tools.ij;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest(CustomerServiceImplPowerMockTest.class)
public class CustomerServiceImplPowerMockTest {
    /**
     * The factory that produces entity manager.
     */
    private static EntityManagerFactory mEmf;
    /**
     * The entity manager that persists and queries the DB.
     */
    private static EntityManager mEntityManager;

    @BeforeClass
    public static void initTestFixture() throws Exception {
        // Get the entity manager for the tests.
        mEmf = Persistence.createEntityManagerFactory("JEE6Demo-Persistence");
        mEntityManager = mEmf.createEntityManager();

        Connection connection = ((EntityManagerImpl) (mEntityManager
                .getDelegate())).getServerSession().getAccessor()
                .getConnection();

        ij.runScript(connection,
                new ClassPathResource("sql/student.sql").getInputStream(),
                "UTF-8", System.out, "UTF-8");

        DatabaseConnection mDBUnitConnection = new DatabaseConnection(connection);
        //Loads the data set from a file named students-datasets.xml
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("datasets.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(mDBUnitConnection, dataSet);

    }

    /**
     * Cleans up the session.
     */
    @AfterClass
    public static void closeTestFixture() {
        mEntityManager.close();
        mEmf.close();
    }

    @Before
    public void initTest() throws Exception {

    }

    @Test
    public void createCustomer() throws Exception {
        System.out.println(mEntityManager.find(Student.class, 1L));
    }

    @Test
    public void findByAgeGreaterThan() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}