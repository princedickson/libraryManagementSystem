package com.explicit.libraryManagementSystem.Controller;

import com.explicit.libraryManagementSystem.Entity.Members;
import com.explicit.libraryManagementSystem.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibraryController {

    private MemberService memberService;

    public LibraryController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/listOfMembers")
    public String listOfMembers(Model model) {
        List<Members> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "listOfMembers";
    }

    @GetMapping("/listOfMembers/new")
    public String createListOfMember(Model model) {
        Members members = new Members();
        model.addAttribute("members", members);
        return "addMember";
    }

    @PostMapping("/listOfMembers")
    public String saveMember(@ModelAttribute("members") Members members) {
        memberService.saveMember(members);
        return "redirect:/listOfMembers";
    }

    @GetMapping("/edit/{id}")
    public String editList(@PathVariable Long id, Model model) {
        model.addAttribute("members", memberService.getMemberById(id));
        return "editMember";
    }

    @PostMapping("/edit/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("members") Members members, Model model) {
        Members exitingMember = new Members();
        exitingMember.setFirstName(members.getFirstName());
        exitingMember.setLastName(members.getLastName());
        exitingMember.setEmail(members.getEmail());
        exitingMember.setGender(members.getGender());
        exitingMember.setContact(members.getContact());
        exitingMember.setMemberType(members.getMemberType());
        exitingMember.setId(id);
        memberService.updateMember(exitingMember);
        return "redirect:/listOfMembers";
        //
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "redirect:/listOfMembers";
    }
}
