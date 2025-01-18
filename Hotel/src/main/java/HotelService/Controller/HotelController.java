package HotelService.Controller;

import HotelService.Model.HotelEntity;
import HotelService.Service.Hotelservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {

    @Autowired
    private Hotelservice hotelservice;

    @GetMapping("/getDetails")
    public List<HotelEntity> getDetails() {
        return hotelservice.getAllHotels();
    }

    @GetMapping("/gethotel/{id}")
    public HotelEntity getHotel(@PathVariable int id){
        return hotelservice.getHotelById(id);
    }

    @PostMapping("/newhotel")
    public void addHotel(@RequestBody HotelEntity hotel){
        hotelservice.SaveHotel(hotel);
    }

    @DeleteMapping("/deletehotel/{id}")
    public String deleteUser(@PathVariable int id){
        return hotelservice.DeleteHotel(id);
    }

}
