package com.fifzu.logisticWebApp;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class LogisticsController {

    @RequestMapping("/")
    public String index()
    {
        return"index";
    }
    @RequestMapping(value="/save", method= RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Employee employee)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee-data");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }



}
