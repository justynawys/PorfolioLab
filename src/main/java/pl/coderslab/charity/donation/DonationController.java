package pl.coderslab.charity.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DonationController {
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String f(Donation donation, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userRepository.findById(currentUser.getUser().getId()).get();
        donationRepository.save(donation);
        user.getDonations().add(donation);
        userRepository.save(user);
        return "form-confirmation";
    }




    @ModelAttribute("categories")
    public List<Category> category(){
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionRepository.findAll();
    }

    @ModelAttribute("user")
    public String user(@AuthenticationPrincipal CurrentUser currentUser){
        return currentUser.getUser().getName();
    }
}
