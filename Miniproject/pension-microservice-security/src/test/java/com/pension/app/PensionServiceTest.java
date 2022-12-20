package com.pension.app;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.assertj.core.api.Assertions;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PensionServiceTest{

    @Autowired
    private PensionService pensionService;
    @MockBean
    private PensionRepository pensionRepository;


    @Test
    void testsave(){

        Pension p = new Pension();
        p.setId(201);
        p.setName("netaji");
        p.setAge(22);
        p.setBalance(90000);
        p.setMobile("1234567890");
        p.setEmpStatus("A");
        p.setPensionStatus("N");
        p.setPensionMMYY("0922");
        p.setInstallment(980);
        pensionRepository.save(p);
        Assertions.assertThat(p.getId()).isPositive();
        verify(pensionRepository, times(1)).save(any(Pension.class));
    }

    @Test
    void testGetStatus(){
        int id=201;
        Pension p = new Pension();
        p.setId(id);
        p.setName("netaji");
        p.setAge(21);
        p.setBalance(80000);
        p.setMobile("1234567890");
        p.setEmpStatus("A");
        p.setPensionStatus("N");
        p.setPensionMMYY("0922");
        p.setInstallment(1000);
        //pensionRepository.getBalance();
        when(pensionRepository.getEmpStatus(id)).thenReturn("A");
        assertEquals("A",pensionRepository.getEmpStatus(id));
        when(pensionRepository.getPensionStatus(id)).thenReturn("N");
        assertEquals("N",pensionRepository.getPensionStatus(id));
    }


    @Test
    void testGetBalance(){
        int id=201;
        Pension pensionBuilder = new Pension();
        pensionBuilder.setId(id);
        pensionBuilder.setName("netaji");
        pensionBuilder.setAge(21);
        pensionBuilder.setBalance(80000);
        pensionBuilder.setMobile("1234567890");
        pensionBuilder.setEmpStatus("A");
        pensionBuilder.setPensionStatus("N");
        pensionBuilder.setPensionMMYY("0922");
        pensionBuilder.setInstallment(1000);

        Pension p = new Pension();
        when(pensionRepository.findById(id)).thenReturn(Optional.of(p));
        Pension pensioner = pensionService.checkApplication(id);
        assertEquals(p.getId(), pensioner.getId());
        assertEquals(p.getName(), pensioner.getName());
        assertEquals(p.getAge(), pensioner.getAge());
        assertEquals(p.getBalance(), pensioner.getBalance());
        assertEquals(p.getMobile(), pensioner.getMobile());
        assertEquals(p.getEmpStatus(), pensioner.getEmpStatus());
        assertEquals(p.getPensionStatus(), pensioner.getPensionStatus());
        assertEquals(p.getPensionMMYY(), pensioner.getPensionMMYY());
        assertEquals(p.getInstallment(), pensioner.getInstallment());

    }
    @Test
    void testGetApplicantById(){
        int id=201;
        Pension pensionBuilder = new Pension();
        pensionBuilder.setId(id);
        pensionBuilder.setName("boppana");
        pensionBuilder.setAge(21);
        pensionBuilder.setBalance(80000);
        pensionBuilder.setMobile("1357924680");
        pensionBuilder.setEmpStatus("A");
        pensionBuilder.setPensionStatus("N");
        pensionBuilder.setPensionMMYY("0922");
        pensionBuilder.setInstallment(1000);

        Pension p = new Pension();
        when(pensionRepository.findById(id)).thenReturn(Optional.of(p));
        Pension pensioner = pensionService.checkApplication(id);
        assertEquals(p.getId(), pensioner.getId());
        assertEquals(p.getName(), pensioner.getName());
        assertEquals(p.getAge(), pensioner.getAge());
        assertEquals(p.getBalance(), pensioner.getBalance());
        assertEquals(p.getMobile(), pensioner.getMobile());
        assertEquals(p.getEmpStatus(), pensioner.getEmpStatus());
        assertEquals(p.getPensionStatus(), pensioner.getPensionStatus());
        assertEquals(p.getPensionMMYY(), pensioner.getPensionMMYY());
        assertEquals(p.getInstallment(), pensioner.getInstallment());

    }

    @Test
    void testLoadPension(){
        pensionService.loadpension();
        verify(pensionRepository).loadpension(any(),any(),any(),any());
    }

    @Test
    void testIssuePension(){
        pensionService.issuepension();
        verify(pensionRepository).issuepension(any(),any(),any(),any());
    }


}