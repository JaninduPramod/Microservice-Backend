package HotelService.Controller;

import HotelService.Model.PackageEntity;
import HotelService.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "http://localhost:5173")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/getpackages")
    public List<PackageEntity> getPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/getpackage/{id}")
    public PackageEntity getPackage(@PathVariable int id){
        return packageService.getPackageById(id);
    }

    @PostMapping("/newpackage")
    public void addPackage(@RequestBody PackageEntity packages){
        packageService.SavePackage(packages);
    }

    @DeleteMapping("/deletepackage/{id}")
    public String deletePackage(@PathVariable int id){
        return packageService.DeletePackage(id);
    }

}
