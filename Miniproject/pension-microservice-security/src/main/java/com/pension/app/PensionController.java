package com.pension.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PensionController {
    @Autowired
    PensionRepository pensionrepository;
    @Autowired
    PensionService pensionService;

    @PostMapping("/create/applicant")
    public @ResponseBody
    void store(@RequestBody final Pension pension){
        pensionrepository.save(pension);
    }

   @GetMapping("/checkstatus/{id}")
    public String checkStatus(@PathVariable int id){
        return pensionService.checkStatus(id);
    }

    @GetMapping("/checkBalance/{id}")
    public String checkBalance(@PathVariable int id){
        return pensionService.checkBalance(id);
    }

   @GetMapping("/checkApplication/{id}")
    public Pension checkApplication(@PathVariable int id){
        return pensionService.checkApplication(id);
    }

    @GetMapping("/issuepension")
    public void issuepension(){
        pensionService.issuepension();
    }

    @GetMapping("/loadpension")
    public void loadpension(){
        pensionService.loadpension();
    }
}
