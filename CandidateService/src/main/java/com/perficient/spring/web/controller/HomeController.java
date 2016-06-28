package com.perficient.spring.web.controller;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perficient.spring.web.model.Candidate;
import com.perficient.spring.web.model.CandidateDTO;
import com.perficient.spring.web.model.DropdownOption;
import com.perficient.spring.web.service.CandidateService;


/**
 * @author Nick Umble - Created the original file. 
 */
@Controller
public final class HomeController {

	static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private CandidateService service;

	/**
	 * Returns sample data for the dropdown until the database is fully implemented.
	 * @author Nick Umble
	 */
	@ModelAttribute("statuses")
	public List<DropdownOption> populateStatuses() {
		List<DropdownOption> statuses = new ArrayList<DropdownOption>(4);

		statuses.add(new DropdownOption(0, "Interviewing"));
		statuses.add(new DropdownOption(1, "Waiting on response"));
		statuses.add(new DropdownOption(2, "Discontinued pursuit"));
		statuses.add(new DropdownOption(3, "Hired"));

		return statuses;
	}

	/**
	 * Returns sample data for the dropdown until the database is fully implemented.
	 * @author Nick Umble
	 */
	@ModelAttribute("degrees")
	public List<DropdownOption> populateDegrees() {
		List<DropdownOption> degrees = new ArrayList<DropdownOption>(5);

		degrees.add(new DropdownOption(0, "High school diploma / GED"));
		degrees.add(new DropdownOption(1, "Certificate"));
		degrees.add(new DropdownOption(2, "Associate's degree"));
		degrees.add(new DropdownOption(3, "Bachelor's degree"));
		degrees.add(new DropdownOption(4, "Master's degree"));
		degrees.add(new DropdownOption(5, "Doctorate"));

		return degrees;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showThymeleafPage() {
		return "redirect:/add";
	}



	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCandidate(Model model) {
		model.addAttribute("candidate", new Candidate()); // replace service.getSampleCandidate() with new Candidate(), testing rn
		return "candidate-thymeleaf-add";
	}


	@RequestMapping(value = "/add", params = "add", method = RequestMethod.POST)
	public String addCandidate(Candidate candidate,
		RedirectAttributes redirectAttributes) {
		System.out.println("In the /add POST requestmapping");
		redirectAttributes.addAttribute("id", service.addcandidate(candidate));
		return "redirect:/edit";

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String EditCandidate(@RequestParam("id") int id, Model model) {
		System.out.println("In /edit GET method, id: " + id);
		model.addAttribute("candidate", service.getOneCandidate(id));
		return "candidate-thymeleaf";
	}

	@RequestMapping(value = "/edit", params = "save", method = RequestMethod.POST)
	public String saveMethod(Candidate candidate, Model model) {
		//Save button implementation for Candidate Service goes here
		model.addAttribute("candidate", service.saveCandidate(candidate));
		return "candidate-thymeleaf";
	}

	@RequestMapping(value = "/edit", params = "convert",
		method = RequestMethod.POST)
	public String convertMethod(Candidate candidate, Model model) {
		System.out.println("converting here");
		model.addAttribute("candidate", service.convertCandidate(candidate));
		//Convert to employee button implementation for Candidate Service goes here
		return "home";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class,
			new CustomDateEditor(dateFormat, false));
	}

	/*  << Pratyusha Vankayala */
	@RequestMapping("/home")
	public String getOneCandidate(Model model) {
		model.addAttribute("candidate", service.getOneCandidate(1)); // gives retrieved candidate object to home.html
		return "candidate-thymeleaf";
	}

	/*  Pratyusha Vankayala >> */

	//	@RequestMapping(value = "/convert", params="convert", method = RequestMethod.POST)
	//	public String convertCandidate(Candidate candidate,Model model){
	//		model.addAttribute("canidate", service.convertToEmployee(candidate));
	//				return "candidate-thymeleaf";
	//	}


	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST,
		consumes = "text/plain")
	public ArrayList<String> findList(@RequestBody String searchBar) {
		System.out.println("in /search");
		//		ArrayList<String> a = new ArrayList<String>();
		//		a.add(searchBar);
		//		return a;
		return service.findAll(searchBar);
	}

	/*  << Pratyusha Vankayala */

	/*
	 * *** method for file upload  
	
	
		
		@RequestMapping(value = "/add", params = "add", method = RequestMethod.POST)
	//	public void addCandidate(Candidate candidate, RedirectAttributes redirectAttributes, @RequestParam("fileUpload") CandidateDTO formdata ) {
		
		public void addCandidate(@ModelAttribute("addForm") CandidateDTO formdata ) {
			System.out.println("In the /add POST requestmapping");
		//	System.out.println(candidate.getResume());
	//		try {
	//			System.out.println(file.getCanonicalPath());
	//		
	//		} catch (IOException e1) {
	//			// TODO Auto-generated catch block
	//			e1.printStackTrace();
	//			
	//		}
	//		try {
	//			FileInputStream input = new FileInputStream(file);
			try {
	//				MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
			//	System.out.println(file.getContentType());
						
					Blob blob = new SerialBlob(formdata.getFileUpload().getBytes());
					
					
					try{
						Class.forName("org.h2.Driver");
						Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
						PreparedStatement insertPreparedStatement = null;
						String insertQuery = "INSERT INTO CANDIDATE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, DEGREE_EN, MAJOR, SKILL_SET, GRADUATION_DATE, STATUS_EN, COMMENTS, RESUME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
						try {
							insertPreparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
							insertPreparedStatement.setString(1, "blah");
							insertPreparedStatement.setString(2,"blah");
							insertPreparedStatement.setString(3, "blah");
							insertPreparedStatement.setString(4, "blah");
							insertPreparedStatement.setDate(5, null);
							
							insertPreparedStatement.setInt(6, 1);
							insertPreparedStatement.setString(7, "blah");
							insertPreparedStatement.setString(8, "blah");
							insertPreparedStatement.setDate(9, null);
							insertPreparedStatement.setInt(10, 1);
							insertPreparedStatement.setString(11, "blah");
							insertPreparedStatement.setBlob(12, blob);
							insertPreparedStatement.executeUpdate();
									
							insertPreparedStatement.close();
						} catch (SQLException e) {
							System.out.println("Insert to database for add failed");
							System.out.println(e.getMessage());
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			
	//		if(file.to){
	//			try{
	//				BufferedOutputStream stream = new BufferedOutputStream(
	//						new FileOutputStream(new File(CandidateServiceApplication.ROOT + "/" + "resume")));
	//                FileCopyUtils.copy(file.getInputStream(), stream);
	//				stream.close();
	//				redirectAttributes.addFlashAttribute("message",
	//						"You successfully uploaded " + "resume" + "!");
	//			}
	//			catch (Exception e) {
	//				redirectAttributes.addFlashAttribute("message",
	//						"You failed to upload " + "resume" + " => " + e.getMessage());
	//			}
	//		}
			
		//	redirectAttributes.addAttribute("id", service.addcandidate(candidate));
		//	return "redirect:/edit";
			
		}
	
		*/

	/*  Pratyusha Vankayala >> */

}
