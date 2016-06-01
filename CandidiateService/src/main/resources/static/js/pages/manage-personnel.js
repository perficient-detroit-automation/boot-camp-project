"use strict";

$(document).ready(function () {

	// ===== Functions ----- ----- ----- ----- ----- 
	// TODO: move many of these functions to template.js

	// Returns an object with the properties "button": <jQueryButton>, and "tabindex": as derived from the jQueryButton object. 
	function createButtonObject(jQueryButton) {
		return { button: jQueryButton, tabindex: jQueryButton.attr('tabindex') };
	}

	// Removes (each of) the jQuery-wrapped "button" parameter from interaction, both keyboard and mouse.
	function disableButton(button) {
		$(button).each(function () {
			var thisButton = $(this).addClass("disabled");
			thisButton.attr("tabindex", "-1");
		});
	}

	// Enables (each of) the jQuery-wrapped button "object" for both keyboard and mouse interaction. Includes setting the tabindex for "button" to the object:tabindex property.
	function enableButtonObject(buttonObject) {
		$(buttonObject).each(function () {
			var thisButton = $(this.button).removeClass("disabled");
			thisButton.attr("tabindex", this.tabindex);
		});
	}

	// Checks to see that the "visible" jQuery object is indeed visible. If so, it renders it hidden and then renders the "hidden" jQuery object visible.
	function switchHiddenContent(toHide, toReveal) {
		hide(toHide);
		toReveal.removeClass("hidden");
	}

	// Adds the "hidden" class to (each) "section".
	function hide(section) {
		$(section).each(function () { $(this).addClass("hidden") });
	}

	// Returns true if the supplied "formInput" element's value is '' or null.
	function isEmpty(formInput) {
		var fieldValue = $(formInput).val();
		return (fieldValue === '' || fieldValue === null);
	}

	// Scans all required, visible fields for proper existence of input. If all required fields have data, it enables the Add/Change button; otherwise, it disables it.
	function checkForRequiredInput() {
		var allRequiredFields = personnelDataForm.find("div.all-required + div").find(":input.form-control:visible");
		var failsRequirements = false;

		allRequiredFields.each(function () {
			failsRequirements |= isEmpty(this);
			if (failsRequirements) { return false; }
		});

		// since e-mail and phone are common fields, we'll check that 1+ of them have entries...provided that all required fields already do
		if (!failsRequirements) {
			failsRequirements = isEmpty($("#phone-number")) && isEmpty($("#e-mail"));

			// TODO: get a proper resume check in here
			if (!failsRequirements && !candidateContent.hasClass("hidden")) {
				failsRequirements = $("#resume-file").text() == "(no resume on file)"
			}
		}

		failsRequirements ? disableButton(addChangeButton) : enableButtonObject(addChangeButtonObject);
	}

	// Toggles the convert button between the candidate and employee content sections. Returns true if it toggled from candidate to employee, and false if it toggled from employee to candidate.
	function toggleConvertButton() {
		if (convertButton.text() == "Convert to Employee") {
			convertButton.text() == "Revert to Candidate";
			convertButton.appendTo("#employee-content div.section-heading");

			return true;
		}

		// (else) if (convertButton.text() == "Revert to Candidate")
		convertButton.text() == "Convert to Employee";
		convertButton.appendTo("#candidate-content div.section-heading");

		return false;
	}
	// ===== "Main" ----- ----- ----- ----- ----- 

	var personnelSelector = $("#personnel-type");

	var addNewButton = $('#add-new-btn');
	var addNewButtonObject = createButtonObject(addNewButton);

	var searchField = $('#search-bar');

	var searchButton = $('#search-btn');
	var searchButtonObject = createButtonObject(searchButton);

	var personnelDataForm = $("#personnel-data-form");
	var commonContent = $('#common-content');
	var candidateContent = $("#candidate-content");

	var uploadResumeButton = $("#upload-resume");
	var uploadResumeButtonObject = createButtonObject(uploadResumeButton);
	var changeResumeButton = $("#change-resume");
	var changeResumeButtonObject = createButtonObject(changeResumeButton);
	var removeResumeButton = $("#remove-resume");
	var removeResumeButtonObject = createButtonObject(removeResumeButton);
	var downloadResumeButton = $("#download-resume");
	var downloadResumeButtonObject = createButtonObject(downloadResumeButton);

	var employeeContent = $("#employee-content");
	var buttonSection = $("#button-section");

	var addChangeButton = $("#add-change-btn");
	var addChangeButtonObject = createButtonObject(addChangeButton);
	var startOverButton = $("#start-over-btn");
	var startOverButtonObject = createButtonObject(startOverButton);
	
	var convertButton = $("#convert-to-employee-btn");
	var convertButtonObject = createButtonObject(convertButton);
	var saveButton = $("#save-candidate-btn");
	var saveButtonObject = createButtonObject(convertButton);

	disableButton([addNewButton, searchButton, addChangeButton]);

	// ====- Handlers ----- ----- ----- ----- 

	personnelSelector.on('change', function () { enableButtonObject(addNewButtonObject); });

	addNewButton.on('click', function () {
		// initial settings, when only the first row of controls shows
		if (commonContent.hasClass('hidden')) {
			commonContent.removeClass('hidden');

			if (personnelSelector.val() == 'Employee') {
				employeeContent.removeClass('hidden');
			} else { // if (personnelSelector.val() == 'Candidate')
				candidateContent.removeClass('hidden');
				enableButtonObject(convertButtonObject);
			}

			//enableButtonObject(startOverButtonObject);
			buttonSection.removeClass("hidden");
		} else { // if entry fields are visible (#common-content, plus others)...
			if (personnelSelector.val() == "Employee") {
				switchHiddenContent(candidateContent, employeeContent);
			} else {	//	if (personnelSelector.val() == "Candidate")
				switchHiddenContent(employeeContent, candidateContent);
				enableButtonObject(convertButtonObject);
			}

			if (convertButton.text() == "Revert to Candidate") {
				convertButton.appendTo("#candidate-content div.section-heading");
				convertButton.text("Convert to Employee");
			}
		}

		$("#resume-file").text("(no resume on file)");
		enableButtonObject(uploadResumeButtonObject);
		disableButton([changeResumeButton, removeResumeButton, downloadResumeButton]);
		addChangeButton.text("Add");
		$("#first-name").focus();
	});

	searchField.on('keyup', function () {
		if (searchField.val() === "") {
			disableButton(searchButton);
		} else {
			enableButtonObject(searchButtonObject);
		}
	});

	// TODO: Have this function address the content properly
	searchButton.on('click', function () {
		// initial settings, when only the first row of controls shows
		if (commonContent.hasClass('hidden')) {
			commonContent.toggleClass('hidden');
			candidateContent.toggleClass('hidden');
			employeeContent.toggleClass('hidden');
			buttonSection.toggleClass('hidden');
			enableButtonObject(startOverButtonObject);
		}

		disableButton(uploadResumeButton);
		enableButtonObject([changeResumeButtonObject, removeResumeButtonObject, downloadResumeButtonObject]);
		addChangeButton.text('Change');
		disableButton(convertButton);
		$("#first-name").focus();

		if (searchField.val() == "me") {
			$("#first-name").val("Nicholas");
			$("#last-name").val("Umble");

			$("#phone-number").val("248-469-2924");
			$("#e-mail").val("nicholas.umble@perficient.com");

			$("#status-dropdown").val("Hired");
			$("#degree-dropdown").val("Associate's degree");
			$("#graduation-date").val("5/9/2009");

			$("#major-field").val("Software Engineering");
			$("#skillset-field").val("Java and Web development");

			var today = new Date();

			$("#resume-file").text("Nicholas_Umble_-_Resume__" + (today.getMonth() + 1) + '_' + today.getDate() + '_' + today.getFullYear() + ".docx");

			toggleConvertButton();

			$("#comments-area").text("Test run with full, valid data.");

			$("#employee-title").val("Technical Consultant");
			$("#employee-id").val("12345");
			$("#start-date").val("3/31/2015");

			$("#employee-type").val("Full-time");
			$("#employee-dept").val("Development");

			enableButtonObject(addChangeButtonObject);
		} else if (searchField.val() == "bad") {
			$("#first-name").val("a");
			$("#last-name").val("b");

			$("#phone-number").val("1");
			$("#e-mail").val("c");

			$("#status-dropdown").val("Hired");
			$("#degree-dropdown").val("Associate's degree");
			$("#graduation-date").val("5");

			$("#major-field").val("d");
			$("#skillset-field").val("e");

			$("#resume-file").text("bla bla resume.docx");

			toggleConvertButton();

			$("#comments-area").text("Test run with nearly all invalid data.");

			$("#employee-title").val("f");
			$("#employee-id").val("g");
			$("#start-date").val("3");

			$("#employee-type").val("Full-time");
			$("#employee-dept").val("Development");
			$("#end-date").val("4");

			enableButtonObject(addChangeButtonObject);
		}
	});

	personnelDataForm.on("change", ":input.form-control", function () { checkForRequiredInput(); });

	personnelDataForm.on("click", "button.section-reset", function () {
		var target = $(event.target);

		if (target.hasClass("glyphicon")) {
			target = target.parent();
		}

		target = target.parent().parent().find(":input.form-control");
		// TODO: reset to database-derived values for a selected Search result
		target.each(function () { this.value = ""; });
		checkForRequiredInput();
		target[0].focus();
	});

	// TODO: update with proper functionality, do we really want to have a revert back...?
	convertButton.on('click', function () {
		employeeContent.toggleClass("hidden");

		// if it's currently only a plain Candidate...
		if (convertButton.text() == "Convert to Employee") {
			$("#employee-title").focus();
			convertButton.appendTo("#employee-content div.section-heading");
			convertButton.text("Revert to Candidate");
			disableButton(addChangeButton);
		} else {
			$("#employee-content").find("div.section-heading button.section-reset")[0].click();
			$("#status-dropdown").focus();
			convertButton.appendTo("#candidate-content div.section-heading");
			convertButton.text("Convert to Employee");
			checkForRequiredInput();
		}
		alert("You clicked the Convert to Employee button, need to finish implementing");
	});
	
	// TODO: update with proper save functionality
	saveButton.on('click', function () {
		//$("#resume-file").text("(uploaded resume)");
		//disableButton(uploadResumeButton);
		//enableButtonObject([changeResumeButtonObject, removeResumeButtonObject, downloadResumeButtonObject]);
		//checkForRequiredInput();
		alert("You clicked the Save button, need to implement");
	});

	// TODO: update with proper functionality
	uploadResumeButton.on('click', function () {
		$("#resume-file").text("(uploaded resume)");
		disableButton(uploadResumeButton);
		enableButtonObject([changeResumeButtonObject, removeResumeButtonObject, downloadResumeButtonObject]);
		checkForRequiredInput();
	});

	startOverButton.on('click', function () {
		personnelSelector.val("");
		searchField.val("");

		disableButton([addNewButton, searchButton, addChangeButton]);
		hide([commonContent, candidateContent, employeeContent, buttonSection]);

		if (addChangeButton.text() == "Add") {
			personnelSelector.focus();
		} else { // if addChangeButton.text() == "Change"){
			searchField.focus();
		}

		if (convertButton.text() == "Revert to Candidate") {
			convertButton.appendTo("#candidate-content div.section-heading");
			convertButton.text("Convert to Employee");
		}
	});
});
