package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {



    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);


        return "search";
    }

    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value ="/results", method = RequestMethod.POST)
    public String search(Model model,@RequestParam  String searchType, String searchTerm){

        model.addAttribute("columns", ListController.columnChoices);

        searchType = searchType.toLowerCase();
        searchTerm = searchTerm.toLowerCase();

        ArrayList<HashMap<String, String>> jobsFound = new ArrayList<>();

        if(searchType.equals("all")){

            jobsFound = JobData.findByValue(searchTerm);

        }else{
            jobsFound = JobData.findByColumnAndValue(searchType,searchTerm);

        }
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm",searchTerm);
        model.addAttribute("jobsFound",jobsFound);

        return "search";
    }

}
