package com.pension.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class PensionService {
    @Autowired
    PensionRepository pensionReposiroty;
    public String save(Pension pension){
        pensionReposiroty.save(pension);
        return "saved";
    }
    public String checkStatus(int id){
        return pensionReposiroty.getPensionStatus(id);


    }
    public String checkBalance(int id){
        return pensionReposiroty.getBalance(id);

    }
   public Pension checkApplication(int id){
       return pensionReposiroty.findById(id).get();
    }
    public void issuepension(){
        pensionReposiroty.issuepension("R","Y",getCurrentMMYY("MMyy"),getPreviousMMYY());

    }

    private static String getPreviousMMYY() {
        String currentMonth = getCurrentMMYY("MM");
        String currentYear = getCurrentMMYY("yy");

        //previous MMYY
        int prevMonth = Integer.parseInt(currentMonth)-1;
        String previousMMYY = String.valueOf(prevMonth)+currentYear;
        if(previousMMYY.length()==3) {
            previousMMYY = "0" + previousMMYY;
        }
        return previousMMYY;
    }

    private static String getCurrentMMYY(String MMyy) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MMyy);
        String currentMMYY = simpleDateFormat.format(new Date());
        return currentMMYY;
    }

    public void loadpension() {
        pensionReposiroty.loadpension("A","N",getCurrentMMYY("MMyy"),getPreviousMMYY());

    }



}
