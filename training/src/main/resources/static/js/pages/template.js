"use strict";

$(document).ready(function () {
	
	// ===== Functions ===================================================
	
	// Returns an object with the properties "button": <jQueryButton>, and "tabindex": as derived from the jQueryButton object. 
	function createButtonObject(jQueryButton) {
		return { button: jQueryButton, tabindex: jQueryButton.attr('tabindex') };
	}
	
	// ===== "Main" ===================================================
	
	var advEmployeeContent = $('#advEmployee-content');
	
	var advancedButton = $("#advOptions-btn");
	var advancedButtonObject = createButtonObject(advancedButton);
	
	// ===== "Handlers" ================================================
	
	advancedButton.on('click', function () {
		advEmployeeContent.toggleClass('hidden');
		(advancedButton.text() == "Change Password") ? (advancedButton.text("Hide Password")) : (advancedButton.text("Change Password"));
	});
	
})