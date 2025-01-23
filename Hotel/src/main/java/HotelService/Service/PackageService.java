package HotelService.Service;


import HotelService.Model.PackageEntity;
import HotelService.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public List<PackageEntity> getAllPackages() {
        return packageRepository.findAll();
    }

    public PackageEntity getPackageById(int id) {
        Optional<PackageEntity> packages = packageRepository.findById(id);
        return packages.orElse(null);
    }

    public void SavePackage(PackageEntity packages)
    {
        packageRepository.save(packages);
    }

    public String DeletePackage(int id) {
        if(getPackageById(id) != null)
        {
            packageRepository.deleteById(id);
            return "Package deleted ...";
        }
        else
        {
            return "Package not found !!!";
        }
    }
}
