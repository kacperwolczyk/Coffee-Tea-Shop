package coffeetea_shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import coffeetea_shop.model.*;
import coffeetea_shop.service.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserDetailsController {

  private UserService userService;
  private UserDetailsService userDetailsService;

  @PostMapping("/userdetails")
  public String addUserDetails(@ModelAttribute @Valid UserDetails userDetails, BindingResult bindResult,
      Principal principal) {
    if (bindResult.hasErrors()) {
      return "afterFirstLogin";
    }

    User user = userService.getUserByUsername(principal.getName());
    userDetailsService.addUserDetails(userDetails);
    user.setDetails(userDetails);
    user.setStatus("active");
    userService.updateUser(user);
    return "redirect:/userpanel";
  }

  @PostMapping("/updateaddress")
  public String updateAddress(@ModelAttribute UserDetails userDetails) {
    userDetailsService.updateUserDetails(userDetails);
    return "redirect:/userpanel";
  }
}
